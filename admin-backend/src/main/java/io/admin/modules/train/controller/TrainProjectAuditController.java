package io.admin.modules.train.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.service.TrainProjectAuditService;
import io.admin.modules.train.service.TrainProjectService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-01-07 11:41:08
 */
@RestController
@RequestMapping("train/projectAudit")
public class TrainProjectAuditController {

    @Autowired
    private TrainProjectAuditService trainProjectAuditService;

    @Autowired
    private TrainProjectService trainProjectService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("train:project:audit:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = trainProjectAuditService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 项目列表
     */
    @RequestMapping("/projectList")
    @RequiresPermissions("train:project:audit:projectList")
    public R projectList(@RequestParam Map<String, Object> params){
        params.put("statusCode", new String[]{"2", "3","4","5"});
        PageUtils page = trainProjectService.projectQueryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("train:project:audit:info")
    public R info(@PathVariable("id") Long id){
		TrainProjectEntity trainProject = trainProjectAuditService.selectById(id);

        return R.ok().put("trainProject", trainProject);
    }


    @RequestMapping("/audit")
    @RequiresPermissions("train:project:audit:audit")
    public R audit(@RequestBody TrainProjectEntity trainProject){
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();

        trainProject.setAuditorId(sysUserEntity.getUsername());
        trainProject.setAuditorName(sysUserEntity.getUsercname());
        trainProject.setAuditTime(new Date());

        trainProjectAuditService.updateById(trainProject);

        return R.ok();
    }

    @RequestMapping("/print/{id}")
    public void info(@PathVariable("id") Long id, HttpServletResponse response) {
        TrainProjectEntity trainProject = trainProjectAuditService.selectById(id);
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        OutputStream out = null;
        byte[] pdfTemplate;
        try {
            //设置响应contentType
            response.setContentType("application/pdf");
            //设置响应文件名称
            String fileName = new String("申请表.pdf".getBytes("UTF-8"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            File file = ResourceUtils.getFile("classpath:template/auditTemplate.pdf");

            pdfTemplate = IOUtils.toByteArray(new FileInputStream(file));

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            BaseFont bf = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

            PdfReader reader = new PdfReader(pdfTemplate);
            PdfStamper stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            form.setFieldProperty("projectName", "textfont", bf, null);
            form.setField("projectName", trainProject.getProjectName());
            form.setField("projectBudget", trainProject.getProjectBudget().toString());

            if (trainProject.getTrainClassHour() != null) {
                form.setField("trainClassHour", trainProject.getTrainClassHour().toString());
            }
            if (trainProject.getTrainTarget() != null){
                form.setFieldProperty("trainTarget", "textfont", bf, null);
                form.setField("trainTarget", trainProject.getTrainTarget());
            }

            if (trainProject.getClassHourAllocation() != null){
                form.setFieldProperty("classHourAllocation", "textfont", bf, null);
                form.setField("classHourAllocation", trainProject.getClassHourAllocation());
            }

            if (trainProject.getTrainRequire() != null){
                form.setFieldProperty("trainRequire", "textfont", bf, null);
                form.setField("trainRequire", trainProject.getTrainRequire());
            }

            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();
            Document doc = new Document();
            out = response.getOutputStream();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);


//            response.setHeader("Content-disposition", "formPDF");
//            response.setHeader("Content-type", "application/pdf;charset=utf-8");
//            response.setHeader("Content-disposition", "attachment;filename=formPDF.pdf");
//            response.setHeader("Pragma", "No-cache");

            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pdfOutputStream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

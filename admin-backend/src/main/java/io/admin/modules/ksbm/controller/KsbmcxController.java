package io.admin.modules.ksbm.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.modules.ksbm.entity.KsbmxxbEntity;
import io.admin.modules.ksbm.service.KsbmxxbService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.admin.common.utils.ShiroUtils.getUserEntity;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-17 17:24:19
 */
@RestController
@RequestMapping("ksbm/ksbmcx")
public class KsbmcxController {
    @Autowired
    private KsbmxxbService ksbmxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ksbm:ksbmcx:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ksbmxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ksbm:ksbmcx:info")
    public R info(@PathVariable("id") Long id){
			KsbmxxbEntity ksbmxxb = ksbmxxbService.selectById(id);

        return R.ok().put("ksbmxxb", ksbmxxb);
    }

    @RequestMapping(value="/export",method = RequestMethod.POST)
    @RequiresPermissions("ksbm:ksbmcx:export")
    public void export(@RequestParam Map<String, Object> params,HttpServletResponse response) throws Exception {
        params.remove("t");
        String ksnd=(String)params.get("ksnd");
        String szbj=(String)params.get("szbj");
        //xlsx 2007以上版本
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode( "学生考试报名信息表.xlsx","UTF-8"));
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("学生考试报名信息");
        int rowNum = 1;
        String[] headers = {"考试年度", "考试科目","学号", "姓名","性别", "出生日期","民族","所在班级", "身份证号", "联系电话"};
        //headers表示excel表中第一行的表头
        XSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            row.createCell(i).setCellValue(headers[i]);
        }
        List<KsbmxxbEntity> zsxxList=ksbmxxbService.selectList(new EntityWrapper<KsbmxxbEntity>().
                like(StringUtils.isNotBlank(ksnd), "ksnd", ksnd).
                like(StringUtils.isNotBlank(szbj), "szbj", szbj)
        );

        //在表中存放查询到的数据放入对应的列
        for (KsbmxxbEntity ksbmxxbEntity : zsxxList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(ksbmxxbEntity.getKsnd());
            row1.createCell(1).setCellValue(ksbmxxbEntity.getKsxmmc());
            row1.createCell(2).setCellValue(ksbmxxbEntity.getXsxh());
            row1.createCell(3).setCellValue(ksbmxxbEntity.getXsxm());
            row1.createCell(4).setCellValue(ksbmxxbEntity.getSex());
            row1.createCell(5).setCellValue(ksbmxxbEntity.getBirth());
            row1.createCell(6).setCellValue(ksbmxxbEntity.getMz());
            row1.createCell(7).setCellValue(ksbmxxbEntity.getSzbj());
            row1.createCell(8).setCellValue(ksbmxxbEntity.getSfzh());
            row1.createCell(9).setCellValue(ksbmxxbEntity.getTelephone());

            rowNum++;
        }
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


}

package io.admin.modules.zsgl.controller;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.modules.zsgl.entity.XsZsxxbEntity;
import io.admin.modules.zsgl.service.XsZsxxbService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.admin.modules.zsgl.entity.ZsxxViewEntity;
import io.admin.modules.zsgl.service.ZsxxViewService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-14 21:51:59
 */
@RestController
@RequestMapping("zsgl/zscx")
public class ZsxxViewController {
    @Autowired
    private ZsxxViewService zsxxViewService;

    @Autowired
    private XsZsxxbService xsZsxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("zsgl:zscx:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zsxxViewService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping(value="/export",method = RequestMethod.POST)
    @RequiresPermissions("zsgl:zscx:export")
    public void export(@RequestParam Map<String, Object> params,HttpServletResponse response) throws Exception {

//        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//        response.setHeader("contentType", "application/vnd.ms-excel");
//        response.setContentType("multipart/form-data");
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // 下载文件的默认名称
//        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode( "学生证书信息表.xlsx","UTF-8"));
/*  //xls
        response.setHeader("contentType", "application/vnd.ms-excel;charset=UTF-8");
        params.remove("t");
        List<XsZsxxbEntity> zsxxList=xsZsxxbService.selectByMap(params);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生证书信息");
        int rowNum = 1;
        String[] headers = { "学号", "姓名", "所在班级", "身份证号", "联系电话", "证书名称", "获证日期"};

        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (XsZsxxbEntity xsZsxxbEntity : zsxxList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(xsZsxxbEntity.getXsxh().toString());
            System.out.println(xsZsxxbEntity.getXsxh() + "--------------------------");
            row1.createCell(1).setCellValue(xsZsxxbEntity.getXsxm());
            row1.createCell(2).setCellValue(xsZsxxbEntity.getSzbj());
            row1.createCell(3).setCellValue(xsZsxxbEntity.getSfzh());
            row1.createCell(4).setCellValue(xsZsxxbEntity.getTelephone());
            row1.createCell(5).setCellValue(xsZsxxbEntity.getZsmc());
            row1.createCell(6).setCellValue(xsZsxxbEntity.getHzrq());
            rowNum++;
        }
        response.flushBuffer();
        workbook.write(response.getOutputStream());
*/

        params.remove("t");
//        Map<String, Object> newparams= new HashMap<String, Object>();
        String ztmstr=(String)params.get("ztm");
        Integer ztm=0;
        if(!ztmstr.equals("") && ztmstr != null){
            ztm = Integer.parseInt(ztmstr);
        }
        String hzrq=(String)params.get("sznd");
//        if(!sznd.equals("") && sznd!=null ){
//            newparams.put("hzrq",sznd);
//        }
        String szbj=(String)params.get("szbj");
//        if(!szbj.equals("") && szbj!=null ){
//            newparams.put("szbj",szbj);
//        }
/*
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if( entry.getValue().equals("") || entry.getValue()==null){
                params.remove(entry.getKey());
            }
        }
*/
/*

  //poi方法 xls
  response.setCharacterEncoding("utf-8");
   response.setHeader("contentType", "application/vnd.ms-excel");
   response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode( "学生证书信息表.xls","UTF-8"));
        List<XsZsxxbEntity> zsxxList=xsZsxxbService.selectByMap(params);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), XsZsxxbEntity.class, zsxxList);
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
*/


        //xlsx 2007以上版本
        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentType("multipart/form-data");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode( "学生证书信息表.xlsx","UTF-8"));
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("学生证书信息");
        int rowNum = 1;
        String[] headers = { "学号", "姓名", "所在班级", "身份证号", "联系电话", "证书名称", "获证日期"};

        //headers表示excel表中第一行的表头
        XSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            row.createCell(i).setCellValue(headers[i]);
        }

        List<XsZsxxbEntity> zsxxList=xsZsxxbService.selectList(new EntityWrapper<XsZsxxbEntity>().
                like(StringUtils.isNotBlank(hzrq), "hzrq", hzrq).
                like(StringUtils.isNotBlank(szbj), "szbj", szbj).
                eq(   ztm > 0 ,"ztm",ztm)
        );

        //在表中存放查询到的数据放入对应的列
        for (XsZsxxbEntity xsZsxxbEntity : zsxxList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(xsZsxxbEntity.getXsxh().toString());
            row1.createCell(1).setCellValue(xsZsxxbEntity.getXsxm());
            row1.createCell(2).setCellValue(xsZsxxbEntity.getSzbj());
            row1.createCell(3).setCellValue(xsZsxxbEntity.getSfzh());
            row1.createCell(4).setCellValue(xsZsxxbEntity.getTelephone());
            row1.createCell(5).setCellValue(xsZsxxbEntity.getZsmc());
            row1.createCell(6).setCellValue(xsZsxxbEntity.getHzrq());
            rowNum++;
        }
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


}

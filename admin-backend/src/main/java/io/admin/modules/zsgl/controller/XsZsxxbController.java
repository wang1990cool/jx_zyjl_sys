package io.admin.modules.zsgl.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.admin.modules.zsgl.entity.XsZsxxbEntity;
import io.admin.modules.zsgl.service.XsZsxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 学生证书信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-27 10:14:57
 */
@RestController
@RequestMapping("zsgl/zscx")
public class XsZsxxbController {
    @Autowired
    private XsZsxxbService xsZsxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("zsgl:zscx:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xsZsxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("zsgl:zscx:info")
    public R info(@PathVariable("id") Long id){
			XsZsxxbEntity xsZsxxb = xsZsxxbService.selectById(id);

        return R.ok().put("xsZsxxb", xsZsxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("zsgl:zscx:save")
    public R save(@RequestBody XsZsxxbEntity xsZsxxb){
			xsZsxxbService.insert(xsZsxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("zsgl:zscx:update")
    public R update(@RequestBody XsZsxxbEntity xsZsxxb){
			xsZsxxbService.updateById(xsZsxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("zsgl:zscx:delete")
    public R delete(@RequestBody Long[] ids){
			xsZsxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
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
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if( entry.getValue().equals("") ){
                params.remove(entry.getKey());
            }
        }
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

        List<XsZsxxbEntity> zsxxList=xsZsxxbService.selectByMap(params);

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

package io.admin.modules.train.controller;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import freemarker.template.utility.StringUtil;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.train.entity.TrainScoreEntity;
import io.admin.modules.train.service.TrainScoreService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-03-10 19:57:02
 */
@RestController
@RequestMapping("train/score")
public class TrainScoreController {
    @Autowired
    private TrainScoreService trainScoreService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("train:score:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = trainScoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("train:score:info")
    public R info(@PathVariable("id") Long id) {
        TrainScoreEntity trainScore = trainScoreService.selectById(id);

        return R.ok().put("trainScore", trainScore);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("train:score:save")
    public R save(@RequestBody TrainScoreEntity trainScore) {
        trainScoreService.insert(trainScore);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("train:score:update")
    public R update(@RequestBody TrainScoreEntity trainScore) {
        trainScoreService.updateById(trainScore);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("train:score:delete")
    public R delete(@RequestBody Long[] ids) {
        trainScoreService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping(value = "/import")
    @RequiresPermissions("train:score:import")
    @Transactional
    public R upload(@RequestParam MultipartFile file) {
        String fileName = file.getOriginalFilename();
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        Workbook wb = null;
        Sheet sheet = null;

        int errorNum = 0;
        try {
            InputStream is = file.getInputStream();
//        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
//        } else {
//            wb = new XSSFWorkbook(is);
//        }
            sheet = wb.getSheetAt(0);

            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCells = 0;
            if (totalRows >= 1 && sheet.getRow(0) != null) {
                totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            }
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                errorNum = i;
                TrainScoreEntity trainScoreEntity = new TrainScoreEntity();
                Row row = sheet.getRow(i);
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                String year = row.getCell(0).getStringCellValue();
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String studentNum = row.getCell(1).getStringCellValue();
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                String courseName = row.getCell(4).getStringCellValue();
                List<TrainScoreEntity> entityList = trainScoreService.selectList(new EntityWrapper<TrainScoreEntity>().
                        eq("year", year).
                        eq("student_num", studentNum).
                        eq("course_name", courseName) );

                if (entityList.size() > 0){
                    entityList.get(0).setStudentName(row.getCell(2).getStringCellValue());
                    entityList.get(0).setCourseName(row.getCell(4).getStringCellValue());
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    entityList.get(0).setStudentScore(Double.parseDouble(row.getCell(5).getStringCellValue()));
                    trainScoreService.updateById(entityList.get(0));
                } else {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    trainScoreEntity.setYear(row.getCell(0).getStringCellValue());
                    trainScoreEntity.setStudentNum(row.getCell(1).getStringCellValue());
                    trainScoreEntity.setStudentName(row.getCell(2).getStringCellValue());
                    trainScoreEntity.setIdentityCard(row.getCell(3).getStringCellValue());
                    trainScoreEntity.setCourseName(row.getCell(4).getStringCellValue());
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    trainScoreEntity.setStudentScore(Double.parseDouble(row.getCell(5).getStringCellValue()));
                    trainScoreService.insert(trainScoreEntity);
                }
            }

        }catch (Exception e){
//            if (errorNum !=  (sheet.getLastRowNum())){
                return R.error("第" + (errorNum+1)+ "行数据存在问题");
//            }
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//关键
        }

        return R.ok();
    }

//    public static List<List<String>> readBook(InputStream inputStream, boolean isExcel2003) {
//        List<List<String>> dataLst = null;
//        try {
//            /** 根据版本选择创建Workbook的方式 */
//            Workbook wb = null;
//            if (isExcel2003) {
//                wb = new HSSFWorkbook(inputStream);
//            } else {
//                wb = new XSSFWorkbook(inputStream);
//
//            }
//            dataLst = readExcel(wb);
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//        return dataLst;
//    }
//
//
//    private static List<List<String>> readExcel(Workbook wb) {
//        List<List<String>> dataLst = new ArrayList<List<String>>();
//
//        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
//        /** 得到第一个shell */
//        Sheet sheet = wb.getSheetAt(0);
//        /** 得到Excel的行数 */
//        int totalRows = sheet.getPhysicalNumberOfRows();
//        /** 得到Excel的列数 */
//        int totalCells = 0;
//        if (totalRows >= 1 && sheet.getRow(0) != null) {
//            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
//        }
//        /** 循环Excel的行 不取第一行 */
//        for (int r = 0; r < totalRows; r++) {
//            Row row = sheet.getRow(r);
//            if (row == null) {
//                continue;
//            }
//            List<String> rowLst = new ArrayList<String>();
//            /** 循环Excel的列 */
//            for (int c = 0; c <= totalCells; c++) {
//                Cell cell = row.getCell(c);
//                String cellValue = "";
//                if (null != cell) {
//                    // 以下是判断数据的类型
//                    switch (cell.getCellType()) {
//                        case HSSFCell.CELL_TYPE_NUMERIC: // 数字
//                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                                Date date = cell.getDateCellValue();
//                                if (date != null) {
//                                    cellValue = new SimpleDateFormat("yyyy-MM-dd")
//                                            .format(date);
//                                } else {
//                                    cellValue = "";
//                                }
//                            } else {
//                                cellValue = cell.getNumericCellValue() + "";
//                            }
//                            break;
//                        case HSSFCell.CELL_TYPE_STRING: // 字符串
//                            cellValue = cell.getStringCellValue();
//                            break;
//                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
//                            cellValue = cell.getBooleanCellValue() + "";
//                            break;
//                        case HSSFCell.CELL_TYPE_FORMULA: // 公式
//                            try {
//                                CellValue value;
//                                value = evaluator.evaluate(cell);
//                                switch (value.getCellType()) {              //判断公式类型
//                                    case Cell.CELL_TYPE_BOOLEAN:
//                                        cellValue  = value.getBooleanValue() + "";
//                                        break;
//                                    case Cell.CELL_TYPE_NUMERIC:
//                                        // 处理日期
//                                        if (DateUtil.isCellDateFormatted(cell)) {
//                                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                                            Date date = cell.getDateCellValue();
//                                            cellValue = format.format(date);
//                                        } else {
//                                            cellValue  = value.getNumberValue() + "";
//                                        }
//                                        break;
//                                    case Cell.CELL_TYPE_STRING:
//                                        cellValue  = value.getStringValue();
//                                        break;
//                                    case Cell.CELL_TYPE_BLANK:
//                                        cellValue = "";
//                                        break;
//                                    case Cell.CELL_TYPE_ERROR:
//                                        cellValue = "";
//                                        break;
//                                    case Cell.CELL_TYPE_FORMULA:
//                                        cellValue = "";
//                                        break;
//                                }
//                            } catch (Exception e) {
//                                cellValue = cell.getStringCellValue().toString();
//                                cell.getCellFormula();
//                            }
//                            break;
//                        case HSSFCell.CELL_TYPE_BLANK: // 空值
//                            break;
//                        case HSSFCell.CELL_TYPE_ERROR: // 故障
//                            cellValue = "非法字符";
//                            break;
//                        default:
//                            cellValue = "未知类型";
//                            break;
//                    }
//                }
//
//                if (StringUtil.isNotEmpty(cellValue) ) {
//                    rowLst.add(cellValue);
//                }
//
//            }
//
//            if (rowLst != null && rowLst.size() > 0) {
//                /** 保存第r行的第c列 */
//                dataLst.add(rowLst);
//            }
//
//        }
//        return dataLst;
//    }

    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request) {
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;

        String filename = "tpl.xls";

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.setHeader("Content-Disposition", "attachment;filename="  + filename);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        int readTmp = 0;

        try {
            Resource resource = new ClassPathResource("tpl/scoreTemplate.xls");
            inputStream = resource.getInputStream();
//            File file = ResourceUtils.getFile("classpath:tpl/scoreTemplate.xls");
            //6.通过response对象获取OutputStream流
            os = response.getOutputStream();
            //4.根据文件路径获取要下载的文件输入流
            bis = new BufferedInputStream(inputStream);
            while ((readTmp =bis.read(buff)) != -1){
                os.write(buff,0,readTmp);
            }
//            response.setContentType("application/vnd.ms-excel");
//            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//            response.addHeader("charset", "utf-8");
//            response.addHeader("Pragma", "no-cache");
//            String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);
//
//            inputStream = new FileInputStream(file);
//            servletOutputStream = response.getOutputStream();
//            IOUtils.copy(inputStream, servletOutputStream);
//            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                inputStream.close();
                // 召唤jvm的垃圾回收器
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        return R.ok();
    }

//    //文件下载
//    @RequestMapping( value = "/download",method = RequestMethod.POST)
//    public void download (HttpServletResponse response) throws UnsupportedEncodingException {
//            String filename = "成绩模板.xls";
////            response.setCharacterEncoding("UTF-8");
////            response.setHeader("content-type", "application/octet-stream");
////            response.setContentType("application/octet-stream");
//            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//            response.setHeader("Content-Disposition", "attachment;filename="  + URLEncoder.encode( filename,"UTF-8"));  //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
//            byte[] buff = new byte[1024];    //5.创建数据缓冲区
//            BufferedInputStream bis = null;
//            OutputStream os = null;
//            try {
//
//                File file = ResourceUtils.getFile("classpath:tpl/scoreTemplate.xls");
//                System.out.println(file.exists());
//                os = response.getOutputStream(); //6.通过response对象获取OutputStream流
//                bis = new BufferedInputStream(new FileInputStream(file));     //4.根据文件路径获取要下载的文件输入流
//                int i = bis.read(buff);         //7.将FileInputStream流写入到buffer缓冲区
//                while (i != -1) {
//                    os.write(buff, 0, buff.length); //8.使用将OutputStream缓冲区的数据输出到客户端浏览器
//                    os.flush();
//                    i = bis.read(buff);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//    }
}

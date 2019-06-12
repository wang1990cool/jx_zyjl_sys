package io.admin.modules.train.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.entity.TrainProjectTrainProgramEntity;
import io.admin.modules.train.entity.TrainScoreEntity;
import io.admin.modules.train.service.TrainProjectService;
import io.admin.modules.train.service.TrainProjectTrainProgramService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("train/projectTrainProgram")
public class TrainProjectTrainProgramController {

    @Autowired
    private TrainProjectTrainProgramService trainProjectTrainProgramService;

    @Autowired
    private TrainProjectService trainProjectService;


    /**
     * 项目列表
     */
    @RequestMapping("/projectList")
    @RequiresPermissions("train:project:trainProgram:projectList")
    public R projectList(@RequestParam Map<String, Object> params){
        params.put("statusCode", new String[]{"3", "4"});
        PageUtils page = trainProjectService.projectQueryPage(params);

        return R.ok().put("page", page);
    }

  /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("train:project:trainProgram:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = trainProjectTrainProgramService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TrainProjectTrainProgramEntity trainProject = trainProjectTrainProgramService.selectById(id);

        return R.ok().put("projectTrainProgram", trainProject);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TrainProjectTrainProgramEntity trainProgram){
        trainProjectTrainProgramService.insert(trainProgram);

        TrainProjectEntity trainProjectEntity = trainProjectService.selectOne(
                new EntityWrapper<TrainProjectEntity>().
                        eq("project_id", trainProgram.getProjectId()));

        trainProjectEntity.setStatusCode("4");
        trainProjectEntity.setStatus("方案已填写");

        trainProjectService.updateById(trainProjectEntity);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody TrainProjectTrainProgramEntity trainProject){
        trainProjectTrainProgramService.updateById(trainProject);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/finish")
    public R finish(@RequestBody String[] projectIds){
        for (int i = 0;i < projectIds.length; i++){
            TrainProjectEntity trainProjectEntity = trainProjectService.selectOne(
                    new EntityWrapper<TrainProjectEntity>().
                            eq("project_id", projectIds[i]));

            trainProjectEntity.setStatusCode("5");
            trainProjectEntity.setStatus("项目结束");

            trainProjectService.updateById(trainProjectEntity);
            trainProjectTrainProgramService.createWorkload(projectIds[i]);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        trainProjectTrainProgramService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping(value = "/import")
    @Transactional
    public R upload(@RequestParam("file") MultipartFile file,@RequestParam("projectId") String projectId) {

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
            wb = new HSSFWorkbook(is);
            sheet = wb.getSheetAt(0);

            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                errorNum = i;
                TrainProjectTrainProgramEntity t = new TrainProjectTrainProgramEntity();
                t.setProjectId(projectId);

                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getLastCellNum();j++){
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                }

                String teacherId = row.getCell(0).getStringCellValue();
                String teacherName = row.getCell(1).getStringCellValue();
                String classHour = row.getCell(2).getStringCellValue();
                String classDate = row.getCell(3).getStringCellValue();
                String classAddress = row.getCell(4).getStringCellValue();

                t.setTeacherId(teacherId);
                t.setTeacherName(teacherName);
                t.setClassHour(Integer.parseInt(classHour));
                t.setClassDate(classDate);
                t.setClassAddress(classAddress);

                trainProjectTrainProgramService.insert(t);
            }

        }catch (Exception e){
//            if (errorNum !=  (sheet.getLastRowNum())){
            return R.error("第" + (errorNum+1)+ "行数据存在问题");
//            }
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//关键
        }

        return R.ok();
    }

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
            Resource resource = new ClassPathResource("tpl/programTemplate.xls");
            inputStream = resource.getInputStream();

            os = response.getOutputStream();
            //4.根据文件路径获取要下载的文件输入流
            bis = new BufferedInputStream(inputStream);
            while ((readTmp =bis.read(buff)) != -1){
                os.write(buff,0,readTmp);
            }
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


}

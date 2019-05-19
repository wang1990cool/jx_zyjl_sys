package io.admin.modules.train.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.entity.TrainProjectTrainProgramEntity;
import io.admin.modules.train.service.TrainProjectService;
import io.admin.modules.train.service.TrainProjectTrainProgramService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
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
    @RequiresPermissions("train:project:delete")
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


}

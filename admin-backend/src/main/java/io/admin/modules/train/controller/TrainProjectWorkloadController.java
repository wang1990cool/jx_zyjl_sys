package io.admin.modules.train.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.entity.TrainProjectTrainProgramEntity;
import io.admin.modules.train.service.TrainProjectService;
import io.admin.modules.train.service.TrainProjectTrainProgramService;
import io.admin.modules.train.service.TrainProjectWorkloadService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("train/projectWorkload")
public class TrainProjectWorkloadController {

    @Autowired
    private TrainProjectWorkloadService trainProjectWorkloadService;

    @Autowired
    private TrainProjectService trainProjectService;

  /**
     * 列表
     */
    @RequestMapping("/projectList")
    @RequiresPermissions("train:project:projectWorkload:projectList")
    public R projectList(@RequestParam Map<String, Object> params){
        params.put("statusCode", new String[]{"5"});
        PageUtils page = trainProjectService.projectQueryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("train:project:projectWorkload:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = trainProjectWorkloadService.queryPage(params);

        return R.ok().put("page", page);
    }


}

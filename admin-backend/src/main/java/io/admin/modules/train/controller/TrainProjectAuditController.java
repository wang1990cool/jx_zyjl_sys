package io.admin.modules.train.controller;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.service.TrainProjectAuditService;
import io.admin.modules.train.service.TrainProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

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
        params.put("statusCode", new String[]{"2", "3"});
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


}

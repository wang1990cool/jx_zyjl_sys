package io.admin.modules.train.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.service.TrainProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-01-07 11:41:08
 */
@RestController
@RequestMapping("train/project")
public class TrainProjectController {

    @Autowired
    private TrainProjectService trainProjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("train:project:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = trainProjectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("train:project:info")
    public R info(@PathVariable("id") Long id){
		TrainProjectEntity trainProject = trainProjectService.selectById(id);

        return R.ok().put("trainProject", trainProject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("train:project:save")
    public R save(@RequestBody TrainProjectEntity trainProject){
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();

        trainProject.setApplicantId(sysUserEntity.getUsername());
        trainProject.setApplicantName(sysUserEntity.getUsercname());
        trainProject.setApplicantDept(sysUserEntity.getUnit());
        trainProject.setCreateUserId(sysUserEntity.getCreateUserId());
        trainProject.setCreateTime(new Date());


		trainProjectService.insert(trainProject);

        return R.ok();
    }

    @RequestMapping("/submit")
    @RequiresPermissions("train:project:submit")
    public R submit(@RequestBody String id) {
        TrainProjectEntity trainProjectEntity = trainProjectService.selectById(id);
        trainProjectEntity.setStatus("待中心审核");
        trainProjectEntity.setStatusCode("2");
        trainProjectService.updateById(trainProjectEntity);
        return R.ok();
    }

    @RequestMapping("/withdraw")
    @RequiresPermissions("train:project:withdraw")
    public R withdraw(@RequestBody String id) {
        TrainProjectEntity trainProjectEntity = trainProjectService.selectById(id);
        trainProjectEntity.setStatus("未提交");
        trainProjectEntity.setStatusCode("1");
        trainProjectService.updateById(trainProjectEntity);
        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("train:project:update")
    public R update(@RequestBody TrainProjectEntity trainProject){
		trainProjectService.updateById(trainProject);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("train:project:delete")
    public R delete(@RequestBody Long[] ids){
		trainProjectService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/checkProjectIdIsExists/{projectId}")
    @ResponseBody
    public R checkProjectIdIsExists(@PathVariable("projectId") String projectId){
        TrainProjectEntity trainProject = trainProjectService.selectOne(
                new EntityWrapper<TrainProjectEntity>().
                        eq("project_id", projectId)
        );
        if (trainProject != null) {
            return R.error("项目编号已存在！");
        }else {
            return R.ok();
        }
    }

}

package io.admin.modules.train.controller;

import java.util.Arrays;
import java.util.Map;

import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.service.TrainProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



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
		trainProjectService.insert(trainProject);

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

}

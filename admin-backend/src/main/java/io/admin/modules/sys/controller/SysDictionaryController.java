package io.admin.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.sys.entity.SysDictionaryEntity;
import io.admin.modules.sys.service.SysDictionaryService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 数据字典
 *
 * @author Wangcaner
 * @date 2018-07-04 22:26:17
 */
@RestController
@RequestMapping("sys/dic")
public class SysDictionaryController {
    @Autowired
    private SysDictionaryService sysDictionaryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dic:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysDictionaryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dic:info")
    public R info(@PathVariable("id") Long id){
		SysDictionaryEntity sysDictionary = sysDictionaryService.selectById(id);

        return R.ok().put("dic", sysDictionary);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dic:save")
    public R save(@RequestBody SysDictionaryEntity sysDictionary){
		sysDictionaryService.insert(sysDictionary);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dic:update")
    public R update(@RequestBody SysDictionaryEntity sysDictionary){
		sysDictionaryService.updateById(sysDictionary);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dic:delete")
    public R delete(@RequestBody Long[] ids){
		sysDictionaryService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}

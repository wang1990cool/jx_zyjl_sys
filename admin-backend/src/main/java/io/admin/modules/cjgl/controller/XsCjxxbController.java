package io.admin.modules.cjgl.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.cjgl.entity.XsCjxxbEntity;
import io.admin.modules.cjgl.service.XsCjxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-02-25 15:57:08
 */
@RestController
@RequestMapping("cjgl/xscjxxb")
public class XsCjxxbController {
    @Autowired
    private XsCjxxbService xsCjxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cjgl:xscjxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xsCjxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:xscjxxb:info")
    public R info(@PathVariable("id") Integer id){
			XsCjxxbEntity xsCjxxb = xsCjxxbService.selectById(id);

        return R.ok().put("xsCjxxb", xsCjxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cjgl:xscjxxb:save")
    public R save(@RequestBody XsCjxxbEntity xsCjxxb){
			xsCjxxbService.insert(xsCjxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cjgl:xscjxxb:update")
    public R update(@RequestBody XsCjxxbEntity xsCjxxb){
			xsCjxxbService.updateById(xsCjxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cjgl:xscjxxb:delete")
    public R delete(@RequestBody Integer[] ids){
			xsCjxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}

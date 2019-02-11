package io.admin.modules.zsgl.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.zsgl.entity.XsZsxxbEntity;
import io.admin.modules.zsgl.service.XsZsxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



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

}

package io.admin.modules.ksbm.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.ksbm.entity.KsbmxxbEntity;
import io.admin.modules.ksbm.service.KsbmxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import static io.admin.common.utils.ShiroUtils.getUserEntity;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-17 17:24:19
 */
@RestController
@RequestMapping("ksbm/ksbmxxb")
public class KsbmxxbController {
    @Autowired
    private KsbmxxbService ksbmxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ksbm:ksbmxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ksbmxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ksbm:ksbmxxb:info")
    public R info(@PathVariable("id") Long id){
			KsbmxxbEntity ksbmxxb = ksbmxxbService.selectById(id);

        return R.ok().put("ksbmxxb", ksbmxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ksbm:ksbmxxb:save")
    public R save(@RequestBody KsbmxxbEntity ksbmxxb) throws IOException {
            ksbmxxb.setXsxm(getUserEntity().getUsercname());
            ksbmxxb.setXsxh(getUserEntity().getUsername());
            ksbmxxb.setSzbj(getUserEntity().getUnit());
            FileInputStream fis = new FileInputStream(ksbmxxb.getZppath());
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            ksbmxxb.setZszp(buffer);
            ksbmxxb.setCreateTime(new Date());
			ksbmxxbService.insert(ksbmxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ksbm:ksbmxxb:update")
    public R update(@RequestBody KsbmxxbEntity ksbmxxb) throws IOException {
        ksbmxxb.setXsxm(getUserEntity().getUsercname());
        ksbmxxb.setXsxh(getUserEntity().getUsername());
        ksbmxxb.setSzbj(getUserEntity().getUnit());
        FileInputStream fis = new FileInputStream(ksbmxxb.getZppath());
        byte[] buffer=new byte[fis.available()];
        fis.read(buffer);
        ksbmxxb.setZszp(buffer);
//        ksbmxxb.setCreateTime(new Date());
			ksbmxxbService.updateById(ksbmxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ksbm:ksbmxxb:delete")
    public R delete(@RequestBody Long[] ids){
			ksbmxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}

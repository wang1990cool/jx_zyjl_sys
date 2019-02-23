package io.admin.modules.zsgl.controller;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.modules.zsgl.entity.XsZsxxbEntity;
import io.admin.modules.zsgl.service.XsZsxxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static io.admin.common.utils.ShiroUtils.getUserEntity;


/**
 * 学生证书信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-27 10:14:57
 */
@RestController
@RequestMapping("zsgl/bjsh")
public class BjshController {
    @Autowired
    private XsZsxxbService xsZsxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("zsgl:bjsh:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("szbj",getUserEntity().getUnit());
        params.put("ztm",2);
        PageUtils page = xsZsxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     *
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("zsgl:bjsh:info")
    public R info(@PathVariable("id") Long id){
			XsZsxxbEntity xsZsxxb = xsZsxxbService.selectById(id);

        return R.ok().put("xsZsxxb", xsZsxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/audit")
    @RequiresPermissions("zsgl:bjsh:audit")
    public R audit(@RequestBody XsZsxxbEntity xsZsxxb) throws IOException{
            XsZsxxbEntity zsxx=xsZsxxbService.selectById(xsZsxxb.getId());
            zsxx.setBjshyj(xsZsxxb.getBjshyj());
            zsxx.setZtm(xsZsxxb.getZtm());
            if(xsZsxxb.getZtm()==4){
                zsxx.setZt("待实训中心审核");

            } else{
                zsxx.setZt("班级审核不通过");
            }
        xsZsxxbService.updateById(zsxx);
        return R.ok();
    }




}

package io.admin.modules.zsgl.controller;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.modules.zsgl.entity.XsZsxxbEntity;
import io.admin.modules.zsgl.service.XsZsxxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
@RequestMapping("zsgl/zxsh")
public class ZxshController {
    @Autowired
    private XsZsxxbService xsZsxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("zsgl:zxsh:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("ztm","4");
        PageUtils page = xsZsxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     *
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("zsgl:zxsh:info")
    public R info(@PathVariable("id") Long id){
			XsZsxxbEntity xsZsxxb = xsZsxxbService.selectById(id);

        return R.ok().put("xsZsxxb", xsZsxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/audit")
    @RequiresPermissions("zsgl:zxsh:audit")
    public R audit(@RequestBody XsZsxxbEntity xsZsxxb) throws IOException{
            XsZsxxbEntity zsxx=xsZsxxbService.selectById(xsZsxxb.getId());
            zsxx.setBjshyj(xsZsxxb.getBjshyj());
            zsxx.setZtm(xsZsxxb.getZtm());
            if(xsZsxxb.getZtm()==6){
                zsxx.setZt("实训中心审核通过");

            } else{
                zsxx.setZt("实训中心审核不通过");
            }
        xsZsxxbService.updateById(zsxx);
        return R.ok();
    }




}

package io.admin.modules.dic.controller;

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

import io.admin.modules.dic.entity.ZdZsmcxxbEntity;
import io.admin.modules.dic.service.ZdZsmcxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import static io.admin.common.utils.ShiroUtils.getUserId;


/**
 * 证书名称信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-25 21:49:19
 */
@RestController
@RequestMapping("/dic/zdzsmcxxb")
public class ZdZsmcxxbController {
    @Autowired
    private ZdZsmcxxbService zdZsmcxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdzsmcxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdZsmcxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdzsmcxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdZsmcxxbEntity zdZsmcxxb = zdZsmcxxbService.selectById(id);

        return R.ok().put("zdZsmcxxb", zdZsmcxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdzsmcxxb:save")
    public R save(@RequestBody ZdZsmcxxbEntity zdZsmcxxb){
        zdZsmcxxb.setCreateUserId(getUserId());
        zdZsmcxxb.setCreateTime(new Date());

			zdZsmcxxbService.insert(zdZsmcxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdzsmcxxb:update")
    public R update(@RequestBody ZdZsmcxxbEntity zdZsmcxxb){

			zdZsmcxxbService.updateById(zdZsmcxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdzsmcxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdZsmcxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}

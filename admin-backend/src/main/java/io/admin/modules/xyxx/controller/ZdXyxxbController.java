package io.admin.modules.xyxx.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.xyxx.entity.ZdXyxxbEntity;
import io.admin.modules.xyxx.service.ZdXyxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-14 20:07:18
 */
@RestController
@RequestMapping("xyxx/zdxyxxb")
public class ZdXyxxbController {
    @Autowired
    private ZdXyxxbService zdXyxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("xyxx:zdxyxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdXyxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("xyxx:zdxyxxb:info")
    public R info(@PathVariable("id") Integer id){
			ZdXyxxbEntity zdXyxxb = zdXyxxbService.selectById(id);

        return R.ok().put("zdXyxxb", zdXyxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("xyxx:zdxyxxb:save")
    public R save(@RequestBody ZdXyxxbEntity zdXyxxb){
			zdXyxxbService.insert(zdXyxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("xyxx:zdxyxxb:update")
    public R update(@RequestBody ZdXyxxbEntity zdXyxxb){
			zdXyxxbService.updateById(zdXyxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("xyxx:zdxyxxb:delete")
    public R delete(@RequestBody Integer[] ids){
			zdXyxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/selectAll")
    @RequiresPermissions("xyxx:zdxyxxb:selectAll")
    public R selectAll(){
        List<ZdXyxxbEntity> list = zdXyxxbService.selectList(new EntityWrapper<ZdXyxxbEntity>()
        );
        ArrayList arrays = new ArrayList();
        for (int i=0;i<list.size();i++){
            Map map = new HashMap();
            map.put("value",list.get(i).getXydm());
            map.put("label",list.get(i).getXymc());
            arrays.add(map);
        }
        return R.ok().put("xymclist",arrays);
    }

}

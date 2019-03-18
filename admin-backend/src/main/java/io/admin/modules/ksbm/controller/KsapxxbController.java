package io.admin.modules.ksbm.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.ksbm.entity.KsapxxbEntity;
import io.admin.modules.ksbm.service.KsapxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-17 17:24:19
 */
@RestController
@RequestMapping("ksbm/ksapxxb")
public class KsapxxbController {
    @Autowired
    private KsapxxbService ksapxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ksbm:ksapxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ksapxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ksbm:ksapxxb:info")
    public R info(@PathVariable("id") Long id){
			KsapxxbEntity ksapxxb = ksapxxbService.selectById(id);

        return R.ok().put("ksapxxb", ksapxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ksbm:ksapxxb:save")
    public R save(@RequestBody KsapxxbEntity ksapxxb){
			ksapxxbService.insert(ksapxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ksbm:ksapxxb:update")
    public R update(@RequestBody KsapxxbEntity ksapxxb){
			ksapxxbService.updateById(ksapxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ksbm:ksapxxb:delete")
    public R delete(@RequestBody Long[] ids){
			ksapxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/select")
    @RequiresPermissions("ksbm:ksapxxb:select")
    public R select(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currdatestr = sdf.format(d);

        List<KsapxxbEntity> list = ksapxxbService.selectList(new EntityWrapper<KsapxxbEntity>().
                le("qsrq",currdatestr).ge("jsrq",currdatestr)
        );
        ArrayList arrays = new ArrayList();
        for (int i=0;i<list.size();i++){
            Map map = new HashMap();
            map.put("value",list.get(i).getId());
            map.put("label",list.get(i).getName());
            map.put("ksnd",list.get(i).getKsnd());
            arrays.add(map);
        }
        return R.ok().put("ksxmmclist",arrays);
    }

}

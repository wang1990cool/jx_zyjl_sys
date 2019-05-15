package io.admin.modules.bjxx.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.bjxx.entity.ZdBjxxbEntity;
import io.admin.modules.bjxx.service.ZdBjxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-15 01:08:13
 */
@RestController
@RequestMapping("bjxx/zdbjxxb")
public class ZdBjxxbController {
    @Autowired
    private ZdBjxxbService zdBjxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bjxx:zdbjxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdBjxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bjxx:zdbjxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdBjxxbEntity zdBjxxb = zdBjxxbService.selectById(id);

        return R.ok().put("zdBjxxb", zdBjxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bjxx:zdbjxxb:save")
    public R save(@RequestBody ZdBjxxbEntity zdBjxxb){
			zdBjxxbService.insert(zdBjxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bjxx:zdbjxxb:update")
    public R update(@RequestBody ZdBjxxbEntity zdBjxxb){
			zdBjxxbService.updateById(zdBjxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bjxx:zdbjxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdBjxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/select")
    @RequiresPermissions("bjxx:zdbjxxb:select")
    public R select(@RequestParam Map<String, Object> params){
        String zydm=(String)params.get("zybm");
        String nj=(String)params.get("sznj");
        List<ZdBjxxbEntity> list = zdBjxxbService.selectList(new EntityWrapper<ZdBjxxbEntity>().
                like(StringUtils.isNotBlank(zydm), "zydm", zydm).
                like(StringUtils.isNotBlank(nj), "nj", nj)
        );
        ArrayList arrays = new ArrayList();
        for (int i=0;i<list.size();i++){
            Map map = new HashMap();
            map.put("value",list.get(i).getBjdm());
            map.put("label",list.get(i).getBjmc());
            arrays.add(map);
        }
        return R.ok().put("bjmclist",arrays);
    }

}

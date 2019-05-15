package io.admin.modules.zyxx.controller;

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

import io.admin.modules.zyxx.entity.ZdZyxxbEntity;
import io.admin.modules.zyxx.service.ZdZyxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-15 00:10:55
 */
@RestController
@RequestMapping("zyxx/zdzyxxb")
public class ZdZyxxbController {
    @Autowired
    private ZdZyxxbService zdZyxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("zyxx:zdzyxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdZyxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("zyxx:zdzyxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdZyxxbEntity zdZyxxb = zdZyxxbService.selectById(id);

        return R.ok().put("zdZyxxb", zdZyxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("zyxx:zdzyxxb:save")
    public R save(@RequestBody ZdZyxxbEntity zdZyxxb){
			zdZyxxbService.insert(zdZyxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("zyxx:zdzyxxb:update")
    public R update(@RequestBody ZdZyxxbEntity zdZyxxb){
			zdZyxxbService.updateById(zdZyxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("zyxx:zdzyxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdZyxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/select")
    @RequiresPermissions("zyxx:zdzyxxb:select")
    public R select(@RequestParam Map<String, Object> params){
        String xydm=(String)params.get("xybm");
        List<ZdZyxxbEntity> list = zdZyxxbService.selectList(new EntityWrapper<ZdZyxxbEntity>().
                like(StringUtils.isNotBlank(xydm), "xydm", xydm)
        );
        ArrayList arrays = new ArrayList();
        for (int i=0;i<list.size();i++){
            Map map = new HashMap();
            map.put("value",list.get(i).getZydm());
            map.put("label",list.get(i).getZymc());
            arrays.add(map);
        }
        return R.ok().put("zymclist",arrays);
    }

}

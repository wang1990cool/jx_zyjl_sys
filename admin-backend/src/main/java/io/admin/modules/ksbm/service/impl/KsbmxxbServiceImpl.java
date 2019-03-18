package io.admin.modules.ksbm.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.ksbm.dao.KsbmxxbDao;
import io.admin.modules.ksbm.entity.KsbmxxbEntity;
import io.admin.modules.ksbm.service.KsbmxxbService;


@Service("ksbmxxbService")
public class KsbmxxbServiceImpl extends ServiceImpl<KsbmxxbDao, KsbmxxbEntity> implements KsbmxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String xsxh = (String)params.get("xsxh");
        String ksnd=(String)params.get("ksnd");
        String szbj=(String)params.get("szbj");

        Page<KsbmxxbEntity> page = this.selectPage(
                new Query<KsbmxxbEntity>(params).getPage(),
                new EntityWrapper<KsbmxxbEntity>().
                        like(StringUtils.isNotBlank(xsxh), "xsxh", xsxh).
                        like(StringUtils.isNotBlank(ksnd), "ksnd", ksnd).
                        like(StringUtils.isNotBlank(szbj), "szbj", szbj)
        );

        return new PageUtils(page);
    }

}

package io.admin.modules.ksbm.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.ksbm.dao.KsbmViewDao;
import io.admin.modules.ksbm.entity.KsbmViewEntity;
import io.admin.modules.ksbm.service.KsbmViewService;


@Service("ksbmViewService")
public class KsbmViewServiceImpl extends ServiceImpl<KsbmViewDao, KsbmViewEntity> implements KsbmViewService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String ksnd=(String)params.get("ksnd");
        String szxb=(String)params.get("szxb");
        String szzy=(String)params.get("szzy");
        String nj=(String)params.get("sznj");
        String szbj=(String)params.get("szbj");

        Page<KsbmViewEntity> page = this.selectPage(
                new Query<KsbmViewEntity>(params).getPage(),
                new EntityWrapper<KsbmViewEntity>().
                        like(StringUtils.isNotBlank(ksnd), "ksnd", ksnd).
                        like(StringUtils.isNotBlank(szxb), "xydm", szxb).
                        like(StringUtils.isNotBlank(szzy), "zydm", szzy).
                        like(StringUtils.isNotBlank(nj), "nj", nj).
                        like(StringUtils.isNotBlank(szbj), "bjdm", szbj)
        );

        return new PageUtils(page);
    }

}

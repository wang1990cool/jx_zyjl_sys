package io.admin.modules.ksbm.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.ksbm.dao.KsapxxbDao;
import io.admin.modules.ksbm.entity.KsapxxbEntity;
import io.admin.modules.ksbm.service.KsapxxbService;


@Service("ksapxxbService")
public class KsapxxbServiceImpl extends ServiceImpl<KsapxxbDao, KsapxxbEntity> implements KsapxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KsapxxbEntity> page = this.selectPage(
                new Query<KsapxxbEntity>(params).getPage(),
                new EntityWrapper<KsapxxbEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<KsapxxbEntity> selectList() {
        return baseMapper.selectList();
    }

}

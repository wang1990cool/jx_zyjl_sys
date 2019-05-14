package io.admin.modules.xyxx.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.xyxx.dao.ZdXyxxbDao;
import io.admin.modules.xyxx.entity.ZdXyxxbEntity;
import io.admin.modules.xyxx.service.ZdXyxxbService;


@Service("zdXyxxbService")
public class ZdXyxxbServiceImpl extends ServiceImpl<ZdXyxxbDao, ZdXyxxbEntity> implements ZdXyxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdXyxxbEntity> page = this.selectPage(
                new Query<ZdXyxxbEntity>(params).getPage(),
                new EntityWrapper<ZdXyxxbEntity>()
        );

        return new PageUtils(page);
    }

}

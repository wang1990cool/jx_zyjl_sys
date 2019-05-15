package io.admin.modules.zyxx.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.zyxx.dao.ZdZyxxbDao;
import io.admin.modules.zyxx.entity.ZdZyxxbEntity;
import io.admin.modules.zyxx.service.ZdZyxxbService;


@Service("zdZyxxbService")
public class ZdZyxxbServiceImpl extends ServiceImpl<ZdZyxxbDao, ZdZyxxbEntity> implements ZdZyxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdZyxxbEntity> page = this.selectPage(
                new Query<ZdZyxxbEntity>(params).getPage(),
                new EntityWrapper<ZdZyxxbEntity>()
        );

        return new PageUtils(page);
    }

}

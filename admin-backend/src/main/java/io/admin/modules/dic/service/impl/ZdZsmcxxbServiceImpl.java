package io.admin.modules.dic.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.dic.dao.ZdZsmcxxbDao;
import io.admin.modules.dic.entity.ZdZsmcxxbEntity;
import io.admin.modules.dic.service.ZdZsmcxxbService;


@Service("zdZsmcxxbService")
public class ZdZsmcxxbServiceImpl extends ServiceImpl<ZdZsmcxxbDao, ZdZsmcxxbEntity> implements ZdZsmcxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdZsmcxxbEntity> page = this.selectPage(
                new Query<ZdZsmcxxbEntity>(params).getPage(),
                new EntityWrapper<ZdZsmcxxbEntity>()
        );

        return new PageUtils(page);
    }

}

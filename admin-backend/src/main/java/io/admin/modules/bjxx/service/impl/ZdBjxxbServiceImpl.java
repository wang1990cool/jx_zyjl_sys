package io.admin.modules.bjxx.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.bjxx.dao.ZdBjxxbDao;
import io.admin.modules.bjxx.entity.ZdBjxxbEntity;
import io.admin.modules.bjxx.service.ZdBjxxbService;


@Service("zdBjxxbService")
public class ZdBjxxbServiceImpl extends ServiceImpl<ZdBjxxbDao, ZdBjxxbEntity> implements ZdBjxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdBjxxbEntity> page = this.selectPage(
                new Query<ZdBjxxbEntity>(params).getPage(),
                new EntityWrapper<ZdBjxxbEntity>()
        );

        return new PageUtils(page);
    }

}

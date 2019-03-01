package io.admin.modules.cjgl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.cjgl.dao.XsCjxxbDao;
import io.admin.modules.cjgl.entity.XsCjxxbEntity;
import io.admin.modules.cjgl.service.XsCjxxbService;


@Service("xsCjxxbService")
public class XsCjxxbServiceImpl extends ServiceImpl<XsCjxxbDao, XsCjxxbEntity> implements XsCjxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XsCjxxbEntity> page = this.selectPage(
                new Query<XsCjxxbEntity>(params).getPage(),
                new EntityWrapper<XsCjxxbEntity>()
        );

        return new PageUtils(page);
    }

}

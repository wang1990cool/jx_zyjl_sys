package io.admin.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.sys.dao.SysDictionaryDao;
import io.admin.modules.sys.entity.SysDictionaryEntity;
import io.admin.modules.sys.service.SysDictionaryService;


@Service("sysDictionaryService")
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryDao, SysDictionaryEntity> implements SysDictionaryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String code = (String)params.get("code");
        String name = (String)params.get("name");
        String order = (String)params.get("order");

        Page<SysDictionaryEntity> page = this.selectPage(
                new Query<SysDictionaryEntity>(params).getPage(),
                new EntityWrapper<SysDictionaryEntity>().
                        like(StringUtils.isNotBlank(code), "code", code).
                        like(StringUtils.isNotBlank(name), "name", name).
                        orderBy(order)
        );

        return new PageUtils(page);
    }

}

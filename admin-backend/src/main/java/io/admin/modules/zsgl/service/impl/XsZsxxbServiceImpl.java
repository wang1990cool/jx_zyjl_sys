package io.admin.modules.zsgl.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.zsgl.dao.XsZsxxbDao;
import io.admin.modules.zsgl.entity.XsZsxxbEntity;
import io.admin.modules.zsgl.service.XsZsxxbService;


@Service("xsZsxxbService")
public class XsZsxxbServiceImpl extends ServiceImpl<XsZsxxbDao, XsZsxxbEntity> implements XsZsxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String)params.get("xsxh");
        String bjmc=(String)params.get("bjmc");
        Integer ztm=(Integer)params.get("ztm");
        Page<XsZsxxbEntity> page = this.selectPage(
                new Query<XsZsxxbEntity>(params).getPage(),
                new EntityWrapper<XsZsxxbEntity>().
                like(StringUtils.isNotBlank(username), "xsxh", username).
                like(StringUtils.isNotBlank(bjmc), "szbj", bjmc).
                eq(  ztm != null && ztm != 0 ,"ztm",ztm).
                orderBy("createTime",false)

        );

        return new PageUtils(page);
    }

}

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
        String hzrq=(String)params.get("sznd");
        String szbj=(String)params.get("szbj");
        String ztmstr=(String)params.get("ztm");
        Integer ztm=0;
        if(!ztmstr.equals("") && ztmstr != null){
            ztm = Integer.parseInt(ztmstr);
        }

        Page<XsZsxxbEntity> page = this.selectPage(
                new Query<XsZsxxbEntity>(params).getPage(),
                new EntityWrapper<XsZsxxbEntity>().
                like(StringUtils.isNotBlank(username), "xsxh", username).
                like(StringUtils.isNotBlank(hzrq), "hzrq", hzrq).
                like(StringUtils.isNotBlank(szbj), "szbj", szbj).
                eq(   ztm > 0 ,"ztm",ztm)

        );

        return new PageUtils(page);
    }

}

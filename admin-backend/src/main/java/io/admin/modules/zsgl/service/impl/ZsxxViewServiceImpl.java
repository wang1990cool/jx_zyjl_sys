package io.admin.modules.zsgl.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.zsgl.dao.ZsxxViewDao;
import io.admin.modules.zsgl.entity.ZsxxViewEntity;
import io.admin.modules.zsgl.service.ZsxxViewService;


@Service("zsxxViewService")
public class ZsxxViewServiceImpl extends ServiceImpl<ZsxxViewDao, ZsxxViewEntity> implements ZsxxViewService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        String username = (String)params.get("xsxh");
        String hzrq=(String)params.get("sznd");
        String szxb=(String)params.get("szxb");
        String szzy=(String)params.get("szzy");
        String nj=(String)params.get("sznj");
        String szbj=(String)params.get("szbj");
        String ztmstr=(String)params.get("ztm");
        Integer ztm=0;
        if(ztmstr != null && !ztmstr.equals("")){
            ztm = Integer.parseInt(ztmstr);
        }

        Page<ZsxxViewEntity> page = this.selectPage(
                new Query<ZsxxViewEntity>(params).getPage(),
                new EntityWrapper<ZsxxViewEntity>().
                        like(StringUtils.isNotBlank(hzrq), "hzrq", hzrq).
                        like(StringUtils.isNotBlank(szxb), "xydm", szxb).
                        like(StringUtils.isNotBlank(szzy), "zydm", szzy).
                        like(StringUtils.isNotBlank(nj), "nj", nj).
                        like(StringUtils.isNotBlank(szbj), "bjdm", szbj).
                        eq(   ztm > 0 ,"ztm",ztm)
        );

        return new PageUtils(page);
    }

}

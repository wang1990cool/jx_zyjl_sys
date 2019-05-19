package io.admin.modules.train.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.train.dao.TrainProjectAcademyAuditDao;
import io.admin.modules.train.dao.TrainProjectAuditDao;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.service.TrainProjectAcademyAuditService;
import io.admin.modules.train.service.TrainProjectAuditService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("trainProjectAcademyAuditService")
public class TrainProjectAcademyAuditServiceImpl extends ServiceImpl<TrainProjectAcademyAuditDao, TrainProjectEntity>
        implements TrainProjectAcademyAuditService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String projectId = (String)params.get("projectId");
        String projectName = (String)params.get("projectName");
        String order = (String)params.get("order");
        String[] statusCode = (String[])params.get("statusCode");

        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        String unit = sysUserEntity.getUnit();


        Page<TrainProjectEntity> page = this.selectPage(
                new Query<TrainProjectEntity>(params).getPage(),
                new EntityWrapper<TrainProjectEntity>().
                        like(StringUtils.isNotBlank(projectId), "project_id", projectId).
                        like(StringUtils.isNotBlank(projectName), "project_name", projectName).
                        in("status_code", statusCode).
                        eq(StringUtils.isNotBlank(unit), "applicant_dept", unit).
                        orderBy(order)

        );

        return new PageUtils(page);
    }

}

package io.admin.modules.train.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;
import io.admin.modules.train.dao.TrainProjectAuditDao;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.service.TrainProjectAuditService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("trainProjectAuditService")
public class TrainProjectAuditServiceImpl extends ServiceImpl<TrainProjectAuditDao, TrainProjectEntity>
        implements TrainProjectAuditService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String projectId = (String)params.get("projectId");
        String projectName = (String)params.get("projectName");
        String order = (String)params.get("order");



        Page<TrainProjectEntity> page = this.selectPage(
                new Query<TrainProjectEntity>(params).getPage(),
                new EntityWrapper<TrainProjectEntity>().
                        in("status_code", new String[]{"2"}).
                        like(StringUtils.isNotBlank(projectId), "project_id", projectId).
                        like(StringUtils.isNotBlank(projectName), "project_name", projectName).
                        orderBy(order)

        );

        return new PageUtils(page);
    }

}

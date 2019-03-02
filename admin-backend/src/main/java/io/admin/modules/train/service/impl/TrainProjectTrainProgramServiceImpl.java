package io.admin.modules.train.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;
import io.admin.modules.train.dao.TrainProjectAuditDao;
import io.admin.modules.train.dao.TrainProjectTrainProgramDao;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.entity.TrainProjectTrainProgramEntity;
import io.admin.modules.train.service.TrainProjectAuditService;
import io.admin.modules.train.service.TrainProjectTrainProgramService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("trainProjectTrainProgramService")
public class TrainProjectTrainProgramServiceImpl extends ServiceImpl<TrainProjectTrainProgramDao, TrainProjectTrainProgramEntity>
        implements TrainProjectTrainProgramService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String projectId = (String)params.get("projectId");

        Page<TrainProjectTrainProgramEntity> page = this.selectPage(
                new Query<TrainProjectTrainProgramEntity>(params).getPage(),
                new EntityWrapper<TrainProjectTrainProgramEntity>().
                        eq("project_id", projectId).orderBy("id")
        );

        return new PageUtils(page);
    }

    @Override
    public void createWorkload(String projectId) {
        baseMapper.createWorkload(projectId);
    }
}

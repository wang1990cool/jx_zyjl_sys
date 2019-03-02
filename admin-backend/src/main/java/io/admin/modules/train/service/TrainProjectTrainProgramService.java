package io.admin.modules.train.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.entity.TrainProjectTrainProgramEntity;

import java.util.Map;

public interface TrainProjectTrainProgramService extends IService<TrainProjectTrainProgramEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void createWorkload(String projectId);
}


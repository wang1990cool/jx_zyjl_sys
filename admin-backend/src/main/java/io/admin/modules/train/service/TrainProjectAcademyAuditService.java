package io.admin.modules.train.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.train.entity.TrainProjectEntity;

import java.util.Map;

public interface TrainProjectAcademyAuditService extends IService<TrainProjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


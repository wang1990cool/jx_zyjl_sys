package io.admin.modules.train.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.train.entity.TrainScoreEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-03-10 19:57:02
 */
public interface TrainScoreService extends IService<TrainScoreEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


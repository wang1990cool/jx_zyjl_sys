package io.admin.modules.train.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.entity.TrainProjectTrainProgramEntity;
import io.admin.modules.train.entity.TrainProjectWorkloadEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TrainProjectWorkloadDao extends BaseMapper<TrainProjectWorkloadEntity> {
	
}


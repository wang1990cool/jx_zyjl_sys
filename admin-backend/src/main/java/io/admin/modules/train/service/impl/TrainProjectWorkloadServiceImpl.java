package io.admin.modules.train.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;
import io.admin.modules.train.dao.TrainProjectDao;
import io.admin.modules.train.dao.TrainProjectWorkloadDao;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.entity.TrainProjectWorkloadEntity;
import io.admin.modules.train.service.TrainProjectService;
import io.admin.modules.train.service.TrainProjectWorkloadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("trainProjectWorkloadService")
public class TrainProjectWorkloadServiceImpl extends ServiceImpl<TrainProjectWorkloadDao, TrainProjectWorkloadEntity> implements TrainProjectWorkloadService {



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String projectId = (String)params.get("projectId");
        String projectName = (String)params.get("projectName");
        String order = (String)params.get("order");

        Page<TrainProjectWorkloadEntity> page = this.selectPage(
                new Query<TrainProjectWorkloadEntity>(params).getPage(),
                new EntityWrapper<TrainProjectWorkloadEntity>().
                        like(StringUtils.isNotBlank(projectId), "project_id", projectId).
                        orderBy(order)
        );

        return new PageUtils(page);
    }



}

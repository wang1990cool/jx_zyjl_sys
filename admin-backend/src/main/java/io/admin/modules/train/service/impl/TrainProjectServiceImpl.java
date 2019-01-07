package io.admin.modules.train.service.impl;

import io.admin.modules.train.dao.TrainProjectDao;
import io.admin.modules.train.entity.TrainProjectEntity;
import io.admin.modules.train.service.TrainProjectService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;



@Service("trainProjectService")
public class TrainProjectServiceImpl extends ServiceImpl<TrainProjectDao, TrainProjectEntity> implements TrainProjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TrainProjectEntity> page = this.selectPage(
                new Query<TrainProjectEntity>(params).getPage(),
                new EntityWrapper<TrainProjectEntity>()
        );

        return new PageUtils(page);
    }

}

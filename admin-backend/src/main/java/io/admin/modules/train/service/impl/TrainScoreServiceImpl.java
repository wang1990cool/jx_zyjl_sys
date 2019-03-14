package io.admin.modules.train.service.impl;

import io.admin.modules.train.dao.TrainScoreDao;
import io.admin.modules.train.entity.TrainScoreEntity;
import io.admin.modules.train.service.TrainScoreService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;


@Service("trainScoreService")
public class TrainScoreServiceImpl extends ServiceImpl<TrainScoreDao, TrainScoreEntity> implements TrainScoreService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String year = (String)params.get("year");
        String studentNum = (String)params.get("studentNum");
        String studentName = (String)params.get("studentName");
        String courseName = (String)params.get("courseName");
        String order = (String)params.get("order");

        Page<TrainScoreEntity> page = this.selectPage(
                new Query<TrainScoreEntity>(params).getPage(),
                new EntityWrapper<TrainScoreEntity>().
                        like(StringUtils.isNotBlank(year), "year", year).
                        like(StringUtils.isNotBlank(studentNum), "student_num", studentNum).
                        like(StringUtils.isNotBlank(studentName), "student_name", studentName).
                        like(StringUtils.isNotBlank(courseName), "course_name", courseName).
                        orderBy(order)
        );

        return new PageUtils(page);
    }

}

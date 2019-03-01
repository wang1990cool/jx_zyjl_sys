package io.admin.modules.zsgl.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.zsgl.entity.XsZsxxbEntity;

import java.util.Map;

/**
 * 学生证书信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-27 10:14:57
 */
public interface XsZsxxbService extends IService<XsZsxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


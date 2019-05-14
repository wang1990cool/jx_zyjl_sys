package io.admin.modules.zsgl.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.zsgl.entity.ZsxxViewEntity;

import java.util.Map;

/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-14 21:51:59
 */
public interface ZsxxViewService extends IService<ZsxxViewEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package io.admin.modules.ksbm.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.ksbm.entity.KsbmViewEntity;

import java.util.Map;

/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-15 11:21:27
 */
public interface KsbmViewService extends IService<KsbmViewEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


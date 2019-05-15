package io.admin.modules.xyxx.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.xyxx.entity.ZdXyxxbEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-14 20:07:18
 */
public interface ZdXyxxbService extends IService<ZdXyxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


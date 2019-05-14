package io.admin.modules.bjxx.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.bjxx.entity.ZdBjxxbEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-15 01:08:13
 */
public interface ZdBjxxbService extends IService<ZdBjxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


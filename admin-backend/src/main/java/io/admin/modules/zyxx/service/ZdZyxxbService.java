package io.admin.modules.zyxx.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.zyxx.entity.ZdZyxxbEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-15 00:10:55
 */
public interface ZdZyxxbService extends IService<ZdZyxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


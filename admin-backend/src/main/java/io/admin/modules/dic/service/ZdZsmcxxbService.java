package io.admin.modules.dic.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.entity.ZdZsmcxxbEntity;

import java.util.Map;

/**
 * 证书名称信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-25 21:49:19
 */
public interface ZdZsmcxxbService extends IService<ZdZsmcxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


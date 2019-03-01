package io.admin.modules.cjgl.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.cjgl.entity.XsCjxxbEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-02-25 15:57:08
 */
public interface XsCjxxbService extends IService<XsCjxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


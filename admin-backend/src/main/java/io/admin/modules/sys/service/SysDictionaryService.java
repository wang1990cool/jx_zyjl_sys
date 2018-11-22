package io.admin.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.sys.entity.SysDictionaryEntity;

import java.util.Map;

/**
 * 数据字典
 *
 * @author Wangcaner
 * @date 2018-07-04 22:26:17
 */
public interface SysDictionaryService extends IService<SysDictionaryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


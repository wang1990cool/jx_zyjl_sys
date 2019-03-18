package io.admin.modules.ksbm.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.ksbm.entity.KsapxxbEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-17 17:24:19
 */
public interface KsapxxbService extends IService<KsapxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<KsapxxbEntity> selectList();
}


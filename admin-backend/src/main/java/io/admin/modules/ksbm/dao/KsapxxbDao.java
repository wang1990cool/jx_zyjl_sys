package io.admin.modules.ksbm.dao;

import io.admin.modules.ksbm.entity.KsapxxbEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-17 17:24:19
 */
@Mapper
public interface KsapxxbDao extends BaseMapper<KsapxxbEntity> {
    List<KsapxxbEntity> selectList();
}

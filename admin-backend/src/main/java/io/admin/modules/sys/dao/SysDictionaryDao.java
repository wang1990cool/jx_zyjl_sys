package io.admin.modules.sys.dao;

import io.admin.modules.sys.entity.SysDictionaryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典
 * 
 * @author Wangcaner
 * @date 2018-07-04 22:26:17
 */
@Mapper
public interface SysDictionaryDao extends BaseMapper<SysDictionaryEntity> {
	
}

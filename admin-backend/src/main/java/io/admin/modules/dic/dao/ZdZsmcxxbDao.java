package io.admin.modules.dic.dao;

import io.admin.modules.dic.entity.ZdZsmcxxbEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 证书名称信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-25 21:49:19
 */
@Mapper
public interface ZdZsmcxxbDao extends BaseMapper<ZdZsmcxxbEntity> {
    List<ZdZsmcxxbEntity> selectList();
}

package io.admin.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 * 
 * @author Wangcaner
 * @date 2018-07-04 22:26:17
 */
@Data
@TableName("sys_dictionary")
public class SysDictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	/**
	 * 编码
	 */
	private Integer code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 是否有效
	 */
	private Integer active;
}

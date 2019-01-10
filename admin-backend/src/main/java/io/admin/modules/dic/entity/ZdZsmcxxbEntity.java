package io.admin.modules.dic.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 证书名称信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-25 21:49:19
 */
@TableName("zd_zsmcxxb")
public class ZdZsmcxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 证件名称
	 */
	private String name;
	/**
	 * 0：禁用   1：可用
	 */
	private Integer status;
	/**
	 * 创建者id
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：证件名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：证件名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：0：禁用   1：可用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0：禁用   1：可用
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建者id
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建者id
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}

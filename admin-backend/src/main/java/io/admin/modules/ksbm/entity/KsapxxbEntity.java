package io.admin.modules.ksbm.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-17 17:24:19
 */
@TableName("ksapxxb")
public class KsapxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String ksnd;
	/**
	 * 
	 */
	private String qsrq;
	/**
	 * 
	 */
	private String jsrq;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Long createUserId;
	/**
	 * 
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
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setKsnd(String ksnd) {
		this.ksnd = ksnd;
	}
	/**
	 * 获取：
	 */
	public String getKsnd() {
		return ksnd;
	}
	/**
	 * 设置：
	 */
	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}
	/**
	 * 获取：
	 */
	public String getQsrq() {
		return qsrq;
	}
	/**
	 * 设置：
	 */
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	/**
	 * 获取：
	 */
	public String getJsrq() {
		return jsrq;
	}
	/**
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}

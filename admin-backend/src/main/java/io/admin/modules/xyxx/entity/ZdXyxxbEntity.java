package io.admin.modules.xyxx.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-14 20:07:18
 */
@TableName("zd_xyxxb")
public class ZdXyxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 系院代码
	 */
	private String xydm;
	/**
	 * 系院名称
	 */
	private String xymc;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：系院代码
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * 获取：系院代码
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * 设置：系院名称
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * 获取：系院名称
	 */
	public String getXymc() {
		return xymc;
	}
}

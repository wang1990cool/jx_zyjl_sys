package io.admin.modules.zyxx.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-15 00:10:55
 */
@TableName("zd_zyxxb")
public class ZdZyxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 专业代码
	 */
	private String zydm;
	/**
	 * 专业名称
	 */
	private String zymc;
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
	 * 设置：专业代码
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * 获取：专业代码
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * 设置：专业名称
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * 获取：专业名称
	 */
	public String getZymc() {
		return zymc;
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

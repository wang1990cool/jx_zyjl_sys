package io.admin.modules.bjxx.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-15 01:08:13
 */
@TableName("zd_bjxxb")
public class ZdBjxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String bjdm;
	/**
	 * 
	 */
	private String bjmc;
	/**
	 * 
	 */
	private String xydm;
	/**
	 * 
	 */
	private String xymc;
	/**
	 * 
	 */
	private String zydm;
	/**
	 * 
	 */
	private String zymc;
	/**
	 * 
	 */
	private String zyfx;
	/**
	 * 
	 */
	private String nj;

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
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * 获取：
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * 设置：
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * 获取：
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * 设置：
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * 获取：
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * 设置：
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * 获取：
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * 设置：
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * 获取：
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * 设置：
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * 获取：
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * 设置：
	 */
	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
	}
	/**
	 * 获取：
	 */
	public String getZyfx() {
		return zyfx;
	}
	/**
	 * 设置：
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * 获取：
	 */
	public String getNj() {
		return nj;
	}
}

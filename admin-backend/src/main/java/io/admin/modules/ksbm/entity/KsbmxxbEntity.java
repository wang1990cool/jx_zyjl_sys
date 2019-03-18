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
@TableName("ksbmxxb")
public class KsbmxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String ksnd;
	/**
	 * 
	 */
	private Long ksxmid;
	/**
	 * 
	 */
	private String ksxmmc;
	/**
	 * 
	 */
	private String xsxh;
	/**
	 * 
	 */
	private String xsxm;
	/**
	 * 
	 */
	private String sex;
	/**
	 * 
	 */
	private String birth;
	/**
	 * 
	 */
	private String mz;
	/**
	 * 
	 */
	private String szbj;
	/**
	 * 
	 */
	private String sfzh;
	/**
	 * 
	 */
	private String telephone;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String zppath;
	/**
	 * 
	 */
	private byte[] zszp;
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
	public void setKsxmid(Long ksxmid) {
		this.ksxmid = ksxmid;
	}
	/**
	 * 获取：
	 */
	public Long getKsxmid() {
		return ksxmid;
	}
	/**
	 * 设置：
	 */
	public void setKsxmmc(String ksxmmc) {
		this.ksxmmc = ksxmmc;
	}
	/**
	 * 获取：
	 */
	public String getKsxmmc() {
		return ksxmmc;
	}
	/**
	 * 设置：
	 */
	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	/**
	 * 获取：
	 */
	public String getXsxh() {
		return xsxh;
	}
	/**
	 * 设置：
	 */
	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}
	/**
	 * 获取：
	 */
	public String getXsxm() {
		return xsxm;
	}
	/**
	 * 设置：
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}
	/**
	 * 获取：
	 */
	public String getBirth() {
		return birth;
	}
	/**
	 * 设置：
	 */
	public void setMz(String mz) {
		this.mz = mz;
	}
	/**
	 * 获取：
	 */
	public String getMz() {
		return mz;
	}
	/**
	 * 设置：
	 */
	public void setSzbj(String szbj) {
		this.szbj = szbj;
	}
	/**
	 * 获取：
	 */
	public String getSzbj() {
		return szbj;
	}
	/**
	 * 设置：
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	/**
	 * 获取：
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * 设置：
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：
	 */
	public void setZppath(String zppath) {
		this.zppath = zppath;
	}
	/**
	 * 获取：
	 */
	public String getZppath() {
		return zppath;
	}
	/**
	 * 设置：
	 */
	public void setZszp(byte[] zszp) {
		this.zszp = zszp;
	}
	/**
	 * 获取：
	 */
	public byte[] getZszp() {
		return zszp;
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

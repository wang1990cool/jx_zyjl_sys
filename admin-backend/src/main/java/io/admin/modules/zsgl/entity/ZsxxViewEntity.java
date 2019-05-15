package io.admin.modules.zsgl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * VIEW
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-14 21:51:59
 */
@TableName("zsxx_view")
public class ZsxxViewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 所在班级
	 */
	@TableId
	private String szbj;
	/**
	 * 
	 */
	private Long id;
	/**
	 * 学号
	 */
	private String xsxh;
	/**
	 * 姓名
	 */
	private String xsxm;
	/**
	 * 身份证号
	 */
	private String sfzh;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 证书名称
	 */
	private String zsmc;
	/**
	 * 获证日期
	 */
	private String hzrq;
	/**
	 * 证书照片
	 */
	private byte[] zszp;
	/**
	 * 班级审核意见
	 */
	private String bjshyj;
	/**
	 * 状态码
	 */
	private Integer ztm;
	/**
	 * 状态
	 */
	private String zt;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 照片路径
	 */
	private String zppath;
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
	 * 设置：所在班级
	 */
	public void setSzbj(String szbj) {
		this.szbj = szbj;
	}
	/**
	 * 获取：所在班级
	 */
	public String getSzbj() {
		return szbj;
	}
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
	 * 设置：学号
	 */
	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	/**
	 * 获取：学号
	 */
	public String getXsxh() {
		return xsxh;
	}
	/**
	 * 设置：姓名
	 */
	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}
	/**
	 * 获取：姓名
	 */
	public String getXsxm() {
		return xsxm;
	}
	/**
	 * 设置：身份证号
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	/**
	 * 获取：身份证号
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * 设置：电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：电话
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：证书名称
	 */
	public void setZsmc(String zsmc) {
		this.zsmc = zsmc;
	}
	/**
	 * 获取：证书名称
	 */
	public String getZsmc() {
		return zsmc;
	}
	/**
	 * 设置：获证日期
	 */
	public void setHzrq(String hzrq) {
		this.hzrq = hzrq;
	}
	/**
	 * 获取：获证日期
	 */
	public String getHzrq() {
		return hzrq;
	}
	/**
	 * 设置：证书照片
	 */
	public void setZszp(byte[] zszp) {
		this.zszp = zszp;
	}
	/**
	 * 获取：证书照片
	 */
	public byte[] getZszp() {
		return zszp;
	}
	/**
	 * 设置：班级审核意见
	 */
	public void setBjshyj(String bjshyj) {
		this.bjshyj = bjshyj;
	}
	/**
	 * 获取：班级审核意见
	 */
	public String getBjshyj() {
		return bjshyj;
	}
	/**
	 * 设置：状态码
	 */
	public void setZtm(Integer ztm) {
		this.ztm = ztm;
	}
	/**
	 * 获取：状态码
	 */
	public Integer getZtm() {
		return ztm;
	}
	/**
	 * 设置：状态
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}
	/**
	 * 获取：状态
	 */
	public String getZt() {
		return zt;
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
	/**
	 * 设置：照片路径
	 */
	public void setZppath(String zppath) {
		this.zppath = zppath;
	}
	/**
	 * 获取：照片路径
	 */
	public String getZppath() {
		return zppath;
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
}

package io.admin.modules.zsgl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生证书信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-27 10:14:57
 */
@TableName("xs_zsxxb")
public class XsZsxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 学号
	 */
	@Excel(name="学号",orderNum = "1")
	private String xsxh;
	/**
	 * 姓名
	 */
	@Excel(name="姓名",orderNum = "2")
	private String xsxm;
	/**
	 * 所在班级
	 */
	@Excel(name="所在班级",orderNum = "3")
	private String szbj;
	/**
	 * 身份证号
	 */
	@Excel(name="身份证号",orderNum = "4")
	private String sfzh;
	/**
	 * 电话
	 */
	@Excel(name="联系电话",orderNum = "5")
	private String telephone;
	/**
	 * 证书名称
	 */
	@Excel(name="证书名称",orderNum = "6")
	private String zsmc;
	/**
	 * 获证日期
	 */
	@Excel(name="获证日期",orderNum = "7")
	private String hzrq;
	/**
	 * 证书照片
	 */
	private byte[] zszp;

	public String getZppath() {
		return zppath;
	}

	public void setZppath(String zppath) {
		this.zppath = zppath;
	}

	/**
	 * 证书照片存放名称
	 */
	private String zppath;
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
}

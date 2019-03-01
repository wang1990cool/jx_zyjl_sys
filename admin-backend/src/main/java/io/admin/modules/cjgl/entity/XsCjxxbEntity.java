package io.admin.modules.cjgl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-02-25 15:57:08
 */
@TableName("xs_cjxxb")
public class XsCjxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 成绩年份
	 */
	private String nf;
	/**
	 * 学生学号
	 */
	private String xsxh;
	/**
	 * 学生姓名
	 */
	private String xsxm;
	/**
	 * 身份证号
	 */
	private String sfzh;
	/**
	 * 科目名称
	 */
	private String kmmc;
	/**
	 * 分数
	 */
	private Float score;
	/**
	 * 状态(0:禁用,1:有效)
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
	 * 设置：成绩年份
	 */
	public void setNf(String nf) {
		this.nf = nf;
	}
	/**
	 * 获取：成绩年份
	 */
	public String getNf() {
		return nf;
	}
	/**
	 * 设置：学生学号
	 */
	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	/**
	 * 获取：学生学号
	 */
	public String getXsxh() {
		return xsxh;
	}
	/**
	 * 设置：学生姓名
	 */
	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}
	/**
	 * 获取：学生姓名
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
	 * 设置：科目名称
	 */
	public void setKmmc(String kmmc) {
		this.kmmc = kmmc;
	}
	/**
	 * 获取：科目名称
	 */
	public String getKmmc() {
		return kmmc;
	}
	/**
	 * 设置：分数
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	/**
	 * 获取：分数
	 */
	public Float getScore() {
		return score;
	}
	/**
	 * 设置：状态(0:禁用,1:有效)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态(0:禁用,1:有效)
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

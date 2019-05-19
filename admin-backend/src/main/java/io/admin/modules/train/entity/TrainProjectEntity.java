package io.admin.modules.train.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-03-10 15:24:50
 */
@TableName("train_project")
public class TrainProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 项目编号
	 */
	private String projectId;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 项目预算
	 */
	private Double projectBudget;
	/**
	 * 培养方案
	 */
	private String trainingPlan;
	/**
	 * 审核意见
	 */
	private String auditOption;
	/**
	 * 申请人工号
	 */
	private String applicantId;
	/**
	 * 申请人姓名
	 */
	private String applicantName;
	/**
	 * 申请人所在部门
	 */
	private String applicantDept;
	/**
	 * 审核人id
	 */
	private String auditorId;
	/**
	 * 审核人姓名
	 */
	private String auditorName;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 学院审核时间
	 */
	private String academyAuditorId;
	/**
	 * 学院审核姓名
	 */
	private String academyAuditorName;
	/**
	 * 学院审核意见
	 */
	private String academyAuditOption;
	/**
	 * 学院审核时间
	 */
	private Date academyAuditTime;

	/**
	 * 状态
	 */
	private String status;
	/**
	 * 状态码
	 */
	private String statusCode;
	/**
	 * 创建用户
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 培训课时
	 */
	private Integer trainClassHour;
	/**
	 * 培训目标
	 */
	private String trainTarget;
	/**
	 * 课时分配
	 */
	private String classHourAllocation;
	/**
	 * 培训要求
	 */
	private String trainRequire;

	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：项目编号
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目编号
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * 设置：项目名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置：项目预算
	 */
	public void setProjectBudget(Double projectBudget) {
		this.projectBudget = projectBudget;
	}
	/**
	 * 获取：项目预算
	 */
	public Double getProjectBudget() {
		return projectBudget;
	}
	/**
	 * 设置：培养方案
	 */
	public void setTrainingPlan(String trainingPlan) {
		this.trainingPlan = trainingPlan;
	}
	/**
	 * 获取：培养方案
	 */
	public String getTrainingPlan() {
		return trainingPlan;
	}
	/**
	 * 设置：审核意见
	 */
	public void setAuditOption(String auditOption) {
		this.auditOption = auditOption;
	}
	/**
	 * 获取：审核意见
	 */
	public String getAuditOption() {
		return auditOption;
	}
	/**
	 * 设置：申请人工号
	 */
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	/**
	 * 获取：申请人工号
	 */
	public String getApplicantId() {
		return applicantId;
	}
	/**
	 * 设置：申请人姓名
	 */
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	/**
	 * 获取：申请人姓名
	 */
	public String getApplicantName() {
		return applicantName;
	}
	/**
	 * 设置：申请人所在部门
	 */
	public void setApplicantDept(String applicantDept) {
		this.applicantDept = applicantDept;
	}
	/**
	 * 获取：申请人所在部门
	 */
	public String getApplicantDept() {
		return applicantDept;
	}
	/**
	 * 设置：审核人id
	 */
	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}
	/**
	 * 获取：审核人id
	 */
	public String getAuditorId() {
		return auditorId;
	}
	/**
	 * 设置：审核人姓名
	 */
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	/**
	 * 获取：审核人姓名
	 */
	public String getAuditorName() {
		return auditorName;
	}
	/**
	 * 设置：审核时间
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	/**
	 * 获取：审核时间
	 */
	public Date getAuditTime() {
		return auditTime;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：状态码
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * 获取：状态码
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * 设置：创建用户
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建用户
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
	/**
	 * 设置：培训课时
	 */
	public void setTrainClassHour(Integer trainClassHour) {
		this.trainClassHour = trainClassHour;
	}
	/**
	 * 获取：培训课时
	 */
	public Integer getTrainClassHour() {
		return trainClassHour;
	}
	/**
	 * 设置：培训目标
	 */
	public void setTrainTarget(String trainTarget) {
		this.trainTarget = trainTarget;
	}
	/**
	 * 获取：培训目标
	 */
	public String getTrainTarget() {
		return trainTarget;
	}
	/**
	 * 设置：课时分配
	 */
	public void setClassHourAllocation(String classHourAllocation) {
		this.classHourAllocation = classHourAllocation;
	}
	/**
	 * 获取：课时分配
	 */
	public String getClassHourAllocation() {
		return classHourAllocation;
	}
	/**
	 * 设置：培训要求
	 */
	public void setTrainRequire(String trainRequire) {
		this.trainRequire = trainRequire;
	}
	/**
	 * 获取：培训要求
	 */
	public String getTrainRequire() {
		return trainRequire;
	}

	public String getAcademyAuditorId() {
		return academyAuditorId;
	}

	public void setAcademyAuditorId(String academyAuditorId) {
		this.academyAuditorId = academyAuditorId;
	}

	public String getAcademyAuditorName() {
		return academyAuditorName;
	}

	public void setAcademyAuditorName(String academyAuditorName) {
		this.academyAuditorName = academyAuditorName;
	}

	public Date getAcademyAuditTime() {
		return academyAuditTime;
	}

	public void setAcademyAuditTime(Date academyAuditTime) {
		this.academyAuditTime = academyAuditTime;
	}

	public String getAcademyAuditOption() {
		return academyAuditOption;
	}

	public void setAcademyAuditOption(String academyAuditOption) {
		this.academyAuditOption = academyAuditOption;
	}
}

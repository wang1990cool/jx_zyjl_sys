package io.admin.modules.train.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-01-07 11:41:08
 */
@Data
@TableName("train_project")
public class TrainProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
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

}

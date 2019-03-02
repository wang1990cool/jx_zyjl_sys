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
 * @date 2019-03-01 10:28:55
 */
@TableName("train_project_workload")
public class TrainProjectWorkloadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String projectId;
	/**
	 * 
	 */
	private String teacherId;
	/**
	 * 
	 */
	private String teacherName;
	/**
	 * 
	 */
	private Long workload;

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
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * 设置：
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * 获取：
	 */
	public String getTeacherId() {
		return teacherId;
	}
	/**
	 * 设置：
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	 * 获取：
	 */
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * 设置：
	 */
	public void setWorkload(Long workload) {
		this.workload = workload;
	}
	/**
	 * 获取：
	 */
	public Long getWorkload() {
		return workload;
	}
}

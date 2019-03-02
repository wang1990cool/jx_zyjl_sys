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
 * @date 2019-02-28 20:08:08
 */
@TableName("train_project_train_program")
public class TrainProjectTrainProgramEntity implements Serializable {
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
	 * 教师工号
	 */
	private String teacherId;
	/**
	 * 教师姓名
	 */
	private String teacherName;
	/**
	 * 课时
	 */
	private Integer classHour;

	private String classDate;

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
	 * 设置：教师工号
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * 获取：教师工号
	 */
	public String getTeacherId() {
		return teacherId;
	}
	/**
	 * 设置：教师姓名
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	 * 获取：教师姓名
	 */
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * 设置：课时
	 */
	public void setClassHour(Integer classHour) {
		this.classHour = classHour;
	}
	/**
	 * 获取：课时
	 */
	public Integer getClassHour() {
		return classHour;
	}

	public String getClassDate() {
		return classDate;
	}

	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
}

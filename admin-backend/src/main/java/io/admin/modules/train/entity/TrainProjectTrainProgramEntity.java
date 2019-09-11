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
 * @date 2019-02-28 20:08:08
 */
@Data
@TableName("train_project_train_program")
public class TrainProjectTrainProgramEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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

	private String classAddress;

	private Integer studentNum;

	private Integer startSection;

	private Integer endSection;


}

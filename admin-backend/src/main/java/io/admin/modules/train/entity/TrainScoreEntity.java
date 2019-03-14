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
 * @date 2019-03-10 19:57:02
 */
@Data
@TableName("train_score")
public class TrainScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String year;
	/**
	 * 
	 */
	private String studentNum;
	/**
	 * 
	 */
	private String studentName;
	/**
	 * 课程号
	 */
	private String courseNum;
	/**
	 * 课程明
	 */
	private String courseName;
	/**
	 * 学生成绩
	 */
	private Double studentScore;
	/**
	 * 
	 */
	private String statusCode;
	/**
	 * 
	 */
	private String status;



}

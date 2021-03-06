package com.jtdd.entity;
// Generated 2017-11-4 17:55:03 by Hibernate Tools 4.0.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SkillScore generated by hbm2java
 */
@Entity
@Table(name = "skill_score", catalog = "stms")
public class SkillScore implements java.io.Serializable {

	private Integer skillId;
	private RecordStatus recordStatus;
	private String studentNumber;
	private String studentName;
	private Date scoreDate;
	private String scoreFine;
	private String scorePass;
	private String skillRemark;
	private int score;
	private int menuId;

	public SkillScore() {
	}

	public SkillScore(RecordStatus recordStatus, String studentNumber, String studentName, Date scoreDate,
			String scoreFine, String scorePass, int score, int menuId) {
		this.recordStatus = recordStatus;
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.scoreDate = scoreDate;
		this.scoreFine = scoreFine;
		this.scorePass = scorePass;
		this.score = score;
		this.menuId = menuId;
	}

	public SkillScore(RecordStatus recordStatus, String studentNumber, String studentName, Date scoreDate,
			String scoreFine, String scorePass, String skillRemark, int score, int menuId) {
		this.recordStatus = recordStatus;
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.scoreDate = scoreDate;
		this.scoreFine = scoreFine;
		this.scorePass = scorePass;
		this.skillRemark = skillRemark;
		this.score = score;
		this.menuId = menuId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "skill_id", unique = true, nullable = false)
	public Integer getSkillId() {
		return this.skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	public RecordStatus getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
	}

	@Column(name = "student_number", nullable = false, length = 50)
	public String getStudentNumber() {
		return this.studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	@Column(name = "student_name", nullable = false, length = 6)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "score_date", nullable = false, length = 10)
	public Date getScoreDate() {
		return this.scoreDate;
	}

	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}

	@Column(name = "score_fine", nullable = false, length = 50)
	public String getScoreFine() {
		return this.scoreFine;
	}

	public void setScoreFine(String scoreFine) {
		this.scoreFine = scoreFine;
	}

	@Column(name = "score_pass", nullable = false, length = 2)
	public String getScorePass() {
		return this.scorePass;
	}

	public void setScorePass(String scorePass) {
		this.scorePass = scorePass;
	}

	@Column(name = "skill_remark", length = 200)
	public String getSkillRemark() {
		return this.skillRemark;
	}

	public void setSkillRemark(String skillRemark) {
		this.skillRemark = skillRemark;
	}

	@Column(name = "score", nullable = false)
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Column(name = "menu_id", nullable = false)
	public int getMenuId() {
		return this.menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

}

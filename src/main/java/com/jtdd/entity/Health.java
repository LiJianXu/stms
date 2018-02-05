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
 * Health generated by hbm2java
 */
@Entity
@Table(name = "health", catalog = "stms")
public class Health implements java.io.Serializable {

	private Integer id;
	private RecordStatus recordStatus;
	private String studentNumber;
	private String studentName;
	private Date healthTime;
	private int healthTestScore;
	private int healthPhysiqueScore;
	private int sickness;
	private int society;
	private String remarks;

	public Health() {
	}

	public Health(RecordStatus recordStatus, String studentNumber, String studentName, Date healthTime,
			int healthTestScore, int healthPhysiqueScore, int sickness, int society) {
		this.recordStatus = recordStatus;
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.healthTime = healthTime;
		this.healthTestScore = healthTestScore;
		this.healthPhysiqueScore = healthPhysiqueScore;
		this.sickness = sickness;
		this.society = society;
	}

	public Health(RecordStatus recordStatus, String studentNumber, String studentName, Date healthTime,
			int healthTestScore, int healthPhysiqueScore, int sickness, int society, String remarks) {
		this.recordStatus = recordStatus;
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.healthTime = healthTime;
		this.healthTestScore = healthTestScore;
		this.healthPhysiqueScore = healthPhysiqueScore;
		this.sickness = sickness;
		this.society = society;
		this.remarks = remarks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statue_id", nullable = false)
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

	@Column(name = "student_name", nullable = false, length = 50)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "health_time", nullable = false, length = 10)
	public Date getHealthTime() {
		return this.healthTime;
	}

	public void setHealthTime(Date healthTime) {
		this.healthTime = healthTime;
	}

	@Column(name = "health_test_score", nullable = false)
	public int getHealthTestScore() {
		return this.healthTestScore;
	}

	public void setHealthTestScore(int healthTestScore) {
		this.healthTestScore = healthTestScore;
	}

	@Column(name = "health_physique_score", nullable = false)
	public int getHealthPhysiqueScore() {
		return this.healthPhysiqueScore;
	}

	public void setHealthPhysiqueScore(int healthPhysiqueScore) {
		this.healthPhysiqueScore = healthPhysiqueScore;
	}

	@Column(name = "sickness", nullable = false)
	public int getSickness() {
		return this.sickness;
	}

	public void setSickness(int sickness) {
		this.sickness = sickness;
	}

	@Column(name = "society", nullable = false)
	public int getSociety() {
		return this.society;
	}

	public void setSociety(int society) {
		this.society = society;
	}

	@Column(name = "remarks", length = 50)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}

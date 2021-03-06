package com.jtdd.entity;
// Generated 2017-11-4 17:55:03 by Hibernate Tools 4.0.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RecordStatus generated by hbm2java
 */
@Entity
@Table(name = "record_status", catalog = "stms")
public class RecordStatus implements java.io.Serializable {

	private Integer id;
	private String statusName;
	private int statusType;
	private Set<Health> healths = new HashSet<Health>(0);
	private Set<SkillExamActivity> skillExamActivities = new HashSet<SkillExamActivity>(0);
	private Set<TeamHoorMessage> teamHoorMessages = new HashSet<TeamHoorMessage>(0);
	private Set<SkillActivity> skillActivities = new HashSet<SkillActivity>(0);
	private Set<SkillScore> skillScores = new HashSet<SkillScore>(0);

	public RecordStatus() {
	}

	public RecordStatus(String statusName, int statusType) {
		this.statusName = statusName;
		this.statusType = statusType;
	}

	public RecordStatus(String statusName, int statusType, Set<Health> healths,
			Set<SkillExamActivity> skillExamActivities, Set<TeamHoorMessage> teamHoorMessages,
			Set<SkillActivity> skillActivities, Set<SkillScore> skillScores) {
		this.statusName = statusName;
		this.statusType = statusType;
		this.healths = healths;
		this.skillExamActivities = skillExamActivities;
		this.teamHoorMessages = teamHoorMessages;
		this.skillActivities = skillActivities;
		this.skillScores = skillScores;
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

	@Column(name = "status_name", nullable = false, length = 50)
	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Column(name = "status_type", nullable = false)
	public int getStatusType() {
		return this.statusType;
	}

	public void setStatusType(int statusType) {
		this.statusType = statusType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recordStatus")
	public Set<Health> getHealths() {
		return this.healths;
	}

	public void setHealths(Set<Health> healths) {
		this.healths = healths;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recordStatus")
	public Set<SkillExamActivity> getSkillExamActivities() {
		return this.skillExamActivities;
	}

	public void setSkillExamActivities(Set<SkillExamActivity> skillExamActivities) {
		this.skillExamActivities = skillExamActivities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recordStatus")
	public Set<TeamHoorMessage> getTeamHoorMessages() {
		return this.teamHoorMessages;
	}

	public void setTeamHoorMessages(Set<TeamHoorMessage> teamHoorMessages) {
		this.teamHoorMessages = teamHoorMessages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recordStatus")
	public Set<SkillActivity> getSkillActivities() {
		return this.skillActivities;
	}

	public void setSkillActivities(Set<SkillActivity> skillActivities) {
		this.skillActivities = skillActivities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recordStatus")
	public Set<SkillScore> getSkillScores() {
		return this.skillScores;
	}

	public void setSkillScores(Set<SkillScore> skillScores) {
		this.skillScores = skillScores;
	}

}

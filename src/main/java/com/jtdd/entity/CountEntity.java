package com.jtdd.entity;

import java.io.Serializable;

public class CountEntity implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	public static Integer HONEST = 1;
	public static Integer EXECUTION = 2;
	
	private Integer id;
	
	private String date;
	
	private String addScore;
	
	private String subScore;
	
	private Integer allScore;
	
	private Integer type;



	public CountEntity() {
		super();
	}

	public CountEntity(Integer id, String date, String addScore, String subScore, Integer allScore, Integer type) {
		super();
		this.id = id;
		this.date = date;
		this.addScore = addScore;
		this.subScore = subScore;
		this.allScore = allScore;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddScore() {
		return addScore;
	}

	public void setAddScore(String addScore) {
		this.addScore = addScore;
	}

	public String getSubScore() {
		return subScore;
	}

	public void setSubScore(String subScore) {
		this.subScore = subScore;
	}

	public Integer getAllScore() {
		return allScore;
	}

	public void setAllScore(Integer allScore) {
		this.allScore = allScore;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CountEntity [id=" + id + ", date=" + date + ", addScore=" + addScore + ", subScore=" + subScore
				+ ", allScore=" + allScore + ", type=" + type + "]";
	}
	
	

}

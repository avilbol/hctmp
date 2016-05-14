package com.hallocasa.commons.vo;

import java.io.Serializable;

public class Coordinate implements Serializable{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 1039611321081520687L;

	private Integer grades;
	
	private Integer minutes;
	
	private Integer seconds;

	public Integer getGrades() {
		return grades;
	}

	public void setGrades(Integer grades) {
		this.grades = grades;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}
}

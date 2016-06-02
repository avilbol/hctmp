package com.hallocasa.commons.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Coordinate implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 1039611321081520687L;

	private Integer grades;

	private Integer minutes;

	private Integer seconds;

	private BigDecimal decRepresentation;

	public Coordinate() {
		super();
	}

	public Coordinate(BigDecimal decRepresentation) {
		super();
		this.decRepresentation = decRepresentation;
		setupGms();
	}
	
	private void setupGms(){
		if(this.getDecRepresentation() == null){
			return;
		}
		double deg = this.decRepresentation.doubleValue();
		double d = Math.floor(deg);
		double minfloat = (deg - d) * 60;
		double m = Math.floor(minfloat);
		double secfloat = (minfloat - m) * 60;
		double s = Math.round(secfloat);
		if (s == 60) {
			m++;
			s = 0;
		}
		if (m == 60) {
			d++;
			m = 0;
		}
		this.setGrades((int) d);
		this.setMinutes((int) m);
		this.setSeconds((int) s);
	}

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

	public BigDecimal getDecRepresentation() {
		return decRepresentation;
	}

	public void setDecRepresentation(BigDecimal decRepresentation) {
		this.decRepresentation = decRepresentation;
		setupGms();
	}
}

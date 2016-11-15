package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;
import java.util.Date;

import com.hallocasa.vo.i.ValueObject;

public class PropertyFieldValueSpec implements ValueObject, Serializable {

	private static final long serialVersionUID = 1310406395646310790L;

	private String strVal;

	private Boolean boolVal;

	private Date dateVal;

	private Double doubleVal;
	
	private Integer intVal;

	public String getStrVal() {
		return strVal;
	}

	public void setStrVal(String strVal) {
		this.strVal = strVal;
	}

	public Boolean getBoolVal() {
		return boolVal;
	}

	public void setBoolVal(Boolean boolVal) {
		this.boolVal = boolVal;
	}

	public Date getDateVal() {
		return dateVal;
	}

	public void setDateVal(Date dateVal) {
		this.dateVal = dateVal;
	}

	public Double getDoubleVal() {
		return doubleVal;
	}

	public void setDoubleVal(Double doubleVal) {
		this.doubleVal = doubleVal;
	}

	public Integer getIntVal() {
		return intVal;
	}

	public void setIntVal(Integer intVal) {
		this.intVal = intVal;
	}
}

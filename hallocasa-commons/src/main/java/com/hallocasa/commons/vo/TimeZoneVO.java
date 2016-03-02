package com.hallocasa.commons.vo;

import java.io.Serializable;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * The Value object class for the time zone
 * 
 */
public class TimeZoneVO implements Serializable, ValueObject {

	private static final long serialVersionUID = 1L;
	/**
	 * Name of the property timeZoneName
	 */
	public static final String timeZoneName_ = "timeZoneName";

	private Long id;
	private String timeZoneName;

	/**
	 * Constructor
	 */
	public TimeZoneVO() {
	}

	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for timeZoneName
	 * 
	 * @return the timeZoneName
	 */
	public String getTimeZoneName() {
		return timeZoneName;
	}

	/**
	 * Setter for timeZoneName
	 * 
	 * @param timeZoneName the timeZoneName to set
	 */
	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeZoneVO other = (TimeZoneVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hallocasa.vo.User;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

/**
 * This value object represents an hallocasa property
 * @author Alexander Villamil
 */
public class Property implements ValueObject, Serializable {

	private static final long serialVersionUID = -8762506705749463829L;

	private String id;
	
	private User user;
	
	private PropertyKey propertyKey;
	
	private List<PropertyField> fieldList;
	
	private Date publishDate;

	public PropertyKey getPropertyKey() {
		return propertyKey;
	}

	public void setPropertyKey(PropertyKey propertyKey) {
		this.propertyKey = propertyKey;
	}

	public List<PropertyField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<PropertyField> fieldList) {
		this.fieldList = fieldList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}

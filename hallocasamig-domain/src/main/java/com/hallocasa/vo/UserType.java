package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Value object that represents a type of user
 */
public class UserType implements ValueObject, Serializable{

	private static final long serialVersionUID = -1643100320879253399L;

    private Long id;

    private String name;
	
    private String lang;
    
    private String tooltipLang;

    private boolean manageTooltip;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getTooltipLang() {
		return tooltipLang;
	}

	public void setTooltipLang(String tooltipLang) {
		this.tooltipLang = tooltipLang;
	}

	public boolean isManageTooltip() {
		return manageTooltip;
	}

	public void setManageTooltip(boolean manageTooltip) {
		this.manageTooltip = manageTooltip;
	}
}

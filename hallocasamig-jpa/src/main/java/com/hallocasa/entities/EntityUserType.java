package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.persistence.converters.HcBooleanConverter;

@Entity
@Table(name = "user_type")
@NamedQueries({
    @NamedQuery(name= EntityUserType.QUERY_FIND_ALL, query = "select u from EntityUserType u")
})
public class EntityUserType implements HallocasaEntity {

    public static final String QUERY_FIND_ALL = "UserType.findAll";

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lang")
    private String lang;
    
    @Column(name = "tooltip_lang")
    private String tooltipLang;
    
    @Convert(converter = HcBooleanConverter.class)
    @Column(name = "manage_tooltip")
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

	public boolean isManageTooltip() {
		return manageTooltip;
	}

	public void setManageTooltip(boolean manageTooltip) {
		this.manageTooltip = manageTooltip;
	}

	public String getTooltipLang() {
		return tooltipLang;
	}

	public void setTooltipLang(String tooltipLang) {
		this.tooltipLang = tooltipLang;
	}
}

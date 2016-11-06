/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.app;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.dataentities.converters.MultiLanguageTextConverter;

/**
 *
 * @author Alexander Villamil
 */
@Entity
@Table(name = "city")
@NamedQueries({
    @NamedQuery(name = City.QUERY_FIND_BY_ID,
            query = "select c from City c where c.id = ?1"),
    @NamedQuery(name = City.QUERY_FIND_ALL,
    		query = "select c from City c"),
    @NamedQuery(name = City.QUERY_FIND_BY_STATE,
            query = "select c from City c where c.state = ?1")
})
public class City implements Serializable, HallocasaEntity {
    
    public static final String QUERY_FIND_BY_ID = "City.findById";
    public static final String QUERY_FIND_ALL = "City.findAll";
    public static final String QUERY_FIND_BY_STATE = "City.findByState";
    
    public static final String defaultLatCoordinate_ = "defaultLatCoordinate";
	public static final String defaultLngCoordinate_ = "defaultLngCoordinate";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "city_name")
    @Convert(converter = MultiLanguageTextConverter.class)
    private MultiLanguageText cityName;

    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private State state;
    
    @Column(name = "default_lat_coordinate")
    private BigDecimal defaultLatCoordinate;
    
    @Column(name = "default_lng_coordinate")
    private BigDecimal defaultLngCoordinate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultiLanguageText getCityName() {
        return cityName;
    }

    public void setCityName(MultiLanguageText cityName) {
        this.cityName = cityName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

	public BigDecimal getDefaultLatCoordinate() {
		return defaultLatCoordinate;
	}

	public void setDefaultLatCoordinate(BigDecimal defaultLatCoordinate) {
		this.defaultLatCoordinate = defaultLatCoordinate;
	}

	public BigDecimal getDefaultLngCoordinate() {
		return defaultLngCoordinate;
	}

	public void setDefaultLngCoordinate(BigDecimal defaultLngCoordinate) {
		this.defaultLngCoordinate = defaultLngCoordinate;
	}
}

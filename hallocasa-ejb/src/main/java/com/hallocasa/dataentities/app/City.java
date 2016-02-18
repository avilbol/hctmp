/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.app;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.dataentities.converters.MultiLanguageTextConverter;
import java.io.Serializable;
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

/**
 *
 * @author Alexander Villamil
 */
@Entity
@Table(name = "city")
@NamedQueries({
    @NamedQuery(name = City.QUERY_FIND_BY_ID,
            query = "select c from City c where c.id = ?1"),
    @NamedQuery(name = City.QUERY_FIND_BY_STATE,
            query = "select c from City c where c.state = ?1")
})
public class City implements Serializable, HallocasaEntity {
    
    public static final String QUERY_FIND_BY_ID = "City.findById";
    public static final String QUERY_FIND_BY_STATE = "City.findByState";
    
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

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.app;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.Coordinate;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.dataentities.converters.MultiLanguageTextConverter;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "country")
@NamedQueries({
    @NamedQuery(name = Country.QUERY_FIND_BY_ID,
            query = "select c from Country c where c.id = ?1"),
    @NamedQuery(name= Country.QUERY_FIND_ALL,
            query = "select c from Country c")
})
public class Country implements Serializable, HallocasaEntity {

    public static final String QUERY_FIND_BY_ID = "Country.findById";
    public static final String QUERY_FIND_ALL = "Country.findAll";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_name")
    @Convert(converter = MultiLanguageTextConverter.class)
    private MultiLanguageText countryName;

    @Column(name = "java_code")
    private String javaCode;
    
    /**
     * Default constructor
     */
    public Country() {
    }

    /**
     * Constructor with primary key
     *
     * @param id
     */
    public Country(Long id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param countryName
     * @param javaCode
     */
    public Country(Long id, MultiLanguageText countryName, String javaCode) {
        this.id = id;
        this.countryName = countryName;
        this.javaCode = javaCode;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the countryName
     */
    public MultiLanguageText getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(MultiLanguageText countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the javaCode
     */
    public String getJavaCode() {
        return javaCode;
    }

    /**
     * @param javaCode the javaCode to set
     */
    public void setJavaCode(String javaCode) {
        this.javaCode = javaCode;
    }
}

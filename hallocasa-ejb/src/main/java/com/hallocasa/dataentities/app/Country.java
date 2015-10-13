/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.app;

import java.io.Serializable;
import javax.persistence.Column;
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
            query = "select c from Country c where c.id = ?1")
})
public class Country implements Serializable {

    public static final String QUERY_FIND_BY_ID = "Country.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "java_code")
    private String javaCode;

    /**
     * Default constructor
     */
    public Country() {
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
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
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

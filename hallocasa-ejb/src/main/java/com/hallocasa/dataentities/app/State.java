/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.app;

import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import java.io.Serializable;
import javax.persistence.Column;
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
 * @author david
 */
@Entity
@Table(name = "state")
@NamedQueries({
    @NamedQuery(name = State.QUERY_FIND_BY_ID,
            query = "select s from State s where s.id = ?1"),
    @NamedQuery(name = State.QUERY_FIND_BY_COUNTRY,
            query = "select s from State s where s.country = ?1")
})
public class State implements Serializable, HallocasaEntity {

    public static final String QUERY_FIND_BY_ID = "State.findById";
    public static final String QUERY_FIND_BY_COUNTRY = "State.findByCountry";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "state_name")
    private String stateName;

    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;

    /**
     * Default constructor
     */
    public State() {
    }

    /**
     * Constructor with parameters
     *
     * @param id
     * @param stateName
     * @param country
     */
    public State(Long id, String stateName, Country country) {
        this.id = id;
        this.stateName = stateName;
        this.country = country;
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
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }

}

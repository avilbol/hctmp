/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.vo;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.ValueObject;
import java.io.Serializable;

/**
 *
 * @author Alexander Villamil
 */
public class CityVO implements Serializable, ValueObject {
    
    private static final long serialVersionUID = 1L;
    /**
     * Name of the id property
     */
    public static final String id_ = "id";
    /**
     * Name of the country name property
     */
    public static final String cityName_ = "cityName";

    private Long id;
    private MultiLanguageText cityName;
    private StateVO state;
    private Coordinate latCoordinate;
    private Coordinate lngCoordinate;

    /**
     * Constructor
     */
    public CityVO() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public CityVO(Long id) {
        super();
        this.id = id;
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
     * Getter for cityName
     *
     * @return the cityName
     */
    public MultiLanguageText getCityName() {
        return cityName;
    }

    /**
     * Setter for cityName
     *
     * @param cityName the cityName to set
     */
    public void setCityName(MultiLanguageText cityName) {
        this.cityName = cityName;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CityVO other = (CityVO) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public StateVO getState() {
        return state;
    }

    public void setState(StateVO state) {
        this.state = state;
    }

	public Coordinate getLatCoordinate() {
		return latCoordinate;
	}

	public void setLatCoordinate(Coordinate latCoordinate) {
		this.latCoordinate = latCoordinate;
	}

	public Coordinate getLngCoordinate() {
		return lngCoordinate;
	}

	public void setLngCoordinate(Coordinate lngCoordinate) {
		this.lngCoordinate = lngCoordinate;
	}
}

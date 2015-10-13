package com.hallocasa.commons.vo;

import java.io.Serializable;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * The persistent class for the country database table.
 *
 */
public class StateVO implements Serializable, ValueObject {

    private static final long serialVersionUID = 1L;
    /**
     * Name of the id property
     */
    public static final String id_ = "id";
    /**
     * Name of the country name property
     */
    public static final String stateName_ = "stateName";

    private Long id;
    private String stateName;
    private CountryVO country;

    /**
     * Constructor
     */
    public StateVO() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public StateVO(Long id) {
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
     * Getter for stateName
     *
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Setter for stateName
     *
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
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
        StateVO other = (StateVO) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the country
     */
    public CountryVO getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(CountryVO country) {
        this.country = country;
    }

}

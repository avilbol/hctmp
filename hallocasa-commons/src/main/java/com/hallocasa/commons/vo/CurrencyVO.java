package com.hallocasa.commons.vo;

import java.io.Serializable;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for currency representation
 * @author avillamil
 *
 */
public class CurrencyVO  implements ValueObject, Serializable{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -5728762206380375694L;

	private Integer id;
	
	private MultiLanguageText name;

	private String abbreviation;

	private String prefix;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MultiLanguageText getName() {
		return name;
	}

	public void setName(MultiLanguageText name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
        CurrencyVO other = (CurrencyVO) obj;
        if (id == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!id.equals(other.getId())) {
            return false;
        }
        return true;
    }
}

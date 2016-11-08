package com.hallocasa.commons.vo.properties;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.ValueObject;


/**
 * Value object for a property type
 * @author avillamil
 *
 */
public class PropertyTypeVO  implements ValueObject{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 7693359995496257200L;

	private PropertyTypeGroupVO propertyTypeGroup;
	
	/**
	 * Property type identifier
	 */
	private Integer id;
	
	private MultiLanguageText name;

	public PropertyTypeGroupVO getPropertyTypeGroup() {
		return propertyTypeGroup;
	}

	public void setPropertyTypeGroup(PropertyTypeGroupVO propertyTypeGroup) {
		this.propertyTypeGroup = propertyTypeGroup;
	}

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
        PropertyTypeVO other = (PropertyTypeVO) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
	
}

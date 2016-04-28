package com.hallocasa.helpers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.hallocasa.commons.vo.properties.PropertyBasicInfo;
import com.hallocasa.commons.vo.properties.PropertyImageInfo;
import com.hallocasa.commons.vo.properties.PropertyLocationInfo;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.dataentities.app.properties.PropertyFieldValue;

/**
 * Parser of propertyVO to property and vice-versa
 *
 * @author Alexander Villamil
 * @since 1.7
 */
public class PropertyVOParser extends HallocasaVOParser<Property, PropertyVO>{

	/**
     * Constructor
     */
    public PropertyVOParser() {
        super();
    }

    /* static fields */

    /* instance variables */

    /* constructors */

    /* Methods */
    protected PropertyVO toValueObject(Property entity) throws IllegalAccessException,
            InvocationTargetException {
    	PropertyVO vo = new PropertyVO();
    	setupKeyInfo(vo, entity);
    	List<PropertyFieldValue> fieldValueList = entity.getFieldValueList();
    	setupBasicInfo(vo.getPropertyBasicInfo(), fieldValueList);
    	setupLocationInfo(vo.getPropertyLocationInfo(), fieldValueList);
    	setupImageInfo(vo.getPropertyImageInfo(), fieldValueList);
        return vo;
    }

   
	private void setupImageInfo(PropertyImageInfo propertyImageInfo,
			List<PropertyFieldValue> fieldValueList) {
		// TODO Auto-generated method stub
		
	}

	private void setupLocationInfo(PropertyLocationInfo propertyLocationInfo,
			List<PropertyFieldValue> fieldValueList) {
		// TODO Auto-generated method stub
		
	}

	private void setupBasicInfo(PropertyBasicInfo propertyBasicInfo,
			List<PropertyFieldValue> fieldValueList) {
		// TODO Auto-generated method stub
		
	}

	private void setupKeyInfo(PropertyVO vo, Property entity) {
		// TODO Auto-generated method stub
		//vo.setPropertyProposal(entity.getPropertyProposal());
	}

	@Override
    protected void copyVOPropertyToEntityProperty(PropertyVO vo, Property entity,
            String propertyName, Object propertyValue, Object[] options)
            throws IllegalAccessException, InvocationTargetException {

        super.copyVOPropertyToEntityProperty(vo, entity, propertyName,
                propertyValue, options);
    }

    /**
     *
     * @param vo
     * @param entity
     * @param excludeIpList If this is true, the ip list is not copied
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void copyVOToEntity(PropertyVO vo, Property entity,
            boolean excludeIpList) throws IllegalAccessException,
            InvocationTargetException {
        copyVOToEntity(vo, entity, buildOptions(excludeIpList));
    }

    /**
     * @param excludeIpList
     * @return
     */
    private Object[] buildOptions(boolean excludeIpList) {
        return new Object[]{excludeIpList};
    }

    /* Getters & Setters */
	
}

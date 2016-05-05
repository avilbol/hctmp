package com.hallocasa.helpers;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.hallocasa.commons.vo.properties.PropertyTypeGroupVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.dataentities.app.properties.PropertyType;
import com.hallocasa.dataentities.app.properties.PropertyTypeGroup;

/**
 * Parser for type of properties
 * @author avillamil
 *
 */
public class PropertyTypeVOParser extends HallocasaVOParser<PropertyType, PropertyTypeVO> {


	/* static fields */

	/* instance variables */

	/* constructors */

	/**
	 * Constructor
	 */
	public PropertyTypeVOParser() {
		super();
	}

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */
	@Override
	protected void copyEntityPropertyToValueObjectProperty(PropertyTypeVO vo,
			PropertyType entity, String propertyName, Object propertyValue,
			Object[] options) throws IllegalAccessException,
			InvocationTargetException {
		if (propertyName.equals(PropertyType.group_)) {
			vo.setPropertyTypeGroup(new PropertyTypeGroupVO());
			BeanUtils.copyProperties(vo.getPropertyTypeGroup(), entity.getPropertyTypeGroup());
		}
		else {
			super.copyEntityPropertyToValueObjectProperty(vo, entity, propertyName,
					propertyValue, options);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobiera.social.commons.vo.helpers.GenericVOParser#
	 * copyVOPropertyToEntityProperty
	 * (com.mobiera.social.commons.vo.interfaces.ValueObject,
	 * com.mobiera.social.commons.vo.interfaces.SocialEntity, java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	protected void copyVOPropertyToEntityProperty(PropertyTypeVO vo, PropertyType entity,
			String propertyName, Object propertyValue, Object[] options)
			throws IllegalAccessException, InvocationTargetException {
		if (propertyName.equals(PropertyType.group_)) {
			entity.setPropertyTypeGroup(new PropertyTypeGroup());
			BeanUtils.copyProperties(entity.getPropertyTypeGroup(), vo.getPropertyTypeGroup());
		}
		else {
			super.copyVOPropertyToEntityProperty(vo, entity, propertyName,
					propertyValue, options);
		}
	}
	
	/* Getters & Setters */
	
}

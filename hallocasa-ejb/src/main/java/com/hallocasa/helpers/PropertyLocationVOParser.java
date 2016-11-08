package com.hallocasa.helpers;

import java.lang.reflect.InvocationTargetException;

import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.dataentities.app.properties.PropertyLocation;

/**
 * Parser of propertyLocationVO to propertyLocation and vice-versa
 *
 * @author Alexander Villamil
 * @since 1.7
 */
public class PropertyLocationVOParser extends HallocasaVOParser<PropertyLocation, PropertyLocationVO> {

	/**
	 * Constructor
	 */
	public PropertyLocationVOParser() {
		super();
	}

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */
	@Override
	protected void copyEntityPropertyToValueObjectProperty(PropertyLocationVO vo, PropertyLocation entity, String propertyName,
			Object propertyValue, Object[] options) throws IllegalAccessException, InvocationTargetException {

		super.copyEntityPropertyToValueObjectProperty(vo, entity, propertyName, propertyValue, options);

	}

	@Override
	protected void copyVOPropertyToEntityProperty(PropertyLocationVO vo, PropertyLocation entity, String propertyName,
			Object propertyValue, Object[] options) throws IllegalAccessException, InvocationTargetException {

		super.copyVOPropertyToEntityProperty(vo, entity, propertyName, propertyValue, options);
	}

	/**
	 *
	 * @param vo
	 * @param entity
	 * @param excludeIpList
	 *            If this is true, the ip list is not copied
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void copyVOToEntity(PropertyLocationVO vo, PropertyLocation entity, boolean excludeIpList)
			throws IllegalAccessException, InvocationTargetException {
		copyVOToEntity(vo, entity, buildOptions(excludeIpList));
	}

	/**
	 * @param excludeIpList
	 * @return
	 */
	private Object[] buildOptions(boolean excludeIpList) {
		return new Object[] { excludeIpList };
	}

	/* Getters & Setters */
}
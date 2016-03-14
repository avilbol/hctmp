package com.hallocasa.helpers;

import java.lang.reflect.InvocationTargetException;

import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Parser of VO to entity instance and vice-versa
 *
 * @author Alexander Villamil
 * @since 1.7
 */
public class StandardVOParser<T extends HallocasaEntity, U extends ValueObject> extends HallocasaVOParser<T, U> {

	/**
	 * Constructor
	 */
	public StandardVOParser() {
		super();
	}

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */
	@Override
	protected void copyEntityPropertyToValueObjectProperty(U vo, T entity, String propertyName, Object propertyValue,
			Object[] options) throws IllegalAccessException, InvocationTargetException {

		super.copyEntityPropertyToValueObjectProperty(vo, entity, propertyName, propertyValue, options);

	}

	@Override
	protected void copyVOPropertyToEntityProperty(U vo, T entity, String propertyName, Object propertyValue,
			Object[] options) throws IllegalAccessException, InvocationTargetException {

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
	public void copyVOToEntity(U vo, T entity, boolean excludeIpList)
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

package com.hallocasa.utils.constants.parsing;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;

import com.hallocasa.utils.constants.parsing.i.Parser;

/**
 * Class to implement the parse between vo's and entities
 * @author avillamil
 *
 * @param <U>
 * @param <V>
 */
public class DeeperParser<U, V> implements Parser<U,V> {

	private Set<String> ignoreEntityFields;
	private Set<String> ignoreVOFields;

	/**
	 * Constructor
	 */
	public DeeperParser() {
		ignoreEntityFields = new HashSet<String>();
		ignoreVOFields = new HashSet<String>();

		addIgnoreEntityField("class");
		addIgnoreVOField("class");
	}

	/**
	 * Constructor
	 */
	public DeeperParser(List<String> paramsToIgnore) {
		ignoreEntityFields = new HashSet<String>();
		ignoreVOFields = new HashSet<String>();

		addIgnoreEntityField("class");
		addIgnoreVOField("class");
		
		for(String paramToIgnore : paramsToIgnore){
			addIgnoreEntityField(paramToIgnore);
			addIgnoreVOField(paramToIgnore);
		}
	}
	

	/**
	 * Copy the value object to the entity
	 * 
	 * @param vo
	 * @param entity
	 * @param options
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void copyVOToEntity(U vo, V entity)
		throws IllegalAccessException, InvocationTargetException {
		try {
			Map<String, Object> voProperties = PropertyUtils.describe(vo);

			for (String propertyName : voProperties.keySet()) {
				if (!ignoreVOFields.contains(propertyName)) {
					copyVOPropertyToEntityProperty(vo, entity, propertyName,
						voProperties.get(propertyName));
				}
			}
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Copies a property from entity to a property in the value object
	 * 
	 * @param vo Value Object
	 * @param entity Entity object
	 * @param propertyName Name of the property to copy
	 * @param propertyValue Value of the property in the entity
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	protected void copyEntityPropertyToValueObjectProperty(U vo, T entity,
		String propertyName, Object propertyValue, Object[] options)
		throws IllegalAccessException, InvocationTargetException {
		// by default executes a reflection copy
		getBeanUtilsBean().setProperty(vo, propertyName, propertyValue);
	}

	/**
	 * Copies a property from value object to a property in the entity
	 * 
	 * @param vo Value Object
	 * @param entity Entity object
	 * @param propertyName Name of the property to copy
	 * @param propertyValue Value of the property in the entity
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	protected void copyVOPropertyToEntityProperty(U vo, V entity,
		String propertyName, Object propertyValue)
		throws IllegalAccessException, InvocationTargetException {

		// by default executes a reflection copy
		try {
			getBeanUtilsBean().setProperty(entity, propertyName, propertyValue);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Error setting property "
				+ propertyName + " with value " + propertyValue
				+ " over entity " + entity, e);
		}
	}

	/**
	 * The implementation of this method should return the bean utilities for
	 * conversions
	 * 
	 * @return the beanUtilsBean
	 */
	public abstract BeanUtilsBean getBeanUtilsBean();

	/**
	 * Converts Entity to ValueObject
	 * 
	 * @param entity
	 * @param valueObjectClass
	 * @param options
	 * @return the created value object
	 */
	public U toValueObject(T entity, Class<U> valueObjectClass, Object[] options) {
		try {
			if (entity == null) {
				return null;
			}
			U vo = valueObjectClass.newInstance();
			copyEntityToVO(entity, vo, options);
			return vo;
		} catch (IllegalAccessException | InvocationTargetException
			| InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Converts Entity to ValueObject
	 * 
	 * @param entity
	 * @param valueObjectClass
	 * @return the created value object
	 */
	public U toValueObject(T entity, Class<U> valueObjectClass) {
		return toValueObject(entity, valueObjectClass, null);
	}

	/**
	 * Creates a value object list from entity list
	 * 
	 * @param entities entities to convert
	 * @param valueObjectClass value object class
	 * @param options
	 * @return the list of the new value objects
	 */
	public List<U> toValueObjectList(List<T> entities,
		Class<U> valueObjectClass, Object[] options) {
		if (entities == null) {
			return null;
		}
		List<U> list = new ArrayList<>();
		for (T entity : entities) {
			list.add(toValueObject(entity, valueObjectClass, options));
		}
		return list;
	}

	/**
	 * Creates a value object list from entity list
	 * 
	 * @param entities entities to convert
	 * @param valueObjectClass value object class
	 * 
	 * @return the list of the new value objects
	 */
	public List<U> toValueObjectList(List<T> entities, Class<U> valueObjectClass) {
		return toValueObjectList(entities, valueObjectClass, null);
	}

	/**
	 * Creates a entity list from value object list
	 * 
	 * @param vos Value objects to convert
	 * @param entityClass value object class
	 * @param options
	 * @return the list of the new entities
	 */
	final public List<T> toEntityList(List<U> vos, Class<T> entityClass,
		Object[] options) {
		if (vos == null) {
			return null;
		}
		List<T> list = new ArrayList<>();
		for (U vo : vos) {
			list.add(toEntity(vo, entityClass, options));
		}
		return list;
	}

	/**
	 * Creates a entity list from value object list
	 * 
	 * @param vos Value objects to convert
	 * @param entityClass value object class
	 * @return the list of the new entities
	 */
	final public List<T> toEntityList(List<U> vos, Class<T> entityClass) {
		return toEntityList(vos, entityClass, null);
	}

	/**
	 * 
	 * @param entity
	 * @param vo
	 * @param options
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	final public void copyEntityToVO(T entity, U vo)
		throws IllegalAccessException, InvocationTargetException {
		copyEntityToVO(entity, vo, null);
	}

	/**
	 * 
	 * @param entity
	 * @param vo
	 * @param options
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	final public void copyEntityToVO(V entity, U vo, Object[] options)
		throws IllegalAccessException, InvocationTargetException {
		try {
			Map<String, Object> entityProperties = PropertyUtils
				.describe(entity);

			for (String propertyName : entityProperties.keySet()) {
				if (!ignoreEntityFields.contains(propertyName)) {
					copyEntityPropertyToValueObjectProperty(vo, entity,
						propertyName, entityProperties.get(propertyName),
						options);
				}
			}
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds the field to the ignore list, so it will not going to have into
	 * account in the parsing process from VO to entity
	 * 
	 * @param fieldName
	 */
	public void addIgnoreVOField(String fieldName) {
		ignoreVOFields.add(fieldName);
	}

	/**
	 * Adds the field to the ignore list, so it will not going to have into
	 * account in the parsing process from entity to VO
	 * 
	 * @param fieldName
	 */
	public void addIgnoreEntityField(String fieldName) {
		ignoreEntityFields.add(fieldName);
	}

	@Override
	public V toEntity(U valueObject, Class<V> clazz) {
		if (valueObject == null) {
			return null;
		}
		try {
			V entity = clazz.newInstance();
			copyVOToEntity(valueObject, entity);
			return entity;
		} catch (IllegalAccessException | InvocationTargetException
			| InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public U toValueObject(V entity, Class<U> clazz) {
		// TODO Auto-generated method stub
		return null;
	}
}

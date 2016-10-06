package com.hallocasa.utils.constants.parsing;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.i.ValueObject;

/**
 * Class to implement the parse between vo's and entities
 * @author avillamil
 *
 * @param <U>
 * @param <V>
 */
public class DeeperParser implements Parser<ValueObject, HallocasaEntity> {

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
	public void copyVOToEntity(ValueObject vo, HallocasaEntity entity)
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
	protected void copyEntityPropertyToValueObjectProperty(ValueObject vo, HallocasaEntity entity,
		String propertyName, Object propertyValue, Object[] options)
		throws IllegalAccessException, InvocationTargetException {
		// by default executes a reflection copy
		//getBeanUtilsBean().setProperty(vo, propertyName, propertyValue);
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
	protected void copyVOPropertyToEntityProperty(ValueObject vo, HallocasaEntity entity,
		String propertyName, Object propertyValue)
		throws IllegalAccessException, InvocationTargetException {

		// by default executes a reflection copy
		try {
			//getBeanUtilsBean().setProperty(entity, propertyName, propertyValue);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Error setting property "
				+ propertyName + " with value " + propertyValue
				+ " over entity " + entity, e);
		}
	}

	/**
	 * 
	 * @param entity
	 * @param vo
	 * @param options
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	final public void copyEntityToVO(HallocasaEntity entity, ValueObject vo)
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
	final public void copyEntityToVO(HallocasaEntity entity, ValueObject vo, Object[] options)
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
	public HallocasaEntity toEntity(ValueObject valueObject, Class<?> clazz) {
		if (valueObject == null) {
			return null;
		}
		try {
			Object entity = clazz.newInstance();
			copyVOToEntity(valueObject, (HallocasaEntity) entity);
			return (HallocasaEntity) entity;
		} catch (IllegalAccessException | InvocationTargetException
			| InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public ValueObject toValueObject(HallocasaEntity entity, Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.hallocasa.utils.constants.parsing;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.i.ValueObject;

import static org.apache.commons.beanutils.PropertyUtils.getPropertyType;
import static com.hallocasa.utils.constants.parsing.ParserMetadata.clazzEquivalenceMap;
import static com.hallocasa.utils.constants.parsing.ParserMetadata.getParser;
import static com.hallocasa.utils.constants.parsing.ParserMetadata.getWrapperTypes;


/**
 * Class to implement the parse between vo's and entities
 * 
 * @author avillamil
 */
public class DefaultParser implements Parser<ValueObject, HallocasaEntity> {

	private Set<String> ignoreFields;
	private BeanUtilsBean beanUtilsBean;

	public DefaultParser() {
		ignoreFields = new HashSet<String>();
		addIgnoreField("class");
	}

	public DefaultParser(List<String> paramsToIgnore) {
		this();
		if (paramsToIgnore == null) {
			return;
		}
		for (String paramToIgnore : paramsToIgnore) {
			addIgnoreField(paramToIgnore);
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
				if (!ignoreFields.contains(propertyName)) {
					copyVOPropertyToEntityProperty(vo, entity, propertyName, voProperties.get(propertyName));
				}
			}
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Copies a property from entity to a property in the value object
	 * 
	 * @param vo
	 *            Value Object
	 * @param entity
	 *            Entity object
	 * @param propertyName
	 *            Name of the property to copy
	 * @param propertyValue
	 *            Value of the property in the entity
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	protected void copyEntityPropertyToVoProperty(ValueObject vo, HallocasaEntity entity, String propertyName,
			Object propertyValue) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		try {
			ParsingPackage parsingPackage = loadParsingPackage(vo, entity, propertyName, propertyValue);
			if(parsingPackage.isApplyParser()){
				parsingPackage.entityToVo(getPropertyType(vo, propertyName));
			}
			getBeanUtilsBean().setProperty(vo, propertyName, parsingPackage.getPropertyToSet());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Error setting property "
				+ propertyName + " with value " + propertyValue
				+ " over entity " + entity, e);
		}
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
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		try {
			ParsingPackage parsingPackage = loadParsingPackage(vo, entity, propertyName, propertyValue);
			if(parsingPackage.isApplyParser()){
				parsingPackage.voToEntity(getPropertyType(entity, propertyName));
			}
			getBeanUtilsBean().setProperty(entity, propertyName, parsingPackage.getPropertyToSet());		
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Error setting property "
				+ propertyName + " with value " + propertyValue
				+ " over entity " + entity, e);
		}
	}
	
	private ParsingPackage loadParsingPackage(ValueObject vo, HallocasaEntity entity,
			String propertyName, Object propertyValue) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Object propertyToSet = null;
		Class<?> voClazz = getPropertyType(vo, propertyName);
		Class<?> entClazz = getPropertyType(entity, propertyName);
		boolean equalClazz = voClazz != null && entClazz != null && voClazz.equals(entClazz);
		Class<?> parserEntClazz = clazzEquivalenceMap.get(voClazz);
		boolean parserExistent = parserEntClazz != null && parserEntClazz.equals(entClazz);
		boolean allowCasting = getWrapperTypes().contains(voClazz) && getWrapperTypes().contains(entClazz);
		if(equalClazz || allowCasting || parserExistent){
			propertyToSet = propertyValue;
		}
		return new ParsingPackage(propertyToSet, !equalClazz && parserExistent, getParser(voClazz));
	}

	/**
	 * 
	 * @param entity
	 * @param vo
	 * @param options
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void copyEntityToVO(HallocasaEntity entity, ValueObject vo)
			throws IllegalAccessException, InvocationTargetException {
		try {
			Map<String, Object> entityProperties = PropertyUtils.describe(entity);
			for (String propertyName : entityProperties.keySet()) {
				if (!ignoreFields.contains(propertyName)) {
					copyEntityPropertyToVoProperty(vo, entity, propertyName, entityProperties.get(propertyName));
				}
			}
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds the field to the ignore list, so it will not going to have into
	 * account in the parsing process
	 * 
	 * @param fieldName
	 */
	public void addIgnoreField(String fieldName) {
		ignoreFields.add(fieldName);
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
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public ValueObject toValueObject(HallocasaEntity entity, Class<?> clazz) {
		if (entity == null) {
			return null;
		}
		try {
			Object valueObject = clazz.newInstance();
			copyEntityToVO(entity, (ValueObject) valueObject);
			return (ValueObject) valueObject;
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public BeanUtilsBean getBeanUtilsBean() {
		if (beanUtilsBean == null) {
			beanUtilsBean = new BeanUtilsBean();
		}
		return beanUtilsBean;
	}
}
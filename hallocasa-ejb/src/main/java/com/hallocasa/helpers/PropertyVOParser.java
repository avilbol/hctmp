package com.hallocasa.helpers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.hallocasa.commons.annotations.PropertyFieldValueParser;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.properties.PropertyBasicInfo;
import com.hallocasa.commons.vo.properties.PropertyImageInfo;
import com.hallocasa.commons.vo.properties.PropertyLocationInfo;
import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyProposalVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.Country;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.dataentities.app.properties.PropertyFieldValue;
import com.hallocasa.dataentities.app.properties.PropertyLocation;
import com.hallocasa.dataentities.app.properties.PropertyProposal;
import com.hallocasa.dataentities.app.properties.PropertyType;

/**
 * Parser of propertyVO to property and vice-versa
 * 
 * @author Alexander Villamil
 * @since 1.7
 */
public class PropertyVOParser {

	/**
	 * Constructor
	 */
	public PropertyVOParser() {
		super();
	}

	static PropertyVOParser mainInstance;

	static {
		mainInstance = new PropertyVOParser();
	}

	public static PropertyVOParser getInstance() {
		return mainInstance;
	}

	/* static fields */

	/* instance variables */

	/* constructors */

	public List<PropertyVO> toValueObject(List<Property> entityList) {
		List<PropertyVO> propertyList = new ArrayList<>();
		for (Property property : entityList) {
			propertyList.add(toValueObject(property));
		}
		return propertyList;
	}

	public List<Property> toEntity(List<PropertyVO> voList) {
		List<Property> propertyList = new ArrayList<>();
		for (PropertyVO propertyVO : voList) {
			propertyList.add(toEntity(propertyVO));
		}
		return propertyList;
	}

	/* Methods */
	public PropertyVO toValueObject(Property entity) {
		try {
			PropertyVO vo = new PropertyVO();
			setupKeyInfo(vo, entity);
			Map<Integer, PropertyFieldValue> fieldValueList = loadFVMap(entity
					.getFieldValueList());
			setupBasicInfo(vo.getPropertyBasicInfo(), fieldValueList);
			setupLocationInfo(vo.getPropertyLocationInfo(), fieldValueList);
			setupImageInfo(vo.getPropertyImageInfo(), fieldValueList);
			return vo;
		} catch (NoSuchMethodException | SecurityException
				| IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			throw new RuntimeException("Error when translating entity "
					+ "property to value Object", e);
		}
	}

	public Property toEntity(PropertyVO vo) {
		try {
			Property property = new Property();
			setupKeyInfo(property, vo);
			Map<Integer, PropertyFieldValue> fieldValueList = new HashMap<>();
			setupBasicInfo(fieldValueList, vo.getPropertyBasicInfo(),
					vo.getId());
			setupLocationInfo(fieldValueList, vo.getPropertyLocationInfo(),
					vo.getId());
			setupImageInfo(fieldValueList, vo.getPropertyImageInfo(),
					vo.getId());
			property.setFieldValueList(new ArrayList<PropertyFieldValue>(
					fieldValueList.values()));
			return property;
		} catch (NoSuchMethodException | SecurityException
				| IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			throw new RuntimeException(
					"Error when translating property value object "
							+ "to entity", e);
		}
	}

	private Map<Integer, PropertyFieldValue> loadFVMap(
			List<PropertyFieldValue> fieldValueList) {
		Map<Integer, PropertyFieldValue> fvMap = new HashMap<>();
		for (PropertyFieldValue fvalue : fieldValueList) {
			fvMap.put(fvalue.getPropertyField().getId(), fvalue);
		}
		return fvMap;
	}

	private void setupImageInfo(PropertyImageInfo propertyImageInfo,
			Map<Integer, PropertyFieldValue> fieldValueList)
			throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		copyFvaluesToVO(PropertyImageInfo.class, fieldValueList,
				propertyImageInfo);
	}

	private void setupLocationInfo(PropertyLocationInfo propertyLocationInfo,
			Map<Integer, PropertyFieldValue> fieldValueList)
			throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		copyFvaluesToVO(PropertyLocationInfo.class, fieldValueList,
				propertyLocationInfo);
	}

	private void setupBasicInfo(PropertyBasicInfo propertyBasicInfo,
			Map<Integer, PropertyFieldValue> fieldValueList)
			throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		copyFvaluesToVO(PropertyBasicInfo.class, fieldValueList,
				propertyBasicInfo);
	}

	private void setupImageInfo(
			Map<Integer, PropertyFieldValue> fieldValueList,
			PropertyImageInfo propertyImageInfo, String propertyId)
			throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		copyVOToFValues(PropertyImageInfo.class, fieldValueList,
				propertyImageInfo, propertyId);
	}

	private void setupLocationInfo(
			Map<Integer, PropertyFieldValue> fieldValueList,
			PropertyLocationInfo propertyLocationInfo, String propertyId)
			throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		copyVOToFValues(PropertyLocationInfo.class, fieldValueList,
				propertyLocationInfo, propertyId);
	}

	private void setupBasicInfo(
			Map<Integer, PropertyFieldValue> fieldValueList,
			PropertyBasicInfo propertyBasicInfo, String propertyId)
			throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		copyVOToFValues(PropertyBasicInfo.class, fieldValueList,
				propertyBasicInfo, propertyId);
	}

	private void setupKeyInfo(PropertyVO vo, Property entity)
			throws IllegalAccessException, InvocationTargetException {
		vo.setId(entity.getId());
		vo.setCountry(new CountryVO());
		BeanUtils.copyProperties(vo.getCountry(), entity.getCountry());
		vo.setPropertyLocation(new PropertyLocationVO());
		BeanUtils.copyProperties(vo.getPropertyLocation(),
				entity.getPropertyLocation());
		vo.setPropertyProposal(new PropertyProposalVO());
		BeanUtils.copyProperties(vo.getPropertyProposal(),
				entity.getPropertyProposal());
		vo.setPropertyType(new PropertyTypeVO());
		ParsersContext.PROPERTY_TYPE_VO_PARSER.copyEntityToVO(
				entity.getPropertyType(), vo.getPropertyType());
	}

	private void setupKeyInfo(Property entity, PropertyVO vo)
			throws IllegalAccessException, InvocationTargetException {
		entity.setId(vo.getId());
		entity.setCountry(new Country());
		BeanUtils.copyProperties(entity.getCountry(), vo.getCountry());
		entity.setPropertyLocation(new PropertyLocation());
		BeanUtils.copyProperties(entity.getPropertyLocation(),
				vo.getPropertyLocation());
		entity.setPropertyProposal(new PropertyProposal());
		BeanUtils.copyProperties(entity.getPropertyProposal(),
				vo.getPropertyProposal());
		entity.setPropertyType(new PropertyType());
		ParsersContext.PROPERTY_TYPE_VO_PARSER.copyVOToEntity(
				vo.getPropertyType(), entity.getPropertyType());
	}

	public void copyFvaluesToVO(Class<? extends Object> clazz,
			Map<Integer, PropertyFieldValue> pfvalues, Object ob)
			throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Field[] fields = clazz.getDeclaredFields();
		MetadataPropertyParser mpparser = MetadataPropertyParser.getInstance();
		for (Field field : fields) {
			PropertyFieldValueParser ma = (PropertyFieldValueParser) field
					.getAnnotations()[0];
			String methodName = ma.methodToExecute();
			Method method = MetadataPropertyParser.class.getMethod(methodName,
					String.class);
			String propertyValue = pfvalues.get(ma.id()).getValue();
			field.setAccessible(true);
			field.set(ob, method.invoke(mpparser, propertyValue));
		}
	}

	public void copyVOToFValues(Class<? extends Object> clazz,
			Map<Integer, PropertyFieldValue> pfvalues, Object ob,
			String propertyId) throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Field[] fields = clazz.getDeclaredFields();
		MetadataPropertyParser mpparser = MetadataPropertyParser.getInstance();
		for (Field field : fields) {
			PropertyFieldValueParser ma = (PropertyFieldValueParser) field
					.getAnnotations()[0];
			String methodName = ma.methodToExecute();
			Method method = MetadataPropertyParser.class.getMethod(methodName,
					field.getType());
			field.setAccessible(true);
			String value = (String) method.invoke(mpparser, field.get(ob));
			PropertyFieldValue pfv = PropertyFieldValue.loadInstance(
					propertyId, ma.id(), value);
			pfvalues.put(ma.id(), pfv);
		}
	}
}

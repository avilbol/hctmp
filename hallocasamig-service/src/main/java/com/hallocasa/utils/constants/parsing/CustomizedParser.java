package com.hallocasa.utils.constants.parsing;

import java.util.ArrayList;
import java.util.List;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.i.ValueObject;

public abstract class CustomizedParser implements Parser<ValueObject, HallocasaEntity> {

	private List<String> fieldsToIgnore;

	@Override
	public HallocasaEntity toEntity(ValueObject valueObject, Class<?> clazz) {
		if (valueObject == null)
			return null;
		clear();
		initialize();
		DefaultParser deeperParser = new DefaultParser(getFieldsToIgnore());
		HallocasaEntity entity = deeperParser.toEntity(valueObject, clazz);
		transform(entity, valueObject);
		return entity;
	}

	@Override
	public ValueObject toValueObject(HallocasaEntity entity, Class<?> clazz) {
		if (entity == null)
			return null;
		clear();
		initialize();
		DefaultParser deeperParser = new DefaultParser(getFieldsToIgnore());
		ValueObject valueObject = deeperParser.toValueObject(entity, clazz);
		transform(valueObject, entity);
		return valueObject;
	}

	public void clear() {
		fieldsToIgnore = null;
	}

	public abstract void initialize();

	public abstract void transform(ValueObject vo, HallocasaEntity ent);

	public abstract void transform(HallocasaEntity ent, ValueObject vo);

	public List<String> getFieldsToIgnore() {
		if (fieldsToIgnore == null) {
			fieldsToIgnore = new ArrayList<>();
		}
		return fieldsToIgnore;
	}

	public void setFieldsToIgnore(List<String> fieldsToIgnore) {
		this.fieldsToIgnore = fieldsToIgnore;
	}

	public void addIgnoreVOField(String fieldName) {
		getFieldsToIgnore().add(fieldName);
	}
}

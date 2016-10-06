package com.hallocasa.utils.constants.parsing;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.i.ValueObject;

public class ParsingPackage {

	private Object propertyToSet;

	private boolean applyParser;
	
	private boolean ignore;

	private Parser<ValueObject, HallocasaEntity> parser;

	public ParsingPackage(Object propertyToSet, boolean applyParser, Parser<ValueObject, HallocasaEntity> parser) {
		super();
		this.propertyToSet = propertyToSet;
		this.applyParser = applyParser;
		this.parser = parser;
	}

	public Object getPropertyToSet() {
		return propertyToSet;
	}

	public void setPropertyToSet(Object propertyToSet) {
		this.propertyToSet = propertyToSet;
	}
	
	public void voToEntity(Class<?> clazz){
		this.propertyToSet = this.getParser().toEntity((ValueObject) this.propertyToSet, clazz);
	}
	
	public void entityToVo(Class<?> clazz){
		this.propertyToSet = this.getParser().toValueObject((HallocasaEntity) this.propertyToSet, clazz);
	}

	public boolean isApplyParser() {
		return applyParser;
	}

	public void setApplyParser(boolean applyParser) {
		this.applyParser = applyParser;
	}

	public Parser<ValueObject, HallocasaEntity> getParser() {
		return parser;
	}

	public void setParser(Parser<ValueObject, HallocasaEntity> parser) {
		this.parser = parser;
	}

	public boolean ignore() {
		return ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}
	
	
}

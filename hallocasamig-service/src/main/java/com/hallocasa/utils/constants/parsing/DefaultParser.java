package com.hallocasa.utils.constants.parsing;

import static org.apache.commons.beanutils.BeanUtils.copyProperties;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.i.ValueObject;

/**
 * Class to implement the parse between vo's and entities
 * @author avillamil
 *
 * @param <U>
 * @param <V>
 */
public class DefaultParser implements Parser<ValueObject, HallocasaEntity> {

	@Override
	public ValueObject toValueObject(HallocasaEntity entity, Class<?> clazz) {
		try{
			ValueObject valueObject = (ValueObject) clazz.newInstance();
			copyProperties(valueObject, entity);
			return valueObject;
		} catch(Exception e){
			throw new FatalException("Ha ocurrido un error inesperado", e);
		} 
	}

	@Override
	public HallocasaEntity toEntity(ValueObject valueObject, Class<?> clazz) {
		try{
			HallocasaEntity entity = clazz.newInstance();
			System.out.println(valueObject.);
			copyProperties(entity, valueObject);
			return entity;
		} catch(Exception e){
			throw new FatalException("Ha ocurrido un error inesperado", e);
		} 
	}
}

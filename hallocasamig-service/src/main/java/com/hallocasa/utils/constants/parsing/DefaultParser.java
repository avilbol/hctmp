package com.hallocasa.utils.constants.parsing;

import static org.apache.commons.beanutils.BeanUtils.copyProperties;

import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.utils.constants.parsing.i.Parser;

/**
 * Class to implement the parse between vo's and entities
 * @author avillamil
 *
 * @param <U>
 * @param <V>
 */
public class DefaultParser<U, V> implements Parser<U,V> {

	/**
	 * {@inheritDoc}
	 */
	public V toEntity(U valueObject, Class<V> clazz){
		try{
			V entity = clazz.newInstance();
			copyProperties(entity, valueObject);
			return entity;
		} catch(Exception e){
			throw new FatalException("Ha ocurrido un error inesperado", e);
		} 
	}
	
	/**
	 * {@inheritDoc}
	 */
	public U toValueObject(V entity, Class<U> clazz){
		try{
			U valueObject = clazz.newInstance();
			copyProperties(valueObject, entity);
			return valueObject;
		} catch(Exception e){
			throw new FatalException("Ha ocurrido un error inesperado", e);
		} 
	}
}

package com.hallocasa.utils.constants.parsing;

import java.util.HashMap;
import java.util.Map;

import com.hallocasa.entities.EntityExample;
import com.hallocasa.entities.security.EntityAuthorizationCode;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.Example;
import com.hallocasa.vo.security.AuthorizationCode;

public class HallocasaConvert <U, V> {

	public static Map<Class<?>, Class<?>> clazzEquivalenceMap = new HashMap<>();
	
	public static Map<Class<?>, Parser<?, ?>> parserMap = new HashMap<>();
	
	/**
	 * Place here equivalences between entities and value objects
	 */
	static{
		 clazzEquivalenceMap.put(Example.class, EntityExample.class);
		 clazzEquivalenceMap.put(AuthorizationCode.class, EntityAuthorizationCode.class);
	}
	
	/**
	 * Place here customized parsers
	 */
	static{
		// TODO : Place here customized parsers
	}
	
	@SuppressWarnings("unchecked")
	public static <U, V> V toEntity(U vo){
		Class<V> entityEquivalence = (Class<V>) clazzEquivalenceMap.get(vo.getClass());
		if(entityEquivalence == null){
			throw new FatalException("Imposible determinar la entidad asociada al Value Object " 
					+ vo.getClass().getName());
		}
		Parser<U, V> parser = (Parser<U, V>) parserMap.get(vo.getClass());
		if(parser == null){
			parser = new DefaultParser<>();
		}
		return parser.toEntity(vo, entityEquivalence);
	}
	
	@SuppressWarnings("unchecked")
	public static <U, V> U toValueObject(V entity){
		if (entity == null){
			return null;
		}
		Class<U> voEquivalence = null;
		for(Class<?> valueObject : clazzEquivalenceMap.keySet()){
			Class<U> value = (Class<U>) valueObject;
			if(clazzEquivalenceMap.get(value).equals(entity.getClass())){
				voEquivalence = value;
			}
		}
		if(voEquivalence == null){
			throw new FatalException("Imposible determinar el value object asociado a la entidad " 
					+ entity.getClass().getName());
		}
		Parser<U, V> parser = (Parser<U, V>) parserMap.get(voEquivalence);
		if(parser == null){
			parser = new DefaultParser<>();
		}
		return parser.toValueObject(entity, voEquivalence);
	}
	
	public static void main(String[] args){
		HallocasaConvert.<Example, EntityExample>toValueObject(new EntityExample());
	}
	
}

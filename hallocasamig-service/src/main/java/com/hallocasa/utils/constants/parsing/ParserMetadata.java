package com.hallocasa.utils.constants.parsing;

import java.util.HashMap;
import java.util.Map;

import com.hallocasa.entities.EntityExample;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.entities.security.EntityAuthorizationCode;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.Example;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.security.AuthorizationCode;

public class ParserMetadata {

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
		parserMap.put(Example.class, new DeeperParser());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <U,V> Parser<U, V> getParser(Class clazz){
		Parser<U, V> parser = (Parser<U, V>) parserMap.get(clazz);
		return parser != null ? parser : (Parser<U, V>) new DefaultParser();
	}
	
	public static void main(String[] args){
		Parser<ValueObject, HallocasaEntity> parser = ParserMetadata.<ValueObject, HallocasaEntity>getParser(Example.class);
		Example example = new Example();
		example.setDescription("desc");
		example.setIdentifier(2);
		EntityExample entityExample = (EntityExample) parser.toEntity((ValueObject)new Example(), EntityExample.class);
		System.out.println(entityExample.getDescription());
	}
}

package com.hallocasa.utils.constants.parsing;

import java.util.HashMap;
import java.util.Map;

import com.hallocasa.entities.EntityExample;
import com.hallocasa.entities.security.EntityAuthorizationCode;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.Example;
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
		// TODO : Place here customized parsers
		parserMap.put(Example.class, new DeeperParser<Example, EntityExample>());
	}
	
}

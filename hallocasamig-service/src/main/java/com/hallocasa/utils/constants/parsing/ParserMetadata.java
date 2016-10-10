package com.hallocasa.utils.constants.parsing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hallocasa.entities.EntityComposedExample;
import com.hallocasa.entities.EntityExample;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.entities.security.EntityAuthorizationCode;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.ComposedExample;
import com.hallocasa.vo.Example;
import com.hallocasa.vo.User;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.security.AuthorizationCode;

public class ParserMetadata {

	public static Map<Class<?>, Class<?>> clazzEquivalenceMap = new HashMap<>();

	public static Map<Class<?>, Parser<?, ?>> parserMap = new HashMap<>();

	/**
	 * Place here equivalences between entities and value objects (required)
	 */
	static {
		clazzEquivalenceMap.put(Example.class, EntityExample.class);
		clazzEquivalenceMap.put(AuthorizationCode.class, EntityAuthorizationCode.class);
		clazzEquivalenceMap.put(ComposedExample.class, EntityComposedExample.class);
		clazzEquivalenceMap.put(User.class, EntityUser.class);
	}

	/**
	 * Place here customized parsers (according to needed)
	 */
	static {
		parserMap.put(ComposedExample.class, new ComposedExampleParser());
	}
	
	public static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        ret.add(String.class);
        return ret;
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <U, V> Parser<U, V> getParser(Class clazz) {
		Parser<U, V> parser = (Parser<U, V>) parserMap.get(clazz);
		return parser != null ? parser : (Parser<U, V>) new DefaultParser();
	}

	public static void main(String[] args) {
		Parser<ValueObject, HallocasaEntity> parser = ParserMetadata.getParser(Example.class);
		Example example = new Example();
		example.setDescription("desc");
		example.setIdentifier(2);
		EntityExample entityExample = (EntityExample) parser.toEntity((ValueObject) example, EntityExample.class);
		System.out.println(entityExample.getIdentifier());
	}
}

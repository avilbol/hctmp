package com.hallocasa.commons.vo.helpers;

import org.apache.commons.beanutils.Converter;

import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * @author David Mantilla
 *
 * @param <T> Class of the entity
 * @param <U> Class of the value object
 * @param <R> Class of the parser used to conversion
 * @since 1.7
 */
public class GenericVOEntityConverter<T extends HallocasaEntity, U extends ValueObject, R extends GenericVOParser<T, U>>
	implements Converter {

	private R parser;
	private final Class<T> entityClass;
	private final Class<U> voClass;

	/**
	 * Constructor
	 * 
	 * @param parserClass
	 * @param entityClass
	 * @param voClass
	 */
	public GenericVOEntityConverter(Class<R> parserClass, Class<T> entityClass,
		Class<U> voClass) {
		this.entityClass = entityClass;
		this.voClass = voClass;
		try {
			parser = parserClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class,
	 * java.lang.Object)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		}

		// destination class:VO, that means entity to VO conversion
		if (voClass.isAssignableFrom(type)) {
			if (entityClass.isAssignableFrom(value.getClass()) ) {
				T entity = (T) value;
				return parser.toValueObject(entity, voClass, null);
			} else {
				return (U) value;
			}
		}
		// destination class: Entity, that means VO to entity conversion
		else if (entityClass.isAssignableFrom(type)) {
			if (voClass.isAssignableFrom(value.getClass())) {
				U vo = (U) value;
				return parser.toEntity(vo, entityClass, null);
			} else {
				return (T) value;
			}
		}
		return value;
	}

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}

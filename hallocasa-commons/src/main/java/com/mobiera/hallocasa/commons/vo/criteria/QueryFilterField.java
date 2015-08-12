package com.mobiera.hallocasa.commons.vo.criteria;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for building query sentences from beans
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryFilterField {

	/**
	 * Field of the entity where it is going to filer over
	 */
	public String[] field();

	/**
	 * Operation of the filter
	 */
	public FilterOperation operation() default FilterOperation.EQUALS;

}

package com.hallocasa.validators;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation for NotEmpty validator
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = IpValidator.class)
@Documented
public @interface Ip {

	/**
	 * @return message of the annotation
	 */
	String message() default "{" + ValidationMessages.IP + "}";

	/**
	 * @return groups
	 */
	Class<?>[] groups() default {};

	/**
	 * @return payload class
	 */
	Class<? extends Payload>[] payload() default {};

	/**
	 * 
	 * @return wildcard
	 */
	boolean wildCard() default false;

}
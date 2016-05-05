package com.hallocasa.commons.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyFieldValueParser {

	int id();
	
	String methodToExecute();
}

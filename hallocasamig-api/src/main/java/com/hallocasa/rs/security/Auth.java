package com.hallocasa.rs.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

/**
 * Annotation for api requests that need authorization. They will need Authorization-code Oauth 2.0 scheme
 * @author avillamil
 */
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Auth { }
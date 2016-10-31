package com.hallocasa.rs.config;

import java.util.Set;

import javax.ws.rs.ApplicationPath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class HallocasaAppResourceConfig extends ResourceConfig {

	/**
	 * Log de la clase.
	 */
	private static final Logger LOG = LogManager.getLogger(HallocasaAppResourceConfig.class);

	/**
	 * Constructor por defecto.
	 */
	public HallocasaAppResourceConfig() {
		super();
		packages("io.swagger.jaxrs.json");
        packages("io.swagger.jaxrs.listing");
		LOG.info("Inicio de la configuraci\u00F3n de la aplicaci\u00F3n.");
		LOG.info("Registrando recursos..");
		packages("com.hallocasa.rs");
		LOG.info("Registrando filtros...");
		register(com.hallocasa.rs.security.AuthenticationFilter.class);
		register(com.hallocasa.rs.security.AuthorizationFilter.class);
		LOG.info("Registrando exception mappers...");
		// Exception mappers
		register(com.hallocasa.rs.mapper.SecurityExceptionMapper.class);
		register(com.hallocasa.rs.mapper.GenericExceptionMapper.class);
		LOG.info("Registrando features...");
		// Features
		LOG.info("Aplicaci\u00F3n configurada correctamente.");
	}

	/**
	 * @param classes c
	 */
	public HallocasaAppResourceConfig(Set<Class<?>> classes) {
		super(classes);
	}

	/**
	 * @param classes c
	 */
	public HallocasaAppResourceConfig(Class<?>... classes) {
		super(classes);
	}

	/**
	 * @param original c
	 */
	public HallocasaAppResourceConfig(ResourceConfig original) {
		super(original);
	}
	
}

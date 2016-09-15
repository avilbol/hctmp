package com.hallocasa.rs;

import org.glassfish.jersey.server.ResourceConfig;

import com.hallocasa.rs.example.ExampleResource;

import org.glassfish.jersey.jackson.JacksonFeature;

public class HallocasaApp extends ResourceConfig {

	public HallocasaApp(){
		register(JacksonFeature.class);
		register(ExampleResource.class);
	}	
}

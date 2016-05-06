package com.hallocasa.commons.system;

import java.io.IOException;
import java.util.Properties;

public class SystemProperty {

	private static final Properties properties = new Properties() ;
	
	static {
		try {
			properties.load(SystemProperty.class.getClassLoader()
					.getResourceAsStream("config.properties"));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static String get(String property){  
		return properties.getProperty(property);
	}
}

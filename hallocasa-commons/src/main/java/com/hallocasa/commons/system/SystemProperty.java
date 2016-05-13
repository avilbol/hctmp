package com.hallocasa.commons.system;

import java.io.IOException;
import java.util.Properties;

public class SystemProperty {

	private static final Properties properties = new Properties() ;
	
	public static final Properties dbMaintainProperties = new Properties() ;
	
	static {
		try {
			properties.load(SystemProperty.class.getClassLoader()
					.getResourceAsStream("config.properties"));
			dbMaintainProperties.load(SystemProperty.class.getClassLoader()
					.getResourceAsStream("dbmaintain.properties"));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static String get(String property){  
		return properties.getProperty(property);
	}
}

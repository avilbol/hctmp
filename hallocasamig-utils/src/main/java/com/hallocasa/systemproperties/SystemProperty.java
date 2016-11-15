package com.hallocasa.systemproperties;

import java.io.IOException;
import java.util.Properties;

import com.hallocasa.utils.constants.exceptions.FatalException;

public class SystemProperty {
	
	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(SystemProperty.class.getClassLoader()
					.getResourceAsStream("hallocasamig-utils.properties"));
		} catch (IOException ex) {
			throw new FatalException("Unexpected error", ex);
		}
	}

	public static String get(String property) {
		return properties.getProperty(property);
	}
}

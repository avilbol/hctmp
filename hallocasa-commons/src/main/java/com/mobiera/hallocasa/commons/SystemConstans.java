package com.mobiera.hallocasa.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * All constants of the system, like all configuration stuff
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class SystemConstans {
	/* static fields */

	static {
		Properties prop = new Properties();
		InputStream inputStream = SystemConstans.class.getClassLoader()
			.getResourceAsStream("social.properties");

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(
					"unable to open property file 'social.properties' in the classpath",
					e);
			}
		} else {
			throw new RuntimeException(
				"property file 'social.properties' not found in the classpath");
		}

		// get properties
		VERSION = prop.getProperty("com.mobiera.social.version");
	}

	/**
	 * This is the social application version
	 */
	public static final String VERSION;

	/* Methods */

	/* Getters & Setters */
}

package com.mobiera.hallocasa.commons.singlesignon;

/**
 * Utilities for single sign on
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class SingleSignOnUtils {

	/* static fields */
	public enum Action {
		HOME,
		ACTIVATE;
	}

	/* instance variables */

	/* constructors */

	/* Methods */

	/**
	 * Builds the URL of external application which is able to receive and
	 * process single sing on token
	 * 
	 * @param baseUrl
	 * @param token
	 * @return The built URL
	 */
	public static final String buildTokenUrl(String baseUrl, String token) {
		StringBuilder str = new StringBuilder();
		str.append(baseUrl);
		if (baseUrl.contains("?")) {
			str.append("&");
		} else {
			str.append("?");
		}
		str.append("token=");
		str.append(token);
		return str.toString();
	}

	/**
	 * 
	 * @param baseUrl
	 * @param action
	 * @return The built URL
	 */
	public static final String buildActionUrl(String baseUrl, Action action) {
		StringBuilder str = new StringBuilder();
		str.append(baseUrl);
		if (baseUrl.contains("?")) {
			str.append("&");
		} else {
			str.append("?");
		}
		str.append("action");
		str.append("=");
		str.append(action.name().toLowerCase());
		return str.toString();
	}

	/* Getters & Setters */
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.exceptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hallocasa.commons.Language;

/**
 * This class represents the exception that is generated always there is a
 * service exception
 *
 * @author David Mantilla
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 3286417646443153318L;
	private String messageKey;

	/**
	 * @param message
	 * @param summary
	 * @param detail
	 * @param cause
	 */
	public ServiceException(String message, String messageKey, Throwable cause) {
		super(message, cause);
		this.messageKey = messageKey;
	}

	/**
	 * Returns the message in a specific language
	 *
	 * @param language
	 * @return
	 */
	public String getLocalizedMessage(Language language) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		String value = null;
		if (cl != null) {
			InputStream in = cl
					.getResourceAsStream("com/hallocasa/language/ServicesMessages_"
							+ language.name() + ".properties");
			if (in != null) {
				Properties props;
				try {
					props = new Properties();
					props.load(in);
					value = props.getProperty(messageKey);
					in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				} finally {
					//
				}
			}
		}
		if (value == null) {
			throw new RuntimeException("Unable to load the message"
					+ messageKey);
		}
		return value;
	}
}

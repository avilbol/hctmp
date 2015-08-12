package com.mobiera.hallocasa.commons.i18n;

import java.util.Locale;

import javax.validation.MessageInterpolator;

/**
 * Message Interpolator wrapper
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class SocialMessageInterpolator implements MessageInterpolator {

	private MessageInterpolator messageInterpolator;
	private Locale locale;

	/**
	 * Constructor
	 * 
	 * @param messageInterpolator
	 * @param locale used to build message
	 */
	public SocialMessageInterpolator(MessageInterpolator messageInterpolator,
		Locale locale) {
		this.messageInterpolator = messageInterpolator;
		this.locale = locale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.MessageInterpolator#interpolate(java.lang.String,
	 * javax.validation.MessageInterpolator.Context)
	 */
	@Override
	public String interpolate(String messageTemplate, Context context) {
		return this.messageInterpolator.interpolate(messageTemplate, context,
			this.locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.MessageInterpolator#interpolate(java.lang.String,
	 * javax.validation.MessageInterpolator.Context, java.util.Locale)
	 */
	@Override
	public String interpolate(String messageTemplate, Context context,
		Locale locale) {
		return this.messageInterpolator.interpolate(messageTemplate, context,
			locale);
	}
	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}

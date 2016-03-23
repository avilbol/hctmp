package com.hallocasa.commons.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

/**
 * Messages handler for presentation layer
 *
 * @author David Mantilla
 * @since 1.7
 */
public class ValidationMessages {

    private static final String BUNDLE_NAME = "ValidationMessages"; //$NON-NLS-1$
    private static final Logger LOG = Logger.getLogger(ValidationMessages.class
            .getName());
    private static final Map<Locale, ResourceBundle> resourceBundleMap;

    public static final String ASSERT_FALSE = "javax.validation.constraints.AssertFalse.message";
    public static final String ASSERT_TRUE = "javax.validation.constraints.AssertTrue.message";
    public static final String DECIMAL_MAX = "javax.validation.constraints.DecimalMax.message";
    public static final String DECIMAL_MIN = "javax.validation.constraints.DecimalMin.message";
    public static final String DIGITS = "javax.validation.constraints.Digits.message";
    public static final String FUTURE = "javax.validation.constraints.Future.message";
    public static final String MAX = "javax.validation.constraints.Max.message";
    public static final String MIN = "javax.validation.constraints.Min.message";
    public static final String NOT_NULL = "javax.validation.constraints.NotNull.message";
    public static final String NULL = "javax.validation.constraints.Null.message";
    public static final String PAST = "javax.validation.constraints.Past.message";
    public static final String PATTERN = "javax.validation.constraints.Pattern.message";
    public static final String SIZE = "javax.validation.constraints.Size.message";
    public static final String EMAIL_PATTERN = "com.hallocasa.validator.constraints.EmailPattern.message";
    public static final String NOT_EMPTY = "com.hallocasa.validator.constraints.NotEmpty.message";
    public static final String IP = "com.hallocasa.validator.constrains.Ip.message";
    public static final String URL_PATTERN = "com.hallocasa.validator.constraints.UrlPattern.message";
    public static final String PHONE_PATTERN = "com.hallocasa.validator.constrains.PhonePattern.message";
    public static final String PASSWORD_PATTERN = "com.hallocasa.validator.constrains.PasswordPattern.message";
    public static final String GENERAL_NAME_PATTERN = "com.hallocasa.validator.constrains.GeneralNamePattern.message";
    public static final String LINKED_IN_PATTERN = "com.hallocasa.validator.constrains.LinkedInPattern.message";
    public static final String ADDRESS_PATTERN = "com.hallocasa.validator.constrains.AddressPattern.message";
    public static final String USE_CASE_NAME_PATTERN = "com.hallocasa.validator.constrains.UseCasePattern.message";
    public static final String MUST_ACCEPT_TERMS_AND_CONDITIONS = "com.hallocasa.validator.constraints.TermsAndConditions";
    
    /* sign up dialog */
    public static final String SIGNUP_PASSWORD_CONFIRM_NOT_MATCH = "com.hallocasa.signup.password.NotMatch.summary";
    public static final String RECOVERY_PASSWORD_CONFIRM_NOT_MATCH = "com.hallocasa.signup.password.NotMatch.summary";
    
    static {
        resourceBundleMap = new HashMap<Locale, ResourceBundle>();
    }

    /**
     * Return a string of the resource bundle given its key and the locale
     *
     * @param key
     * @param locale
     * @return founded string
     */
    public static String getString(String key, Locale locale) {
        try {
            return getResourceBundle(locale).getString(key);
        } catch (MissingResourceException e) {
            LOG.log(Level.WARNING,
                    "Message not found in ValidationMessages bundle: " + key
                    + " with locale " + locale, e);
            return "???" + key + "???";
        }
    }

    /**
     * Get resource bundle given a locale. If locale is not found English one is
     * given by default
     *
     * @param locale
     * @return
     */
    private static ResourceBundle getResourceBundle(Locale locale) {
        ResourceBundle resourceBundle = resourceBundleMap.get(locale);
        if (resourceBundle == null) {
            return loadResourceBundle(locale);
        }
        return resourceBundle;
    }

    /**
     * Return a string of the resource bundle given its key and params, taken
     * session
     *
     * @param key
     * @param locale
     * @param params
     * @return found string text
     */
    public static String getString(String key, Locale locale, Map<String, Object> params) {
        return interpolate(getString(key, locale), params);
    }

    /**
     * @param msg
     * @param params
     * @return found string text
     */
    public static String interpolate(final String msg, Map<String, Object> params) {

        if (params == null || StringUtils.isEmpty(msg)) {
            return msg;
        }

        String aux = msg;

        for (String key : params.keySet()) {
            aux = StringUtils.replace(aux, "{" + key + "}", String
                    .valueOf(params.get(key)));
        }

        return aux;
    }

    /**
     * Load a resource bundle by its locale and put it into the map
     *
     * @param locale
     * @return
     */
    synchronized private static ResourceBundle loadResourceBundle(Locale locale) {
        ResourceBundle resourceBundle = null;
        try {
            resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        } catch (MissingResourceException e) {
            LOG.log(Level.WARNING, "Language not supported", e);
            resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(
                    "en"));
        }
        resourceBundleMap.put(locale, resourceBundle);
        return resourceBundle;
    }
}

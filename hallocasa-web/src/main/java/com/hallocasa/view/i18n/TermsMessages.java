package com.hallocasa.view.i18n;

import com.hallocasa.model.session.WebSessionImpl;
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
 * @author Alexander Villamil
 * @since 1.7
 */
public class TermsMessages {

    private static final Logger LOG = Logger
            .getLogger(TermsMessages.class.getName());
    private static final Map<Locale, ResourceBundle> resourceBundleMap;
    public static final String FACES_BUNDLE_NAME = "viewTermsBundle";
    public static final String BUNDLE_NAME = "com.hallocasa.language.terms.TermsText";
  
     /* The Constant KEY_ARG_ZERO.
     */
    private static final String KEY_ARG_ZERO = "{0}";

    static {
        resourceBundleMap = new HashMap<>();
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
            return '!' + key + '!';
        }
    }

    /**
     * Return a string of the resource bundle given its key and taken session
     * current locale
     *
     * @param key
     * @return founded string
     */
    public static String getString(String key) {
        try {
            // LOG.warning("Getting message with key " + key + " in locale "
            // + SocialSessionImpl.getCurrent().getLocale().toString());
            return getResourceBundle(WebSessionImpl.getCurrent()
                    .getCurrentLanguage().getLocale()).getString(key);

        } catch (MissingResourceException e) {
            return "???" + key + "???";
        }
    }

    /**
     * Return a string of the resource bundle given its key and params, taken
     * session
     *
     * @param key
     * @param params
     * @return found string text
     */
    public static String getString(String key, Object... params) {
        return interpolate(getString(key), params);
    }

    /**
     * @param msg
     * @param args
     * @return found string text
     */
    public static String interpolate(final String msg, Object... args) {

        if (args == null || StringUtils.isEmpty(msg)
                || !StringUtils.contains(msg, KEY_ARG_ZERO)) {
            return msg;
        }

        String aux = msg;

        for (int i = 0; i < args.length; i++) {
            aux = StringUtils.replace(aux, "{" + i + "}", String
                    .valueOf(args[i]));
        }

        return aux;
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
     * Load a resource bundle by its locale and put it into the map
     *
     * @param locale
     * @return
     */
    synchronized private static ResourceBundle loadResourceBundle(Locale locale) {
        ResourceBundle resourceBundle;
        try {
            LOG.log(Level.WARNING, "Getting bundle {0}", locale.toString());
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

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
 * @author David Mantilla
 * @since 1.7
 */
public class Messages {

    private static final Logger LOG = Logger
            .getLogger(Messages.class.getName());
    private static final Map<Locale, ResourceBundle> resourceBundleMap;

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
    public static final String LOGIN_INVALID_EMAIL_MESSAGE = "SocialSession.Login.InvalidEmail.Message";
    public static final String LOGIN_INVALID_PASSWORD_MESSAGE = "SocialSession.Login.InvalidPassword.Message";
    public static final String LOGIN_INVALID_IP_MESSAGE = "SocialSession.Login.InvalidIP.Message";
    public static final String LOGIN_INACTIVE_USER_MESSAGE = "SocialSession.Login.InactiveUser.Message";
    public static final String UNEXPECTED_ERROR = "Common.UnexpectedError.Message";
    public static final String PASSWORD_NOT_MATCH = "PassRecoveryPresenter.PasswordNotMatch.Message";
    public static final String PASSWORD_CHANGED_SUCCESS = "PassRecoveryPresenter.PasswordChangedSuccess.Message";
    public static final String USER_ACTIVATION_SUCCESS = "UserListPresenter.UserActivationSuccess.Message";
    public static final String USER_INACTIVATION_SUCCESS = "UserListPresenter.UserInActivationSuccess.Message";
    public static final String PARTNER_UPDATE_SUCCESS = "MyCompanyPresenter.PartnerUpdateSuccess.Message";
    public static final String OPERATION_UPDATE_SUCCESS = "MyCompanyPresenter.OperationUpdateSuccess.Message";

    /**
     * The Constant KEY_ARG_ZERO.
     */
    private static final String KEY_ARG_ZERO = "{0}";

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
            return getResourceBundle(WebSessionImpl.getCurrent().getLocale())
                    .getString(key);

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
        ResourceBundle resourceBundle = null;
        try {
            LOG.warning("Getting bundle " + locale.toString());
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

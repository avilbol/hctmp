package com.hallocasa.commons.exceptions.services;

import com.hallocasa.commons.Language;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author davidmantilla
 *
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = -3967833786959837716L;
    private String messageKey;

    /**
     * Default constructor
     */
    public ServiceException() {
        super();
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ServiceException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message
     * @param cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

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

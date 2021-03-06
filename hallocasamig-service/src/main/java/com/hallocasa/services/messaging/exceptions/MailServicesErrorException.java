package com.hallocasa.services.messaging.exceptions;

/**
 * @author David Mantilla
 * @since 1.7
 */
public class MailServicesErrorException extends Exception {

    private static final long serialVersionUID = 6188300498691533241L;

    /**
     * Constructor
     */
    public MailServicesErrorException() {
    }

    /**
     * Constructor
     *
     * @param message
     */
    public MailServicesErrorException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param cause
     */
    public MailServicesErrorException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     */
    public MailServicesErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public MailServicesErrorException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    /* static fields */

    /* instance variables */

    /* constructors */

    /* Methods */

    /* Getters & Setters */
}

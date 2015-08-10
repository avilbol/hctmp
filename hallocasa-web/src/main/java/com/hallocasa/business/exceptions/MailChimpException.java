package com.hallocasa.business.exceptions;

/**
 * Exceptions of the mail chimp services
 * 
 * @author davidmantilla
 *
 */
public class MailChimpException extends RuntimeException {

    private static final long serialVersionUID = 6108577176935516046L;

    public MailChimpException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param message
     */
    public MailChimpException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public MailChimpException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public MailChimpException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public MailChimpException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}

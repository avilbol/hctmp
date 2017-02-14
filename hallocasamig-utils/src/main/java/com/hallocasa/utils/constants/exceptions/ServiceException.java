package com.hallocasa.utils.constants.exceptions;

public class ServiceException extends RuntimeException {

	public static final Integer NOT_FOUND_STATUS = 404;
	
    private static final long serialVersionUID = -3967833786959837716L;
    private int httpStatus;
    
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
    
    public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ServiceException(String message, int httpStatus) {
		super(message);
		this.setHttpStatus(httpStatus);
	}
}

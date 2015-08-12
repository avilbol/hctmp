package com.mobiera.hallocasa.commons.exceptions.services;

public class EntityIntegrityViolationException extends Exception {

	/* static fields */

	/* instance variables */
	private static final long serialVersionUID = -4682489800281484796L;

	private String parentEntity;

	private String childEntity;

	private Object idEntity;
	
	private String message;

	/* constructors */

	/**
	 * Constructor
	 */
	public EntityIntegrityViolationException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public EntityIntegrityViolationException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public EntityIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public EntityIntegrityViolationException(String message) {
		super(message);
	}

	
	/**
	 * Constructor
	 * @param message
	 * @param partnerEntity
	 * @param relationshipEntity
	 * @param idEntity
	 */
	public EntityIntegrityViolationException(String message,
		String parentEntity, String childEntity, Object idEntity) {
		super(message);
		this.message = message;
		this.parentEntity = parentEntity;
		this.childEntity = childEntity;
		this.idEntity = idEntity;
		builderMessage(message);
		
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public EntityIntegrityViolationException(Throwable cause) {
		super(cause);
	}

	/* Methods */

	/* Getters & Setters */
	
	/**
	 * @return
	 */
	public String getParentEntity() {
		return parentEntity;
	}

	/**
	 * @param parentEntity
	 */
	public void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}
	
	/**
	 * @return
	 */
	public Object getIdEntity() {
		return idEntity;
	}
	
	/**
	 * @param idEntity
	 */
	public void setIdEntity(Object idEntity) {
		this.idEntity = idEntity;
	}

	/**
	 * @return
	 */
	public String getChildEntity() {
		return childEntity;
	}

	/**
	 * @param childEntity
	 */
	public void setChildEntity(String childEntity) {
		this.childEntity = childEntity;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param message
	 */
	private void builderMessage(String message){		
		StringBuilder builderMessage = new StringBuilder(message);
		builderMessage.append(" Partner entity: ");
		builderMessage.append(getParentEntity());
		builderMessage.append(" Child entity: ");
		builderMessage.append(getChildEntity());
		builderMessage.append(" Id entity: ");
		builderMessage.append(getIdEntity());
		this.setMessage(builderMessage.toString());
	}
}

package com.hallocasa.commons.vo;

import com.hallocasa.commons.vo.enums.PartnershipStatus;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Partnership Value Object
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class PartnershipVO implements ValueObject {

	private static final long serialVersionUID = 8515502185136030721L;

	/* static fields */
	public static final String appId_ = "appId";

	/* instance variables */
	private Long id;
	private String description;
	private OperationVO fromOperation;
	private OperationVO toOperation;
	private PartnershipStatus partnershipStatus;
	private Long appId;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public PartnershipVO() {
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public PartnershipVO(Long id) {
		super();
		this.id = id;
	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for description
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for fromOperationVO
	 * 
	 * @return the fromOperationVO
	 */
	public OperationVO getFromOperation() {
		return fromOperation;
	}

	/**
	 * Setter for fromOperationVO
	 * 
	 * @param fromOperationVO the fromOperationVO to set
	 */
	public void setFromOperation(OperationVO fromOperationVO) {
		this.fromOperation = fromOperationVO;
	}

	/**
	 * Getter for toOperationVO
	 * 
	 * @return the toOperationVO
	 */
	public OperationVO getToOperation() {
		return toOperation;
	}

	/**
	 * Setter for toOperationVO
	 * 
	 * @param toOperationVO the toOperationVO to set
	 */
	public void setToOperation(OperationVO toOperationVO) {
		this.toOperation = toOperationVO;
	}

	/**
	 * Getter for partnershipStatus
	 * 
	 * @return the partnershipStatus
	 */
	public PartnershipStatus getPartnershipStatus() {
		return partnershipStatus;
	}

	/**
	 * Setter for partnershipStatus
	 * 
	 * @param partnershipStatus the partnershipStatus to set
	 */
	public void setPartnershipStatus(PartnershipStatus partnershipStatus) {
		this.partnershipStatus = partnershipStatus;
	}

	/**
	 * Getter for appId
	 * 
	 * @return the appId
	 */
	public Long getAppId() {
		return appId;
	}

	/**
	 * Setter for appId
	 * 
	 * @param appId the appId to set
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}

}

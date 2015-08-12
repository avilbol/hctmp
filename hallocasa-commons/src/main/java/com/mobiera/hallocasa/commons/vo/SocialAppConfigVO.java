package com.mobiera.hallocasa.commons.vo;

import com.mobiera.hallocasa.commons.vo.interfaces.ValueObject;

public class SocialAppConfigVO implements ValueObject {

	/* static fields */
	private static final long serialVersionUID = -7825734618887714945L;

	/* instance variables */
	private String filesPath;
	private String mailServiceJndi;
	private String xadiskServiceJndi;
	private String filesServerContext;
	private String appServerContext;
	private String serverBaseUrl;
	private Long socialAppId;
	private Integer rowsPerTablePage;
	private Long defaultTimeZoneId;
	private Long registeredPartnerFeatureId;
	private Integer sizePassword;
	private Long defaultLanguageId;
	private Long defaultStandardProfileId;

	/* constructors */

	/**
	 * Constructor
	 */
	public SocialAppConfigVO() {
	}

	/* Methods */

	/* Methods */

	/* Getters & Setters */
	/**
	 * Getter for filesPath
	 * 
	 * @return the filesPath
	 */
	public String getFilesPath() {
		return filesPath;
	}

	/**
	 * Setter for filesPath
	 * 
	 * @param filesPath the filesPath to set
	 */
	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	/**
	 * Getter for mailServiceJndi
	 * 
	 * @return the mailServiceJndi
	 */
	public String getMailServiceJndi() {
		return mailServiceJndi;
	}

	/**
	 * Setter for mailServiceJndi
	 * 
	 * @param mailServiceJndi the mailServiceJndi to set
	 */
	public void setMailServiceJndi(String mailServiceJndi) {
		this.mailServiceJndi = mailServiceJndi;
	}

	/**
	 * Getter for xadiskServiceJndi
	 * 
	 * @return the xadiskServiceJndi
	 */
	public String getXadiskServiceJndi() {
		return xadiskServiceJndi;
	}

	/**
	 * Setter for xadiskServiceJndi
	 * 
	 * @param xadiskServiceJndi the xadiskServiceJndi to set
	 */
	public void setXadiskServiceJndi(String xadiskServiceJndi) {
		this.xadiskServiceJndi = xadiskServiceJndi;
	}

	/**
	 * Getter for filesServerContext
	 * 
	 * @return the filesServerContext
	 */
	public String getFilesServerContext() {
		return filesServerContext;
	}

	/**
	 * Setter for filesServerContext
	 * 
	 * @param filesServerContext the filesServerContext to set
	 */
	public void setFilesServerContext(String filesServerContext) {
		this.filesServerContext = filesServerContext;
	}

	/**
	 * Getter for appServerContext
	 * 
	 * @return the appServerContext
	 */
	public String getAppServerContext() {
		return appServerContext;
	}

	/**
	 * Setter for appServerContext
	 * 
	 * @param appServerContext the appServerContext to set
	 */
	public void setAppServerContext(String appServerContext) {
		this.appServerContext = appServerContext;
	}

	/**
	 * Getter for serverBaseUrl
	 * 
	 * @return the serverBaseUrl
	 */
	public String getServerBaseUrl() {
		return serverBaseUrl;
	}

	/**
	 * Setter for serverBaseUrl
	 * 
	 * @param serverBaseUrl the serverBaseUrl to set
	 */
	public void setServerBaseUrl(String serverBaseUrl) {
		this.serverBaseUrl = serverBaseUrl;
	}

	/**
	 * Getter for socialAppId
	 * 
	 * @return the socialAppId
	 */
	public Long getSocialAppId() {
		return socialAppId;
	}

	/**
	 * Setter for socialAppId
	 * 
	 * @param socialAppId the socialAppId to set
	 */
	public void setSocialAppId(Long socialAppId) {
		this.socialAppId = socialAppId;
	}

	/**
	 * Getter for rowsPerTablePage
	 * 
	 * @return the rowsPerTablePage
	 */
	public Integer getRowsPerTablePage() {
		return rowsPerTablePage;
	}

	/**
	 * Setter for rowsPerTablePage
	 * 
	 * @param rowsPerTablePage the rowsPerTablePage to set
	 */
	public void setRowsPerTablePage(Integer rowsPerTablePage) {
		this.rowsPerTablePage = rowsPerTablePage;
	}

	/**
	 * Getter for defaultTimeZone
	 * 
	 * @return the defaultTimeZone
	 */
	public Long getDefaultTimeZoneId() {
		return defaultTimeZoneId;
	}

	/**
	 * Setter for defaultTimeZone
	 * 
	 * @param defaultTimeZone the defaultTimeZone to set
	 */
	public void setDefaultTimeZoneId(Long defaultTimeZone) {
		this.defaultTimeZoneId = defaultTimeZone;
	}

	/**
	 * Getter for registeredPartnerFeature
	 * 
	 * @return the registeredPartnerFeature
	 */
	public Long getRegisteredPartnerFeatureId() {
		return registeredPartnerFeatureId;
	}

	/**
	 * Setter for registeredPartnerFeature
	 * 
	 * @param registeredPartnerFeature the registeredPartnerFeature to set
	 */
	public void setRegisteredPartnerFeatureId(Long registeredPartnerFeature) {
		this.registeredPartnerFeatureId = registeredPartnerFeature;
	}

	/**
	 * Getter for sizePassword
	 * 
	 * @return the sizePassword
	 */
	public Integer getSizePassword() {
		return sizePassword;
	}

	/**
	 * Setter for sizePassword
	 * 
	 * @param sizePassword the sizePassword to set
	 */
	public void setSizePassword(Integer sizePassword) {
		this.sizePassword = sizePassword;
	}

	/**
	 * Getter for defaultLanguageId
	 * 
	 * @return the defaultLanguageId
	 */
	public Long getDefaultLanguageId() {
		return defaultLanguageId;
	}

	/**
	 * Setter for defaultLanguageId
	 * 
	 * @param defaultLanguageId the defaultLanguageId to set
	 */
	public void setDefaultLanguageId(Long defaultLanguageId) {
		this.defaultLanguageId = defaultLanguageId;
	}

	/**
	 * Getter for defaultStandardProfileId
	 * 
	 * @return
	 */
	public Long getDefaultStandardProfileId() {
		return defaultStandardProfileId;
	}

	/**
	 * Setter for defaultStandardProfileId
	 * 
	 * @param defaultStandardProfileId
	 */
	public void setDefaultStandardProfileId(Long defaultStandardProfileId) {
		this.defaultStandardProfileId = defaultStandardProfileId;
	}

}

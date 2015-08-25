package com.hallocasa.commons.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * @author German Quinones
 *
 * @since 1.7
 */
public class AccountAppVO implements ValueObject {

	private static final long serialVersionUID = 1L;
	public final static String account_ = "account";
	public final static String partner_ = "partnerId";
	public final static String app_ = "appId";
	public final static String profiles_ = "profiles";
	public final static String operations_ = "operations";
	public final static String infoGroups_ = "informationGroups";
	public final static String supervisedOperations_ = "supervisedOperations";

	@NotNull
	private UserVO account;
	
	@NotNull
	private Long partnerId;
	
	@NotNull
	private Long appId;
	
	private List<ProfileVO> profiles;
	private List<OperationVO> operations;
	private List<InformationGroupVO> informationGroups;
	private List<OperationVO> supervisedOperations;

	/**
	 * @return
	 */
	public UserVO getAccount() {
		return account;
	}

	/**
	 * @param account
	 */
	public void setAccount(UserVO account) {
		this.account = account;
	}

	/**
	 * @return
	 */
	public List<OperationVO> getSupervisedOperations() {
		return supervisedOperations;
	}

	/**
	 * @param supervisedOperations
	 */
	public void setSupervisedOperations(List<OperationVO> supervisedOperations) {
		this.supervisedOperations = supervisedOperations;
	}

	/**
	 * @return
	 */
	public Long getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId
	 */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return
	 */
	public Long getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	/**
	 * @return
	 */
	public List<ProfileVO> getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles
	 */
	public void setProfiles(List<ProfileVO> profiles) {
		this.profiles = profiles;
	}

	/**
	 * @return
	 */
	public List<OperationVO> getOperations() {
		return operations;
	}

	/**
	 * @param operations
	 */
	public void setOperations(List<OperationVO> operations) {
		this.operations = operations;
	}

	/**
	 * @return
	 */
	public List<InformationGroupVO> getInformationGroups() {
		return informationGroups;
	}

	/**
	 * @param informationGroups
	 */
	public void setInformationGroups(List<InformationGroupVO> informationGroups) {
		this.informationGroups = informationGroups;
	}
}

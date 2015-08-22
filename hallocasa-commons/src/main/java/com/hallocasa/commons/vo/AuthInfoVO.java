package com.hallocasa.commons.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for Authentication information
 * 
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthInfo", propOrder = {
		"account",
		"apps",
		"partner"
})
public class AuthInfoVO implements ValueObject{

	private static final long serialVersionUID = -1370266485971582595L;
	
	/* static fields */
	private AccountVO account;
	private List<AppVO> apps;
	private PartnerVO partner;

	/* instance variables */

	/* constructors */
	/**
	 * Default Constructor
	 */
	public AuthInfoVO() {
		this.apps = new ArrayList<AppVO>();
	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for account
	 * 
	 * @return the account
	 */
	public AccountVO getAccount() {
		return account;
	}

	/**
	 * Setter for account
	 * 
	 * @param account the account to set
	 */
	public void setAccount(AccountVO account) {
		this.account = account;
	}

	/**
	 * Constructor
	 * 
	 * @param apps
	 */
	public AuthInfoVO(List<AppVO> apps) {
		super();
		this.apps = apps;
	}

	/**
	 * Getter for apps
	 * 
	 * @return the apps
	 */
	public List<AppVO> getApps() {
		return apps;
	}

	/**
	 * Setter for apps
	 * 
	 * @param apps the apps to set
	 */
	public void setApps(List<AppVO> apps) {
		this.apps = apps;
	}

	/**
	 * Getter for partner
	 * @return the partner
	 */
	public PartnerVO getPartner() {
		return partner;
	}

	/**
	 * Setter for partner
	 * @param partner the partner to set
	 */
	public void setPartner(PartnerVO partner) {
		this.partner = partner;
	}

}

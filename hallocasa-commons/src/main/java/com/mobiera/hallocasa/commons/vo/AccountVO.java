package com.mobiera.hallocasa.commons.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.mobiera.hallocasa.commons.i18n.ValidationMessages;
import com.mobiera.hallocasa.commons.validation.NotEmpty;
import com.mobiera.hallocasa.commons.validation.ValidationPatterns;
import com.mobiera.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for account
 * 
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Account", propOrder = { "id", "activeFlag", "email",
	"firstName", "lastName", "mobile", "phone", "city", "state", "skype",
	"address1", "address2", "postalCode", "country", "language", "partner",
	"lastLogin"

})
public class AccountVO implements ValueObject {

	/* static fields */
	private static final long serialVersionUID = -8590243051229357778L;
	public static final String whiteIps_ = "whiteIps";
	public static final String partner_ = "partner";

	/* static fields */

	/* instance variables */
	private Long id;
	private Boolean activeFlag;

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 80)
	@Pattern(regexp = ValidationPatterns.EMAIL_PATTERN, message = "{"
		+ ValidationMessages.EMAIL_PATTERN + "}")
	private String email;

	@NotEmpty
	@Size(min = 0, max = 45)
	@NotNull
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String firstName;

	@NotEmpty
	@Size(min = 0, max = 45)
	@NotNull
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String lastName;

	@Pattern(regexp = ValidationPatterns.PHONE_PATTERN, message = "{"
		+ ValidationMessages.PHONE_PATTERN + "}")
	private String mobile;

	@Pattern(regexp = ValidationPatterns.PHONE_PATTERN, message = "{"
		+ ValidationMessages.PHONE_PATTERN + "}")
	private String phone;

	@Size(min = 0, max = 45)
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String city;

	@Size(min = 0, max = 45)
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String state;

	@Size(min = 0, max = 45)
	private String skype;

	@Size(min = 0, max = 45)
	private String address1;

	@Size(min = 0, max = 45)
	private String address2;

	@Size(min = 0, max = 20)
	private String postalCode;

	@NotNull
	private CountryVO country;

	@NotNull
	private LanguageVO language;

	private PartnerVO partner;

	private Date lastLogin;

	@XmlTransient
	private List<String> whiteIps;
	
	@XmlTransient
	private Boolean hasStandardProfile;

	/* constructors */
	/**
	 * Constructor
	 */
	public AccountVO() {
		super();
		this.whiteIps = new ArrayList<String>();
	}

	public AccountVO(AccountVO account) {
		super();
		this.id = account.getId();
		this.email = account.getEmail();
		this.firstName = account.getFirstName();
		this.lastName = account.getLastName();
		this.mobile = account.getMobile();
		this.phone = account.getPhone();
		this.address1 = account.getAddress1();
		this.address2 = account.getAddress2();
		this.postalCode = account.getPostalCode();
		this.city = account.getCity();
		this.state = account.getState();
		this.skype = account.getSkype();
		this.country = account.getCountry();
		this.language = account.getLanguage();
		this.partner = account.getPartner();
		this.lastLogin = account.getLastLogin();
		this.whiteIps = new ArrayList<String>();
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param email
	 * @param firstName
	 * @param lastName
	 */
	public AccountVO(Long id, String email, String firstName, String lastName) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.whiteIps = new ArrayList<String>();
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
	 * Getter for activeFlag
	 * 
	 * @return the activeFlag
	 */
	public Boolean getActiveFlag() {
		return activeFlag;
	}

	/**
	 * Setter for activeFlag
	 * 
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * Getter for email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for email
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for firstName
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for firstName
	 * 
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for lastName
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for lastName
	 * 
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for mobile
	 * 
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Setter for mobile
	 * 
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Getter for phone
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Setter for phone
	 * 
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Getter for city
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setter for city
	 * 
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Getter for state
	 * 
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Setter for state
	 * 
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Getter for skype
	 * 
	 * @return the skype
	 */
	public String getSkype() {
		return skype;
	}

	/**
	 * Setter for skype
	 * 
	 * @param skype the skype to set
	 */
	public void setSkype(String skype) {
		this.skype = skype;
	}

	/**
	 * Getter for country
	 * 
	 * @return country
	 */
	public CountryVO getCountry() {
		return country;
	}

	/**
	 * Setter for country
	 * 
	 * @param country
	 */
	public void setCountry(CountryVO country) {
		this.country = country;
	}

	/**
	 * Getter for language
	 * 
	 * @return language
	 */
	public LanguageVO getLanguage() {
		return language;
	}

	/**
	 * Setter for language
	 * 
	 * @param language
	 */
	public void setLanguage(LanguageVO language) {
		this.language = language;
	}

	/**
	 * Getter for address1
	 * 
	 * @return address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * Setter for address1
	 * 
	 * @param address1
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * Getter for address2
	 * 
	 * @return address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * Setter for address2
	 * 
	 * @param address2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * Getter for postalCode
	 * 
	 * @return postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Setter for postalCode
	 * 
	 * @param postalCode
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Getter for partner
	 * 
	 * @return partner
	 */
	public PartnerVO getPartner() {
		return partner;
	}

	/**
	 * Setter for partner
	 * 
	 * @param partner
	 */
	public void setPartner(PartnerVO partner) {
		this.partner = partner;
	}

	/**
	 * @return lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * Getter for whiteIps
	 * 
	 * @return the whiteIps
	 */
	public List<String> getWhiteIps() {
		return whiteIps;
	}

	/**
	 * Setter for whiteIps
	 * 
	 * @param whiteIps the whiteIps to set
	 */
	public void setWhiteIps(List<String> whiteIps) {
		this.whiteIps = whiteIps;
	}

	/**
	 * @param lastLogin
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	/**
	 * @return
	 */
	public Boolean getHasStandardProfile() {
		return hasStandardProfile;
	}

	/**
	 * @param hasStandardProfile
	 */
	public void setHasStandardProfile(Boolean hasStandardProfile) {
		this.hasStandardProfile = hasStandardProfile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountVO other = (AccountVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

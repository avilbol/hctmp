package com.hallocasa.commons.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.validation.ValidationPatterns;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value object of the registering partner process
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class RegisteringPartnerVO implements ValueObject {

	/* static */
	public static final String companyName_ = "companyName";
	public static final String firstName_ = "firstName";
	public static final String lastName_ = "lastName";
	public static final String email_ = "email";
	public static final String password_ = "password";
	public static final String mobilePhone_ = "mobilePhone";
	public static final String country_ = "country";

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 80)
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
			+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String companyName;

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

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 80)
	@Pattern(regexp = ValidationPatterns.EMAIL_PATTERN, message = "{"
			+ ValidationMessages.EMAIL_PATTERN + "}")
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 80)
	@Pattern(regexp = ValidationPatterns.PASSWORD_PATTERN, message = "{"
			+ ValidationMessages.PASSWORD_PATTERN + "}")
	private String password;

	@Pattern(regexp = ValidationPatterns.PHONE_PATTERN, message = "{"
			+ ValidationMessages.PHONE_PATTERN + "}")
	private String mobilePhone;

	@NotNull
	private CountryVO country;

	/* static fields */

	/* instance variables */

	/* constructors */

	/**
	 * Constructor
	 */
	public RegisteringPartnerVO() {
	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for companyName
	 * 
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Setter for companyName
	 * 
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * Getter for password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for password
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for mobilePhone
	 * 
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * Setter for mobilePhone
	 * 
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * Getter for countryVO
	 * 
	 * @return the countryVO
	 */
	public CountryVO getCountry() {
		return country;
	}

	/**
	 * Setter for countryVO
	 * 
	 * @param country the countryVO to set
	 */
	public void setCountry(CountryVO country) {
		this.country = country;
	}

}

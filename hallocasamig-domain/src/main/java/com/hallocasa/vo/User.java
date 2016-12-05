package com.hallocasa.vo;

import java.util.Date;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;

/**
 * Value object that represents a system user
 */
public class User implements ValueObject {

	private Long id;
	private String email;
	private String password;
	private Boolean confirmedFlag;
	private Language language;
	private Date registerDate;
	private String firstName;
	private String lastName;
	private String webSite;
	private String linkedIn;
	private String skype;
	private String base64Image;
	private String imageLink;
	private Language mainSpokenLanguage;
	private CountryTelephonePrefix telephonePrefix;
	private String telephoneNumber;
	private List<UserType> userTypes;
	private List<UserLanguage> userLanguages;
	private List<UserDescription> userDescriptions;
	private Country country;
	private State state;
	private City city;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getConfirmedFlag() {
		return confirmedFlag;
	}
	public void setConfirmedFlag(Boolean confirmedFlag) {
		this.confirmedFlag = confirmedFlag;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public Language getMainSpokenLanguage() {
		return mainSpokenLanguage;
	}
	public void setMainSpokenLanguage(Language mainSpokenLanguage) {
		this.mainSpokenLanguage = mainSpokenLanguage;
	}
	public CountryTelephonePrefix getTelephonePrefix() {
		return telephonePrefix;
	}
	public void setTelephonePrefix(CountryTelephonePrefix telephonePrefix) {
		this.telephonePrefix = telephonePrefix;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public List<UserType> getUserTypes() {
		return userTypes;
	}
	public void setUserTypes(List<UserType> userTypes) {
		this.userTypes = userTypes;
	}
	public List<UserLanguage> getUserLanguages() {
		return userLanguages;
	}
	public void setUserLanguages(List<UserLanguage> userLanguages) {
		this.userLanguages = userLanguages;
	}
	public List<UserDescription> getUserDescriptions() {
		return userDescriptions;
	}
	public void setUserDescriptions(List<UserDescription> userDescriptions) {
		this.userDescriptions = userDescriptions;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
}

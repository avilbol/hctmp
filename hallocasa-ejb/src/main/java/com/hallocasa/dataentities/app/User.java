/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.dataentities.converters.ImageContainerConverter;
import com.hallocasa.dataentities.converters.LanguageConverter;
import com.hallocasa.dataentities.converters.MultiLanguageTextConverter;
import com.hallocasa.dataentities.converters.SpokenLanguagesConverter;
import com.hallocasa.dataentities.types.LanguageList;

/**
 * 
 * @author david
 */
@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = User.QUERY_FIND_BY_EMAIL, query = "select u from User u where u.email = ?1"), })
@SuppressWarnings({ "UniqueEntityName", "ValidPrimaryTableName",
		"ValidAttributes" })
public class User implements Serializable, HallocasaEntity {

	/* static fields */
	private static final long serialVersionUID = 1L;
	public static final String QUERY_FIND_BY_EMAIL = "User.findByEmail";
	public static final String QUERY_ALL_LIST_WITH_USER_TYPES = "select u from User u WHERE size(u.userTypes) > 0";
	public static final String QUERY_COUNT_LIST_WITH_USER_TYPES = "select count(u) from User u  WHERE size(u.userTypes) > 0";
	public static final String QUERY_FIND_RANDOM_EXCLUDE_LIST = "select u from User u WHERE u.id NOT IN :exclList ORDER BY RAND()";
	public static final String spokenLanguages_ = "spokenLanguages";
	public static final String password_ = "password";
	public static final String userTypes_ = "userTypes";
	public static final String telephone_ = "telephone";
	
	/* instance variables */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "confirmed_flag")
	private Boolean confirmedFlag;

	@Column(name = "language")
	@Convert(converter = LanguageConverter.class)
	private Language language;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "web_site")
	private String webSite;

	@Column(name = "linked_in")
	private String linkedIn;

	@Column(name = "skype")
	private String skype;

	@Column(name = "spoken_languages")
	@Convert(converter = SpokenLanguagesConverter.class)
	private LanguageList spokenLanguages = new LanguageList();

	@Column(name = "main_spoken_language", columnDefinition = "Varchar(10) default 'en'")
	@Convert(converter = LanguageConverter.class)
	private Language mainSpokenLanguage;

	@Column(name = "user_description")
	@Convert(converter = MultiLanguageTextConverter.class)
	private MultiLanguageText userDescription;

	@JoinTable(name = "user_user_type", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_type_id", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.LAZY)
	private List<UserType> userTypes = new ArrayList<>();

	/*
	 * @JoinTable(name = "user_profile", joinColumns = {
	 * 
	 * @JoinColumn(name = "user_id", referencedColumnName = "id")},
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "profile_id", referencedColumnName = "id")})
	 * 
	 * @ManyToMany(fetch = FetchType.LAZY) private List<Profile> profiles;
	 */

	@JoinColumn(name = "country_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Country country;

	@JoinColumn(name = "state_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private State state;

	@JoinColumn(name = "city_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private City city;
	
	@JoinColumn(name = "user_telephone_id", referencedColumnName = "user_id")
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	private Telephone telephone;

	@Column(name = "image_name")
	@Convert(converter = ImageContainerConverter.class)
	private ImageContainer image;

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/**
	 * Default constructor
	 */
	public User() {
		this.spokenLanguages = new LanguageList();
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		return !((this.id == null && other.id != null) || (this.id != null && !this.id
				.equals(other.id)));
	}

	@Override
	public String toString() {
		return "com.hallocasa.dataentities.app.User[ id=" + id + " ]";
	}

	/**
	 * Getter for id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for Id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userType
	 */
	public List<UserType> getUserTypes() {
		return userTypes;
	}

	/**
	 * @param userTypes
	 *            the userType to set
	 */
	public void setUserType(List<UserType> userTypes) {
		this.setUserTypes(userTypes);
	}

	/**
	 * @return the confirmedFlag
	 */
	public Boolean getConfirmedFlag() {
		return confirmedFlag;
	}

	/**
	 * @param confirmedFlag
	 *            the confirmedFlag to set
	 */
	public void setConfirmedFlag(Boolean confirmedFlag) {
		this.confirmedFlag = confirmedFlag;
	}

	/**
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getMainSpokenLanguage() {
		return mainSpokenLanguage;
	}

	public void setMainSpokenLanguage(Language mainSpokenLanguage) {
		this.mainSpokenLanguage = mainSpokenLanguage;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @param webSite
	 *            the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * @return the linkedIn
	 */
	public String getLinkedIn() {
		return linkedIn;
	}

	/**
	 * @param linkedIn
	 *            the linkedIn to set
	 */
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	/**
	 * @return the skype
	 */
	public String getSkype() {
		return skype;
	}

	/**
	 * @param skype
	 *            the skype to set
	 */
	public void setSkype(String skype) {
		this.skype = skype;
	}

	/**
	 * @return the userDescription
	 */
	public MultiLanguageText getUserDescription() {
		return userDescription;
	}

	/**
	 * @param userDescription
	 *            the userDescription to set
	 */
	public void setUserDescription(MultiLanguageText userDescription) {
		this.userDescription = userDescription;
	}

	/**
	 * @return the spokenLanguages
	 */
	public List<Language> getSpokenLanguages() {
		return spokenLanguages;
	}

	/**
	 * @param spokenLanguages
	 *            the spokenLanguages to set
	 */
	public void setSpokenLanguages(LanguageList spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	/**
	 * @param userTypes
	 *            the userTypes to set
	 */
	public void setUserTypes(List<UserType> userTypes) {
		this.userTypes = userTypes;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	public ImageContainer getImage() {
		return image;
	}

	public void setImage(ImageContainer image) {
		this.image = image;
	}

	public Telephone getTelephone() {
		return telephone;
	}

	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}
}

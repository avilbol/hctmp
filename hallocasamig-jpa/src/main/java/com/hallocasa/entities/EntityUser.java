/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * 
 * @author david
 */
@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = EntityUser.QUERY_FIND_BY_EMAIL, query = "select u from EntityUser u where u.email = ?1"), })
public class EntityUser implements Serializable, com.hallocasa.entities.i.HallocasaEntity {

	/* static fields */
	private static final long serialVersionUID = 1L;
	public static final String QUERY_FIND_BY_EMAIL = "EntityUser.findByEmail";
	public static final String QUERY_ALL_LIST_WITH_USER_TYPES = "select u from EntityUser u WHERE size(u.userTypes) > 0";
	public static final String QUERY_COUNT_LIST_WITH_USER_TYPES = "select count(u) from EntityUser u  WHERE size(u.userTypes) > 0";
	public static final String QUERY_FIND_RANDOM_EXCLUDE_LIST = "select u from EntityUser u WHERE u.id NOT IN :exclList ORDER BY RAND()";
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

	//@Column(name = "language")
	//@Convert(converter = LanguageConverter.class)
	//private Language language;

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

	/*@Column(name = "spoken_languages")
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
	private List<UserType> userTypes = new ArrayList<>();*/

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

/*	@JoinColumn(name = "country_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Country country;

	@JoinColumn(name = "state_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private State state;

	@JoinColumn(name = "city_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private City city;
	
	@JoinColumn(name = "user_telephone_id", referencedColumnName = "id")
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	private Telephone telephone;

	@Column(name = "image_name")
	@Convert(converter = ImageContainerConverter.class)
	private ImageContainer image;*/

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/**
	 * Default constructor
	 */
	public EntityUser() {
		//this.spokenLanguages = new LanguageList();
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EntityUser)) {
			return false;
		}
		EntityUser other = (EntityUser) object;
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
}

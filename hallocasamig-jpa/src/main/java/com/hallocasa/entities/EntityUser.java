package com.hallocasa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.entities.properties.EntityLanguage;
import com.hallocasa.persistence.converters.HcBooleanConverter;

/**
 * Entity that represents an hallocasa user
 */
@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = EntityUser.QUERY_FIND_BASIC_BY_EMAIL, 
	query = "select new com.hallocasa.entities.EntityUser(u.id, u.email, "
			+ "u.password, u.confirmedFlag, u.language, u.registerDate) "
			+ "from EntityUser u where u.email = ?1"),
	@NamedQuery(name = EntityUser.QUERY_FIND_BY_ID, 
		query = "select u from EntityUser u where u.id = ?1"),
	@NamedQuery(name = EntityUser.QUERY_FIND_BY_ID_LIST, 
		query = "select u from EntityUser u WHERE u.id IN ?1")})
public class EntityUser implements Serializable, HallocasaEntity {

	/* static fields */
	private static final long serialVersionUID = 1L;
	public static final String QUERY_FIND_BASIC_BY_EMAIL = "EntityUser.findBasicByEmail";
	public static final String QUERY_FIND_BY_ID = "EntityUser.findById";
	public static final String QUERY_ID_LIST_WITH_USER_TYPES = "select u.id from EntityUser u "
			+ "WHERE size(u.userTypes) > 0";
	public static final String QUERY_FIND_BY_ID_LIST = "EntityUser.FindByIdList";
	public static final String QUERY_COUNT_LIST_WITH_USER_TYPES = "select count(u) from EntityUser u  "
			+ "WHERE size(u.userTypes) > 0";
	
	/* instance variables */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "confirmed_flag")
	private Boolean confirmedFlag;

	@JoinColumn(name = "language", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private EntityLanguage language;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EntityUserLanguage> userLanguages;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EntityUserDescription> userDescriptions;
	
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date")
	private Date registerDate;
	
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private EntityCountry country;

	@JoinColumn(name = "state_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private EntityState state;

	@JoinColumn(name = "city_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private EntityCity city;

	@JoinTable(name = "user_user_type", joinColumns = { 
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, 
			inverseJoinColumns = { 
				@JoinColumn(name = "user_type_id", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.LAZY)
	private List<EntityUserType> userTypes;
	
	@Column(name = "image_link")
	private String imageLink;

	@JoinColumn(name = "telephone_prefix_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private EntityCountryTelephonePrefix telephonePrefix;
	
	@Column(name = "telephone_number")
	private String telephoneNumber;
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	public EntityUser(Long id, String email, String password, 
			Boolean confirmedFlag, EntityLanguage language, Date registerDate) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.confirmedFlag = confirmedFlag;
		this.language = language;
		this.registerDate = registerDate;
	}

	/**
	 * Default constructor
	 */
	public EntityUser() {
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

	public List<EntityUserLanguage> getUserLanguages() {
		if(userLanguages == null){
			userLanguages = new LinkedList<EntityUserLanguage>();
		}
		return userLanguages;
	}

	public void setUserLanguages(List<EntityUserLanguage> userLanguages) {
		this.userLanguages = userLanguages;
	}

	public EntityLanguage getLanguage() {
		return language;
	}

	public void setLanguage(EntityLanguage language) {
		this.language = language;
	}

	public EntityCountry getCountry() {
		return country;
	}

	public void setCountry(EntityCountry country) {
		this.country = country;
	}

	public EntityState getState() {
		return state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}

	public EntityCity getCity() {
		return city;
	}

	public void setCity(EntityCity city) {
		this.city = city;
	}

	public List<EntityUserType> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(List<EntityUserType> userTypes) {
		this.userTypes = userTypes;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public EntityCountryTelephonePrefix getTelephonePrefix() {
		return telephonePrefix;
	}

	public void setTelephonePrefix(EntityCountryTelephonePrefix telephonePrefix) {
		this.telephonePrefix = telephonePrefix;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public List<EntityUserDescription> getUserDescriptions() {
		if(userDescriptions == null){
			userDescriptions = new LinkedList<EntityUserDescription>();
		}
		return userDescriptions;
	}

	public void setUserDescriptions(List<EntityUserDescription> userDescriptions) {
		this.userDescriptions = userDescriptions;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
}

package com.hallocasa.commons.vo;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.beanutils.BeanUtils;

import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.validation.ValidationPatterns;
import com.hallocasa.commons.vo.enums.OperationContactType;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Operation value object
 * 
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Operation", propOrder = { "id", "activeFlag", "addressLine1",
	"addressLine2", "city", "companyName", "postalCode", "state", "timeZone",
	"country", "logoFile", "partnerId", })
public class OperationVO implements ValueObject {

	private static final long serialVersionUID = 1444097563641450620L;

	/* static fields */
	public static final String contactAccountIdsMap_ = "contactAccountIdsMap";
	public static final String timeZone_ = "timeZone";
	public static final String partnerId_ = "partnerId";
	public static final String country_ = "country";
	public static final String companyName_ = "companyName";

	/* instance variables */
	private Long id;
	private Boolean activeFlag;

	@NotEmpty
	@NotNull
	@Size(min = 0, max = 80)
	@Pattern(regexp = ValidationPatterns.STREET_ADDRESS, message = "{"
		+ ValidationMessages.ADDRESS_PATTERN + "}")
	private String addressLine1;

	@NotEmpty
	@Size(min = 0, max = 80)
	@Pattern(regexp = ValidationPatterns.STREET_ADDRESS, message = "{"
		+ ValidationMessages.ADDRESS_PATTERN + "}")
	private String addressLine2;

	@NotEmpty
	@NotNull
	@Size(min = 0, max = 45)
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String city;

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 80)
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String companyName;

	@Size(min = 0, max = 25)
	private String postalCode;

	@Size(min = 0, max = 45)
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String state;

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 45)
	private String timeZone;

	@NotNull
	private CountryVO country;

	private FileVO logoFile;

	@NotNull
	private Long partnerId;

	@XmlTransient
	private String namePartner;

	@XmlTransient
	private EnumMap<OperationContactType, Long> contactAccountIdsMap;

	@XmlTransient
	private Boolean selected;

	@XmlTransient
	private Boolean readOnly;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public OperationVO() {
		this.setContactAccountIdsMap(new EnumMap<OperationContactType, Long>(
			OperationContactType.class));
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public OperationVO(Long id) {
		super();
		this.setContactAccountIdsMap(new EnumMap<OperationContactType, Long>(
			OperationContactType.class));
		this.id = id;
	}

	/**
	 * Constructs a new instance copying all values from a given value object
	 * 
	 * @param operationVO
	 */
	public OperationVO(OperationVO operationVO) {
		try {
			BeanUtils.copyProperties(this, operationVO);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e); // it shouldn't ever happen
		}
		if (operationVO.logoFile != null) {
			this.logoFile = new FileVO(operationVO.logoFile);
		}

		// put an empty map
		this.setContactAccountIdsMap(new EnumMap<OperationContactType, Long>(
			OperationContactType.class));

		if (operationVO.getContactAccountIdsMap() != null) {

			this.getContactAccountIdsMap().putAll(
				operationVO.getContactAccountIdsMap());
		}
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
	 * Getter for addressLine1
	 * 
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * Setter for addressLine1
	 * 
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * Getter for addressLine2
	 * 
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * Setter for addressLine2
	 * 
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
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
	 * Getter for postalCode
	 * 
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Setter for postalCode
	 * 
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	 * Getter for timeZone
	 * 
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * Setter for timeZone
	 * 
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * Getter for country
	 * 
	 * @return the country
	 */
	public CountryVO getCountry() {
		return country;
	}

	/**
	 * Setter for country
	 * 
	 * @param country the country to set
	 */
	public void setCountry(CountryVO country) {
		this.country = country;
	}

	/**
	 * Getter for logoFile
	 * 
	 * @return the logoFile
	 */
	public FileVO getLogoFile() {
		return logoFile;
	}

	/**
	 * Setter for logoFile
	 * 
	 * @param logoFile the logoFile to set
	 */
	public void setLogoFile(FileVO logoFile) {
		this.logoFile = logoFile;
	}

	/**
	 * Getter for partnerId
	 * 
	 * @return the partnerId
	 */
	public Long getPartnerId() {
		return partnerId;
	}

	/**
	 * Setter for partnerId
	 * 
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * Getter for contactAccountIdsMap
	 * 
	 * @return the contactAccountIdsMap
	 */
	public EnumMap<OperationContactType, Long> getContactAccountIdsMap() {
		return contactAccountIdsMap;
	}

	/**
	 * Setter for contactAccountIdsMap
	 * 
	 * @param contactAccountIdsMap the contactAccountIdsMap to set
	 */
	public void setContactAccountIdsMap(
		EnumMap<OperationContactType, Long> contactAccountIdsMap) {
		this.contactAccountIdsMap = contactAccountIdsMap;
	}

	/**
	 * @return namePartner
	 */
	public String getNamePartner() {
		return namePartner;
	}

	/**
	 * @param namePartner
	 */
	public void setNamePartner(String namePartner) {
		this.namePartner = namePartner;
	}

	/**
	 * @return selected
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return readOnly
	 */
	public Boolean getReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly
	 */
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
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
		OperationVO other = (OperationVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

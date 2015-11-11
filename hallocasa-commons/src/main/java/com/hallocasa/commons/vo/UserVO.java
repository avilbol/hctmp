package com.hallocasa.commons.vo;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.validation.ValidationPatterns;
import com.hallocasa.commons.vo.interfaces.ValueObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Value Object for user
 *
 * @author David Mantilla
 * @since 1.7
 */
public class UserVO implements ValueObject {

    /* static fields */
    private static final long serialVersionUID = -8590243051229357778L;
    public static final String spokenLanguages_ = "spokenLanguages";

    /* static fields */

    /* instance variables */
    private Long id;

    @NotNull
    private Boolean confirmedFlag;

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

    @Size(min = 0, max = 45)
    @Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
            + ValidationMessages.GENERAL_NAME_PATTERN + "}")
    private String city;

    @Size(min = 0, max = 45)
    private String skype;

    @Pattern(regexp = ValidationPatterns.URL_PATTERN, message = "{"
            + ValidationMessages.URL_PATTERN + "}")
    @Size(min = 0, max = 80)
    private String linkedIn;

    @Pattern(regexp = ValidationPatterns.URL_PATTERN, message = "{"
            + ValidationMessages.URL_PATTERN + "}")
    @Size(min = 0, max = 80)
    private String webSite;

    private CountryVO country;

    private StateVO state;

    @NotNull
    private Language language;

    private List<Language> spokenLanguages;

    private MultiLanguageText userDescription;

    private List<UserTypeVO> userTypes;

    /* constructors */
    /**
     * Constructor
     */
    public UserVO() {
        super();
    }

    public UserVO(UserVO user) {
        super();
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.webSite = user.webSite;
        this.city = user.city;
        this.state = user.state;
        this.skype = user.skype;
        this.country = user.country;
        this.language = user.language;
        this.linkedIn = user.linkedIn;
        this.spokenLanguages = new ArrayList<>();
        Collections.copy(this.spokenLanguages, user.spokenLanguages);
        this.userTypes = new ArrayList<>();
        Collections.copy(this.userTypes, user.userTypes);
        this.userDescription = new MultiLanguageText(user.getUserDescription());
    }

    /**
     * Constructor
     *
     * @param id
     * @param email
     * @param firstName
     * @param lastName
     */
    public UserVO(Long id, String email, String firstName, String lastName) {
        super();
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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
    public StateVO getState() {
        return state;
    }

    /**
     * Setter for state
     *
     * @param state the state to set
     */
    public void setState(StateVO state) {
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
    public Language getLanguage() {
        return language;
    }

    /**
     * Setter for language
     *
     * @param language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Getter for webSite
     *
     * @return webSite
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * Setter for webSite
     *
     * @param webSite
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserVO other = (UserVO) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the confirmedFlag
     */
    public Boolean getConfirmedFlag() {
        return confirmedFlag;
    }

    /**
     * @param confirmedFlag the confirmedFlag to set
     */
    public void setConfirmedFlag(Boolean confirmedFlag) {
        this.confirmedFlag = confirmedFlag;
    }

    /**
     * @return the linkedIn
     */
    public String getLinkedIn() {
        return linkedIn;
    }

    /**
     * @param linkedIn the linkedIn to set
     */
    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    /**
     * @return the spokenLanguages
     */
    public List<Language> getSpokenLanguages() {
        return spokenLanguages;
    }

    /**
     * @param spokenLanguages the spokenLanguages to set
     */
    public void setSpokenLanguages(List<Language> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    /**
     * @return the userDescription
     */
    public MultiLanguageText getUserDescription() {
        return userDescription;
    }

    /**
     * @param userDescription the userDescription to set
     */
    public void setUserDescription(MultiLanguageText userDescription) {
        this.userDescription = userDescription;
    }

    /**
     * Return concatenation of firstName and lastName
     *
     * @return
     */
    public String getFullName() {
        StringBuilder str = new StringBuilder();
        boolean hasFirstName = false;

        if (firstName != null && !firstName.isEmpty()) {
            str.append(firstName);
            hasFirstName = true;
        }
        if (hasFirstName && lastName != null && lastName.isEmpty()) {
            str.append(" ");
        }
        if (lastName != null) {
            str.append(lastName);
        }
        return str.toString();
    }

    /**
     * @return the userTypes
     */
    public List<UserTypeVO> getUserTypes() {
        return userTypes;
    }
    
    /**
     * @return the userTypes
     */
    public List<UserTypeVO> getExample() {
        System.out.println("blablabla" + userTypes.size());
        return userTypes;
    }

    /**
     * @param userTypes the userTypes to set
     */
    public void setUserTypes(List<UserTypeVO> userTypes) {
        this.userTypes = userTypes;
    }

}

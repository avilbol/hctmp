/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.app;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.dataentities.converters.LanguageConverter;
import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = User.QUERY_FIND_BY_EMAIL,
            query = "select u from User u where u.email = ?1")
})
@SuppressWarnings({"UniqueEntityName", "ValidPrimaryTableName", "ValidAttributes"})
public class User implements Serializable, HallocasaEntity {

    private static final long serialVersionUID = 1L;
    public static final String QUERY_FIND_BY_EMAIL = "User.findByEmail";

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

    @JoinTable(name = "user_user_type", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "user_type_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserType> userTypes;

    @JoinTable(name = "user_profile", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "profile_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Profile> profiles;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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
     * @param email the email to set
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
     * @param password the password to set
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
     * @param userTypes the userType to set
     */
    public void setUserType(List<UserType> userTypes) {
        this.userTypes = userTypes;
    }

    /**
     * @return the profiles
     */
    public List<Profile> getProfiles() {
        return profiles;
    }

    /**
     * @param profiles the profiles to set
     */
    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
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
     * @return the language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

}

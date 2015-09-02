/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.app;

import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
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

/**
 *
 * @author david
 */
@Entity
@NamedQueries({
    @NamedQuery(name = User.QUERY_FIND_BY_EMAIL,
            query = "select u from User u where u.email = ?1")
})
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

    @JoinColumn(name = "user_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserType userType;

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
    public UserType getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
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

}

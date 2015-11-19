/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.app;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.dataentities.converters.MultiLanguageTextConverter;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name = "user_type")
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_type_name")
    @Convert(converter = MultiLanguageTextConverter.class)
    private MultiLanguageText userTypeName;

    @Column(name = "user_type_tooltip")
    @Convert(converter = MultiLanguageTextConverter.class)
    private MultiLanguageText userTypeTooltip;
    
    @Column(name="manage_tooltip", 
            columnDefinition="boolean default true", 
            nullable=false)
    private Boolean manageTooltip = false;
    
    @Column(name="manage_certificate",
            columnDefinition="boolean default true", 
            nullable=false)
    private Boolean manageCertificate = false;
    
    /**
     * Default constructor
     */
    public UserType() {
    }

    /**
     *
     * @param id
     */
    public UserType(Long id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param userTypeName
     */
    public UserType(Long id, MultiLanguageText userTypeName) {
        this.id = id;
        this.userTypeName = userTypeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserType)) {
            return false;
        }
        UserType other = (UserType) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.hallocasa.dataentities.app.UserType[ id=" + id + " ]";
    }

    /**
     * @return the userTypeName
     */
    public MultiLanguageText getUserTypeName() {
        return userTypeName;
    }

    /**
     * @param userTypeName the userTypeName to set
     */
    public void setUserTypeName(MultiLanguageText userTypeName) {
        this.userTypeName = userTypeName;
    }

    public MultiLanguageText getUserTypeTooltip() {
        return userTypeTooltip;
    }

    public void setUserTypeTooltip(MultiLanguageText userTypeTooltip) {
        this.userTypeTooltip = userTypeTooltip;
    }

    public Boolean getManageTooltip() {
        return manageTooltip;
    }

    public void setManageTooltip(Boolean manageTooltip) {
        this.manageTooltip = manageTooltip;
    }

    public Boolean getManageCertificate() {
        return manageCertificate;
    }

    public void setManageCertificate(Boolean manageCertificate) {
        this.manageCertificate = manageCertificate;
    }
}

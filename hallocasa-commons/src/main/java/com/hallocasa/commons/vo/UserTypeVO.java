/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.vo;

import com.hallocasa.commons.i18n.MultiLanguageText;
import java.io.Serializable;

/**
 *
 * @author david
 */
public class UserTypeVO implements Serializable {

    private Long id;
    private MultiLanguageText userTypeName;
    private MultiLanguageText userTypeTooltip;
    private Boolean manageTooltip;
    private Boolean manageCertificate;

    /**
     * Default constructor
     */
    public UserTypeVO() {
    }

    /**
     *
     * @param id
     */
    public UserTypeVO(Long id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param userTypeName
     */
    public UserTypeVO(Long id, MultiLanguageText userTypeName) {
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
        if (!(object instanceof UserTypeVO)) {
            return false;
        }
        UserTypeVO other = (UserTypeVO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities.wcm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "third")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Third.findAll", query = "SELECT t FROM Third t"),
    @NamedQuery(name = "Third.findByThirdId", query = "SELECT t FROM Third t WHERE t.thirdId = :thirdId"),
    @NamedQuery(name = "Third.findByLegalId", query = "SELECT t FROM Third t WHERE t.legalId = :legalId")})
public class Third implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "third_id")
    private Integer thirdId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "third_full_name")
    private String thirdFullName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "legal_id")
    private String legalId;
    @Basic(optional = true)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "third", fetch = FetchType.LAZY)
    private List<Company> companyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "third", fetch = FetchType.LAZY)
    private List<User> userList;

    public Third() {
    }

    public Third(Integer thirdId) {
        this.thirdId = thirdId;
    }

    public Integer getThirdId() {
        return thirdId;
    }

    public void setThirdId(Integer thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdFullName() {
        return thirdFullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setThirdFullName(String thirdFullName) {
        this.thirdFullName = thirdFullName;
    }

    public String getLegalId() {
        return legalId;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

    @XmlTransient
    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (thirdId != null ? thirdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Third)) {
            return false;
        }
        Third other = (Third) object;
        if ((this.thirdId == null && other.thirdId != null) || (this.thirdId != null && !this.thirdId.equals(other.thirdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.Third[ thirdId=" + thirdId + " ]";
    }

}

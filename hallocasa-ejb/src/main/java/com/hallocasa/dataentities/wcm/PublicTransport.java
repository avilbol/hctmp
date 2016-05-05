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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "public_transport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PublicTransport.findAll", query = "SELECT p FROM PublicTransport p"),
    @NamedQuery(name = "PublicTransport.findByPublicTransportId", query = "SELECT p FROM PublicTransport p WHERE p.publicTransportId = :publicTransportId"),
    @NamedQuery(name = "PublicTransport.findByName", query = "SELECT p FROM PublicTransport p WHERE p.name = :name")})
public class PublicTransport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "public_transport_id")
    private Integer publicTransportId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicTransport", fetch = FetchType.LAZY)
    private List<EnvirontmentPublicTransport> environtmentPublicTransportList;

    public PublicTransport() {
    }

    public PublicTransport(Integer publicTransportId) {
        this.publicTransportId = publicTransportId;
    }

    public PublicTransport(Integer publicTransportId, String name) {
        this.publicTransportId = publicTransportId;
        this.name = name;
    }

    public Integer getPublicTransportId() {
        return publicTransportId;
    }

    public void setPublicTransportId(Integer publicTransportId) {
        this.publicTransportId = publicTransportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    @XmlTransient
    public List<EnvirontmentPublicTransport> getEnvirontmentPublicTransportList() {
        return environtmentPublicTransportList;
    }

    public void setEnvirontmentPublicTransportList(List<EnvirontmentPublicTransport> environtmentPublicTransportList) {
        this.environtmentPublicTransportList = environtmentPublicTransportList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (publicTransportId != null ? publicTransportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof PublicTransport)) {
            return false;
        }
        PublicTransport other = (PublicTransport) object;
        if ((this.publicTransportId == null && other.publicTransportId != null) || (this.publicTransportId != null && !this.publicTransportId.equals(other.publicTransportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PublicTransport[ publicTransportId=" + publicTransportId + " ]";
    }

     /**************************************************************************
     * Constanst
     ***************************************************************************
     */

    /***************************************************************************
     * Instance variable
     ***************************************************************************
     */

    /***************************************************************************
     * Constructor
     ***************************************************************************
     */

    /***************************************************************************
     * Methods
     **************************************************************************
     */

    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */

}

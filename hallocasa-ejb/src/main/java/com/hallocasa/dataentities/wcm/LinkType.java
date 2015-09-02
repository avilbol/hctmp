/*
 * To change this template, choose Tools | Templates
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
 * @author Jhon Fredy Martínez Realpe
 */
@Entity
@Table(name = "link_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinkType.findAll", query = "SELECT l FROM LinkType l"),
    @NamedQuery(name = "LinkType.findByLinkTypeId", query = "SELECT l FROM LinkType l WHERE l.linkTypeId = :linkTypeId"),
    @NamedQuery(name = "LinkType.findByName", query = "SELECT l FROM LinkType l WHERE l.name = :name")})
public class LinkType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "link_type_id", nullable = false)
    private Integer linkTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "linkType", fetch = FetchType.LAZY)
    private List<Link> linkList;

    public LinkType() {
    }

    public LinkType(Integer linkTypeId) {
        this.linkTypeId = linkTypeId;
    }

    public LinkType(Integer linkTypeId, String name) {
        this.linkTypeId = linkTypeId;
        this.name = name;
    }

    public Integer getLinkTypeId() {
        return linkTypeId;
    }

    public void setLinkTypeId(Integer linkTypeId) {
        this.linkTypeId = linkTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (linkTypeId != null ? linkTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof LinkType)) {
            return false;
        }
        LinkType other = (LinkType) object;
        if ((this.linkTypeId == null && other.linkTypeId != null) || (this.linkTypeId != null && !this.linkTypeId.equals(other.linkTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.LinkType[ linkTypeId=" + linkTypeId + " ]";
    }
}

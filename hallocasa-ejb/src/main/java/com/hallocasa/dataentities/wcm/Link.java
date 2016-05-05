/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.wcm;

import java.io.Serializable;

import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
@Entity
@Table(name = "link")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Link.findAll", query = "SELECT l FROM Link l"),
    @NamedQuery(name = "Link.findByLinkId", query = "SELECT l FROM Link l WHERE l.linkId = :linkId"),
    @NamedQuery(name = "Link.findByName", query = "SELECT l FROM Link l WHERE l.name = :name"),
    @NamedQuery(name = "Link.findByUrl", query = "SELECT l FROM Link l WHERE l.url = :url")})
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "link_id", nullable = false)
    private Integer linkId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "url", nullable = false, length = 45)
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name_image", length = 45)
    private String nameImage;
    @JoinColumn(name = "link_type_id", referencedColumnName = "link_type_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LinkType linkType;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation nameTransalation;
    @JoinColumn(name = "description_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation descriptionTransalation;

    public Link() {
    }

    public Link(Integer linkId) {
        this.linkId = linkId;
    }

    public Link(Integer linkId, String name, String url) {
        this.linkId = linkId;
        this.name = name;
        this.url = url;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    /**
     * @return the nameTransalation
     */
    public Translation getNameTransalation() {
        return nameTransalation;
    }

    /**
     * @param nameTransalation the nameTransalation to set
     */
    public void setNameTransalation(Translation nameTransalation) {
        this.nameTransalation = nameTransalation;
    }

    /**
     * @return the descriptionTransalation
     */
    public Translation getDescriptionTransalation() {
        return descriptionTransalation;
    }

    /**
     * @param descriptionTransalation the descriptionTransalation to set
     */
    public void setDescriptionTransalation(Translation descriptionTransalation) {
        this.descriptionTransalation = descriptionTransalation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (linkId != null ? linkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.linkId == null && other.linkId != null) || (this.linkId != null && !this.linkId.equals(other.linkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.Link[ linkId=" + linkId + " ]";
    }
}

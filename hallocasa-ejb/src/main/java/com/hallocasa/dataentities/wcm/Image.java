/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities.wcm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
    @NamedQuery(name = "Image.findByImageId", query = "SELECT i FROM Image i WHERE i.imageId = :imageId")})
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "image_id")
    private Integer imageId;
    
    @JoinTable(name = "property_has_image", joinColumns = {
        @JoinColumn(name = "image_image_id", referencedColumnName = "image_id")}, inverseJoinColumns = {
        @JoinColumn(name = "property_property_id", referencedColumnName = "property_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Property> propertyList;
    
    @JoinColumn(name = "file_file_id", referencedColumnName = "file_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private File file;

    public Image() {
    }

    public Image(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @XmlTransient
    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.Image[ imageId=" + imageId + " ]";
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

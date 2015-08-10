/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.dataentities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.hallocasa.commons.Language;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "translation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Translation.findAll", query = "SELECT t FROM Translation t"),
    @NamedQuery(name = "Translation.findByTranslationId", query = "SELECT t FROM Translation t WHERE t.translationId = :translationId")})
public class Translation implements Serializable, TranslationInterface {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "translation_id")
    private Integer translationId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "text_en")
    private String textEn;
    @Lob
    @Size(max = 65535)
    @Column(name = "text_es")
    private String textEs;
    @Lob
    @Size(max = 65535)
    @Column(name = "text_de")
    private String textDe;
    @JoinColumn(name = "text_source_type_id", referencedColumnName = "text_source_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TextSourceType textSourceType;

    public Translation() {
    }

    public Translation(Integer translationId) {
        this.translationId = translationId;
    }

    public Translation(Integer translationId, String textEn) {
        this.translationId = translationId;
        this.textEn = textEn;
    }

    public Integer getTranslationId() {
        return translationId;
    }

    public void setTranslationId(Integer translationId) {
        this.translationId = translationId;
    }

    /**
     * @return the textSourceType
     */
    public TextSourceType getTextSourceType() {
        return textSourceType;
    }

    /**
     * @param textSourceType the textSourceType to set
     */
    public void setTextSourceType(TextSourceType textSourceType) {
        this.textSourceType = textSourceType;
    }

    @Override
    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn;
    }

    @Override
    public String getTextEs() {
        return textEs;
    }

    public void setTextEs(String textEs) {
        this.textEs = textEs;
    }

    @Override
    public String getTextDe() {
        return textDe;
    }

    public void setTextDe(String textDe) {
        this.textDe = textDe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (translationId != null ? translationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Translation)) {
            return false;
        }
        Translation other = (Translation) object;
        if ((this.translationId == null && other.translationId != null) || (this.translationId != null && !this.translationId.equals(other.translationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.Translation[ translationId=" + translationId + " ]";
    }

    @Override
    public String getText(Language language) {
        switch (language) {
            case de:
                return getTextDe();
            case en:
                return getTextEn();
            case es:
                return getTextEs();
            default:
                throw new UnsupportedOperationException("Language " + language + " not supported yet");
        }
    }
}

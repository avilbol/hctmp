/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.business.dataentities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.hallocasa.business.convertions.LanguageConverter;
import com.hallocasa.commons.Language;
import com.hallocasa.commons.constants.ValidationPatterns;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "temporal_publisher_user")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "TemporalPublisherUser.findAll", query = "SELECT t FROM TemporalPublisherUser t") })
@DiscriminatorValue("3")
public class TemporalPublisherUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "temporal_publisher_user_id")
    private Integer temporalPublisherUserId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "full_name")
    private String fullName;
    @Basic(optional = true)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "email")
    @Pattern(regexp = ValidationPatterns.EMAIL_PATTERN, message = "{com.hallocasa.validator.constraints.Email.message}")
    private String email;
    @Column(name = "language")
    @Convert(converter = LanguageConverter.class)
    private Language language;

    /**
     * @return the temporalPublisherUserId
     */
    public Integer getTemporalPublisherUserId() {
        return temporalPublisherUserId;
    }

    /**
     * @param temporalPublisherUserId
     *            the temporalPublisherUserId to set
     */
    public void setTemporalPublisherUserId(Integer temporalPublisherUserId) {
        this.temporalPublisherUserId = temporalPublisherUserId;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName
     *            the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

}

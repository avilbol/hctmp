/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.wcm;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
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

import com.hallocasa.commons.Language;
import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.validation.ValidationPatterns;
import com.hallocasa.dataentities.converters.LanguageConverter;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "newsletter_receiver")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "NewsletterReceiver.findAll", query = "SELECT c FROM NewsletterReceiver c"), })
public class NewsletterReceiver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "newsletter_receiver_id")
    private Integer newsletterReceiverId;
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 120, message = "{com.hallocasa.validator.constraints.Email.message}")
    @Column(name = "email")
    @Pattern(regexp = ValidationPatterns.EMAIL_PATTERN, message = "{com.hallocasa.validator.constraints.Email.message}")
    private String email;
    @Column(name = "language")
    @Convert(converter = LanguageConverter.class)
    private Language language;

    public NewsletterReceiver() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setNewsletterReceiverId(Integer newsletterReceiverId) {
        this.newsletterReceiverId = newsletterReceiverId;
    }

    public Integer getNewsletterReceiverId() {
        return newsletterReceiverId;
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

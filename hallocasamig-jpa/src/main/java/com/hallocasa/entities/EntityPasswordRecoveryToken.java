/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "password_recovery_token")
@NamedQueries({
    @NamedQuery(name = EntityPasswordRecoveryToken.QUERY_FIND_BY_CONTENT_AND_ACTIVE,
            query = "select t from EntityPasswordRecoveryToken t where t.tokenContent = ?1 and"
                    + " t.dueDate >= CURRENT_TIMESTAMP"),
    @NamedQuery(name = EntityPasswordRecoveryToken.QUERY_DELETE_BY_ID,
            query = "delete from EntityPasswordRecoveryToken t where t.idAssociated = ?1")
})
public class EntityPasswordRecoveryToken implements Serializable, HallocasaEntity {
    
     /* static fields */
    private static final long serialVersionUID = 1L;
    public static final String QUERY_FIND_BY_CONTENT_AND_ACTIVE = "Token.findByContentAndActive";
    
    public static final String QUERY_DELETE_BY_ID = "Token.deleteById";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_associated")
    private Integer idAssociated;
    
    @Column(name = "token_content")
    private String tokenContent;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "due_date")
    private Date dueDate;
    
    @Column(name = "active")
    private Boolean active;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expedition_date")
    private Date expeditionDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getIdAssociated() {
        return idAssociated;
    }

    public void setIdAssociated(Integer idAssociated) {
        this.idAssociated = idAssociated;
    }

    public String getTokenContent() {
        return tokenContent;
    }

    public void setTokenContent(String tokenContent) {
        this.tokenContent = tokenContent;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(Date expeditionDate) {
        this.expeditionDate = expeditionDate;
    }   
}

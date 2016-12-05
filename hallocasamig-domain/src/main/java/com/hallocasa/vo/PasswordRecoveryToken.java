package com.hallocasa.vo;

import java.io.Serializable;
import java.util.Date;

import com.hallocasa.vo.i.ValueObject;

public class PasswordRecoveryToken implements ValueObject, Serializable {

	private static final long serialVersionUID = 1530680168438607489L;

    private Integer id;

    private Integer idAssociated;
    
    private String tokenContent;
    
    private Date dueDate;
   
    private Boolean active;

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

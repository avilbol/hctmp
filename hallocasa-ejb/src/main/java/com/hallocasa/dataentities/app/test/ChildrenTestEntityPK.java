package com.hallocasa.dataentities.app.test;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChildrenTestEntityPK {
	
	@Column(name = "psp_ent_id")
	private Integer pspEntId;
	
	@Column(name = "sec_psp_ent_id")
	private String secPspEntId;


	public Integer getPspEntId() {
		return pspEntId;
	}

	public void setPspEntId(Integer pspEntId) {
		this.pspEntId = pspEntId;
	}

	public String getSecPspEntId() {
		return secPspEntId;
	}

	public void setSecPspEntId(String secPspEntId) {
		this.secPspEntId = secPspEntId;
	}

	
}
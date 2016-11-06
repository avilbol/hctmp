package com.hallocasa.dataentities.app.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.hallocasa.commons.vo.interfaces.HallocasaEntity;

@Entity
@Table(name = "test_child")
public class ChildrenTestEntity implements Serializable, HallocasaEntity {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 2855771158959112060L;
	
	@Column(name = "attr")
	public String attr;
	
	@EmbeddedId
	private ChildrenTestEntityPK childrenTestEntityPK;
	
	@MapsId("pspEntId")
	@JoinColumn(name = "psp_ent_id", referencedColumnName = "id")
	@ManyToOne
	private ParentSpEntity pspEnt;
	
	@MapsId("secPspEntId")
	@JoinColumn(name = "sec_psp_ent_id", referencedColumnName = "id")
	@ManyToOne
	private SecondParentSpEntity secPspEnt;
	
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	@ManyToOne
	public TestEntity parent;

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public TestEntity getParent() {
		return parent;
	}

	public void setParent(TestEntity parent) {
		this.parent = parent;
	}

	public ChildrenTestEntityPK getChildrenTestEntityPK() {
		if(childrenTestEntityPK == null){
			childrenTestEntityPK = new ChildrenTestEntityPK();
		}
		return childrenTestEntityPK;
	}

	public void setChildrenTestEntityPK(ChildrenTestEntityPK childrenTestEntityPK) {
		this.childrenTestEntityPK = childrenTestEntityPK;
	}

	public ParentSpEntity getPspEnt() {
		if(pspEnt == null){
			pspEnt =  new ParentSpEntity();
		}
		return pspEnt;
	}

	public void setPspEnt(ParentSpEntity pspEnt) {
		this.pspEnt = pspEnt;
	}
	
	public SecondParentSpEntity getSecPspEnt() {
		if(secPspEnt == null){
			secPspEnt =  new SecondParentSpEntity();
		}
		return secPspEnt;
	}

	public void setSecPspEnt(SecondParentSpEntity pspEnt) {
		this.secPspEnt = pspEnt;
	}
}

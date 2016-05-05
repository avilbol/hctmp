package com.hallocasa.dataentities.app.test;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.dataentities.converters.ImageContainerConverter;

@Entity
@Table(name = "test")
public class TestEntity implements Serializable, HallocasaEntity {

	 /* static fields */
    private static final long serialVersionUID = 1L;

    public static final String QUERY_FIND_BY_ID = "TestEntity.findById";
    
    /* instance variables */
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "other_attribute")
    private String otherAttribute;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ChildrenTestEntity> children;
    
	public TestEntity() {
		super();
	}
	
	public TestEntity(Long id, String otherAttribute) {
		super();
		this.id = id;
		this.otherAttribute = otherAttribute;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOtherAttribute() {
		return otherAttribute;
	}

	public void setOtherAttribute(String otherAttribute) {
		this.otherAttribute = otherAttribute;
	}

	public List<ChildrenTestEntity> getChildren() {
		return children;
	}

	public void setChildren(List<ChildrenTestEntity> children) {
		this.children = children;
	}
}

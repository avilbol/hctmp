package com.hallocasa.dataentities.app;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "image_url")
    @Convert(converter = ImageContainerConverter.class)
    private ImageContainer imageUrl;
    
    @Column(name = "other_attribute")
    private String otherAttribute;

    
    
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

	public ImageContainer getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(ImageContainer imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getOtherAttribute() {
		return otherAttribute;
	}

	public void setOtherAttribute(String otherAttribute) {
		this.otherAttribute = otherAttribute;
	}
}
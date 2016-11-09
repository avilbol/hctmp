package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

@Entity
@Table(name = "property_field_option")
public class EntityPropertyFieldOption implements HallocasaEntity{

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="property_field_id")
	private Integer propertyFieldId;
	
	@Column(name="option_id")
	private Integer option_id ;
	
    @Column(name="data1")
    private String data1;
    
    @Column(name="data2")
    private String data2;
    
    @Column(name="data3")
    private String data3;
    
    @Column(name="data4")
    private String data4;
    
    @Column(name="data5")
    private String data5;
    
    @Column(name="data6")
    private String data6;
    
    @Column(name="data7")
    private String data7;
    
    @Column(name="data8")
    private String data8;
    
    @Column(name="extended_data1")
    private String extendedData1;
    
    @Column(name="extended_data2")
    private String extendedData2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPropertyFieldId() {
		return propertyFieldId;
	}

	public void setPropertyFieldId(Integer propertyFieldId) {
		this.propertyFieldId = propertyFieldId;
	}

	public Integer getOption_id() {
		return option_id;
	}

	public void setOption_id(Integer option_id) {
		this.option_id = option_id;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public String getData4() {
		return data4;
	}

	public void setData4(String data4) {
		this.data4 = data4;
	}

	public String getData5() {
		return data5;
	}

	public void setData5(String data5) {
		this.data5 = data5;
	}

	public String getData6() {
		return data6;
	}

	public void setData6(String data6) {
		this.data6 = data6;
	}

	public String getData7() {
		return data7;
	}

	public void setData7(String data7) {
		this.data7 = data7;
	}

	public String getData8() {
		return data8;
	}

	public void setData8(String data8) {
		this.data8 = data8;
	}

	public String getExtendedData1() {
		return extendedData1;
	}

	public void setExtendedData1(String extendedData1) {
		this.extendedData1 = extendedData1;
	}

	public String getExtendedData2() {
		return extendedData2;
	}

	public void setExtendedData2(String extendedData2) {
		this.extendedData2 = extendedData2;
	}
}

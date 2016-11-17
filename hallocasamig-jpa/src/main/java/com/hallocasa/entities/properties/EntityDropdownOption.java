package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

@Entity
@Table(name = "dropdown_option")
public class EntityDropdownOption implements HallocasaEntity{

	@Id
	@Column(name="id")
	private Integer id;
	
	@JoinColumn(name = "dropdown_option_group_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityDropdownOptionGroup dropdownOptionGroup;
	
	@Column(name="option_id")
	private Integer optionId;
	
	@Column(name="name")
	private String name;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
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

	public EntityDropdownOptionGroup getDropdownOptionGroup() {
		return dropdownOptionGroup;
	}

	public void setDropdownOptionGroup(EntityDropdownOptionGroup dropdownOptionGroup) {
		this.dropdownOptionGroup = dropdownOptionGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package com.hallocasa.vo.options;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO representing an option of a multiple option property field 
 * (typically dropdowns)
 */
public class DropdownOption implements ValueObject, Serializable {

	private static final long serialVersionUID = -8521029765839743518L;

	private Integer optionId;

	private String name;
	
    private String data1;
    
    private String data2;
    
    private String data3;
    
    private String data4;
    
    private String data5;
    
    private String data6;
    
    private String data7;
    
    private String data8;
    
    private String extendedData1;
    
    private String extendedData2;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

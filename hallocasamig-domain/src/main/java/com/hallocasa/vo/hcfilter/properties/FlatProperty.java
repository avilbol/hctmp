package com.hallocasa.vo.hcfilter.properties;

/**
 * This value object represents an hallocasa property with fields 
 * traduced to everything readable internall properties
 * @author Alexander Villamil
 */
public class FlatProperty {

	private String title;
	private String urlImage;
	private String locationDescription;
	private String basicDescription;
	private String marketPrice;
	private Integer area;
	private Integer roomNumber;
	private Integer bathNumber;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrlImage() {
		return urlImage;
	}
	
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	public String getLocationDescription() {
		return locationDescription;
	}
	
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
	
	public String getBasicDescription() {
		return basicDescription;
	}
	
	public void setBasicDescription(String basicDescription) {
		this.basicDescription = basicDescription;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getBathNumber() {
		return bathNumber;
	}

	public void setBathNumber(Integer bathNumber) {
		this.bathNumber = bathNumber;
	}
}

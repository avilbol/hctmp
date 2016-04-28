package com.hallocasa.commons.vo.properties;

import java.math.BigDecimal;
import java.util.List;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.CurrencyVOAmmount;

public class PropertyBasicInfo {

	private List<Language> languages;
	
	private String title;
	
	private String propertyDescription;
	
	private String locationDescription;
	
	private Language mainLanguage;
	
	private CurrencyVOAmmount marketPrice;
	
	private BigDecimal area;

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public Language getMainLanguage() {
		return mainLanguage;
	}

	public void setMainLanguage(Language mainLanguage) {
		this.mainLanguage = mainLanguage;
	}

	public CurrencyVOAmmount getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(CurrencyVOAmmount marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}
}

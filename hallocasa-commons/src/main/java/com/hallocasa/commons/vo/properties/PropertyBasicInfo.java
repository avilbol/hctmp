package com.hallocasa.commons.vo.properties;

import static com.hallocasa.commons.constants.PropertyConstants.AREA_FIELD;
import static com.hallocasa.commons.constants.PropertyConstants.LANGUAGES_FIELD;
import static com.hallocasa.commons.constants.PropertyConstants.LOCATION_DESCRIPTION_FIELD;
import static com.hallocasa.commons.constants.PropertyConstants.MAIN_LANGUAGE_FIELD;
import static com.hallocasa.commons.constants.PropertyConstants.MARKET_PRICE_FIELD;
import static com.hallocasa.commons.constants.PropertyConstants.PROPERTY_DESCRIPTION_FIELD;
import static com.hallocasa.commons.constants.PropertyConstants.TITLE_FIELD;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.annotations.PropertyFieldValueParser;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CurrencyVOAmmount;

@SuppressWarnings("serial")
public class PropertyBasicInfo implements Serializable {

	@PropertyFieldValueParser(id = LANGUAGES_FIELD, methodToExecute = "parseToLanguageList")
	private List<Language> languages;
	
	@PropertyFieldValueParser(id = TITLE_FIELD, methodToExecute = "parseToMultiLanguage")
	private MultiLanguageText title;
	
	@PropertyFieldValueParser(id = PROPERTY_DESCRIPTION_FIELD, methodToExecute = "parseToMultiLanguage")
	private MultiLanguageText propertyDescription;
	
	@PropertyFieldValueParser(id = LOCATION_DESCRIPTION_FIELD, methodToExecute = "parseToMultiLanguage")
	private MultiLanguageText locationDescription;
	
	@PropertyFieldValueParser(id = MAIN_LANGUAGE_FIELD, methodToExecute = "parseToLanguage")
	private Language mainLanguage;
	
	@PropertyFieldValueParser(id = MARKET_PRICE_FIELD, methodToExecute = "parseToCurrency")
	private CurrencyVOAmmount marketPrice;
	
	@PropertyFieldValueParser(id = AREA_FIELD, methodToExecute = "parseToBigDecimal")
	private BigDecimal area;

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public MultiLanguageText getTitle() {
		return title;
	}

	public void setTitle(MultiLanguageText title) {
		this.title = title;
	}

	public MultiLanguageText getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(MultiLanguageText propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	public MultiLanguageText getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(MultiLanguageText locationDescription) {
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

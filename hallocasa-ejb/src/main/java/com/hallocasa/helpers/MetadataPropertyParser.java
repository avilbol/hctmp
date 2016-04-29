package com.hallocasa.helpers;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.Coordinate;
import com.hallocasa.commons.vo.CurrencyVOAmmount;
import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.commons.vo.StateVO;

public class MetadataPropertyParser {

	Gson gson = new GsonBuilder().create();
	
	public MultiLanguageText parseToMultiLanguage(String text){
		return new MultiLanguageText(text);
	}
	
	public List<Language> parseToLanguageList(String text){
		 return gson.fromJson(text, getLanguageListAttributeType());
	}
	
	
	public Language parseToLanguage(String text){
		return Language.valueOf(text);
	}
	
	
	public String parseToSingleText(String text){
		return text;
	}
	
	public Integer parseToSingleInteger(String text){
		return Integer.parseInt(text);
	}
	
	public BigDecimal parseToBigDecimal(String text){
		return new BigDecimal(text);
	}
	
	public CurrencyVOAmmount parseToCurrency(String text){ 
		return gson.fromJson(text, CurrencyVOAmmount.class);
	}
	
	public List<ImageContainer> parseToImageContainerList(String text){	
		return gson.fromJson(text, getImageContainerListAttributeType());
	}
	
	public ImageContainer parseToImageContainer(String text){
		return gson.fromJson(text, ImageContainer.class);
	}
	
	public StateVO parseToState(String text){
		StateVO stateVO = new StateVO();
		stateVO.setId(Long.parseLong(text));
		return stateVO;
	}
	
	public CityVO parseToCity(String text){
		CityVO cityVO = new CityVO();
		cityVO.setId(Long.parseLong(text));
		return cityVO;
	}
	
	public Coordinate parseToCoordinate(String text){
		return gson.fromJson(text, Coordinate.class);
	}
	
	
    public Type getLanguageListAttributeType() {
        return new TypeToken<List<Language>>() {}.getType();
    }
    
    public Type getImageContainerListAttributeType() {
        return new TypeToken<List<ImageContainer>>() {}.getType();
    }
	
}

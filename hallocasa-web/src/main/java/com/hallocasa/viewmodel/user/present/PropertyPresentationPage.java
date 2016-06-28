package com.hallocasa.viewmodel.user.present;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CurrencyVOAmmount;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.model.application.CurrencyGlobalApplication;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;

/**
 * View model for property presentation data
 * 
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean
@ViewScoped
public class PropertyPresentationPage {

	@Inject
    private WebSession webSession;
    
	@Inject
   	private HallocasaApplicationImpl halloCasaApplication;
    
	@Inject
    private CurrencyGlobalApplication currencyGlobal;
	
    
	private static String DEFAULT_URL_IMG = "/propertyimage/propertyudefault.jpg";
	
	public String getCityName(Long cityId){
    	MultiLanguageText mtValue = halloCasaApplication.getCityMap().get(cityId);
    	return mtValue.getLangValue(webSession.getCurrentLanguage());
    }
    
    public String titleValue(PropertyVO property){
    	Language mainLang = property.getPropertyBasicInfo().getMainLanguage();
    	MultiLanguageText title = property.getPropertyBasicInfo().getTitle();
    	return title.getLangValue(mainLang);
    }
    
    public String propertyDescriptionValue(PropertyVO property){
    	Language mainLang = property.getPropertyBasicInfo().getMainLanguage();
    	MultiLanguageText title = property.getPropertyBasicInfo().getPropertyDescription();
    	return title.getLangValue(mainLang);
    }
    
    public String currencyValue(CurrencyVOAmmount ammount){
    	if(ammount == null || ammount.getValue() == null){
    		return null;
    	}
    	DecimalFormat formatter = new DecimalFormat("#,###");
    	BigDecimal exchangedValue = new BigDecimal(currencyGlobal.rateExchange(ammount.getValue(), 
    			ammount.getCurrency(), webSession.getCurrentCurrency()));
    	return formatter.format(exchangedValue) + " " + 
    			webSession.getCurrentCurrency().getAbbreviation();
    }
    
    public String squareMetersValue(BigDecimal sqValue){
    	if(sqValue == null){
    		return null;
    	}
    	return sqValue.toString() + " m\u00B2";
    }
    
    public String langValue(MultiLanguageText value){
    	return value.getLangValue(webSession.getCurrentLanguage());
    }
    
    public String getDefaultImageUrl(){
		return DEFAULT_URL_IMG;
	}
}

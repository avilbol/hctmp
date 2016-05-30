package com.hallocasa.viewmodel.user.profile;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.commons.vo.CurrencyVOAmmount;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.Currency;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.controlaccess.ForbiddenException;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;

@ManagedBean
@ViewScoped
public class PropertyListPage {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -6006649582382274792L;

	/* instance variables */
    private UserVO user;

    private static String DEFAULT_URL_IMG = "/propertyimage/propertydefault.jpg";
    

    @Inject
    private ViewContext viewContext;
    
    /* dependencies */
    @Inject
    private WebSession webSession;
    @Inject
    private NavigationHandler navigationHandler;
    @EJB
    private UserServices userServices;
    @EJB
    private PropertyServices propertyServices;
    @Inject
	private HallocasaApplicationImpl halloCasaApplication;
    
    
    private List<PropertyVO> propertyList;

    /**
     * Default constructor
     */
    public PropertyListPage() {
    }
    /**
     * Initialize the bean
     */
    @PostConstruct
    public void initialize() {
       propertyList = propertyServices.find(webSession.getCurrentUser());
    }
    
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
    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
    	CurrencyVO selectedCurrencyVO = null;
    	for(CurrencyVO currency : halloCasaApplication.getCurrencies()){
    		if(currency.getId().equals(ammount.getCurrency().getId())){
    			selectedCurrencyVO = currency;
    			break;
    		}
    	}
    	return formatter.format(ammount.getValue()) + " " + 
    			selectedCurrencyVO.getAbbreviation();
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
    
    /**
     * Process click event over edit button
     */
    public void onDeletePropertyClick(){
        viewContext.showGlobalInfoMessage("Eliminado", "Eliminado correctamente");
    }
	public List<PropertyVO> getPropertyList() {
		return propertyList;
	}
	
	public void setPropertyList(List<PropertyVO> propertyList) {
		this.propertyList = propertyList;
	}
	
	public String getDefaultImageUrl(){
		return DEFAULT_URL_IMG;
	}
}

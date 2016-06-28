package com.hallocasa.viewmodel.user.profile;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.commons.vo.CurrencyVOAmmount;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.model.application.CurrencyGlobalApplication;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.context.ViewContext;

@ManagedBean
@ViewScoped
public class PropertyListPage {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -6006649582382274792L;


    private static String DEFAULT_URL_IMG = "/propertyimage/propertyudefault.jpg";
    

    @Inject
    private ViewContext viewContext;
    
    /* dependencies */
    @Inject
    private WebSession webSession;
    @EJB
    private UserServices userServices;
    @EJB
    private PropertyServices propertyServices;
    @Inject
	private HallocasaApplicationImpl halloCasaApplication;
    @Inject
   	private CurrencyGlobalApplication currencyGlobal;
    
    @ManagedProperty(value = "#{globalProfilePage}")
	private GlobalProfilePage globalProfilePage;


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
    
    /**
     * Process click event over edit button
     */
    public void onDeletePropertyClick(PropertyVO propertyVO){
    	propertyServices.delete(propertyVO);
    	globalProfilePage.refreshProperties();
        viewContext.showGlobalInfoMessage("Properties.Delete.Succesful", null);
    }
	
	public String getDefaultImageUrl(){
		return DEFAULT_URL_IMG;
	}
	
	public List<PropertyVO> getPropertyList(){
		return globalProfilePage.getPropertyVOList();
	}
	public GlobalProfilePage getGlobalProfilePage() {
		return globalProfilePage;
	}
	public void setGlobalProfilePage(GlobalProfilePage globalProfilePage) {
		this.globalProfilePage = globalProfilePage;
	}
}

package com.hallocasa.viewmodel.user.properties;

import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.model.controlaccess.ForbiddenException;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;
import com.hallocasa.view.utils.FormatUtils;

/**
 * View model for property list page
 * 
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean(name="propertyReadPage")
@ViewScoped
public class PropertyReadPage implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 7692825786747068241L;
	
	/* dependencies */
	@Inject
	private WebSession webSession;
	@EJB
	private UserServices userServices;
	@Inject
    private NavigationHandler navigationHandler;
	@EJB
	private PropertyServices propertyServices;

	/**
	 * Property id taken
	 */
	private String propertyIdStr;
	
	/**
	 * Property to show details
	 */
	private PropertyVO property;
	
	/**
	 * Default constructor
	 */
	public PropertyReadPage() {
	}

	/**
	 * Initialize the bean
	 */
	@PostConstruct
	public void initialize() {
		property = propertyServices.findByPropertyId(this.getPropertyIdStr());
	    if (property == null){
	        // it should never 
	        throw new ForbiddenException();
	    }
	}

	/**
	 * Process click event over edit button
	 */
	public void goToPublicProfile() {
		HashMap<ViewParamEnum, String> params = new HashMap<ViewParamEnum, String>();
		params.put(ViewParamEnum.USER_ID, this.getUser().getId().toString());
		navigationHandler.redirectToPage(HallocasaViewEnum.PUBLIC_PROFILE, params);
	}

	/**
	 * Getter for user
	 * 
	 * @return user
	 */
	public UserVO getUser() {
		return this.getProperty().getUser();
	}

	public String getUsername() {
		return FormatUtils.getDefensiveLabel(this.getUser().getFullName());
	}

	public boolean isUsernamePending() {
		return this.getUser().getFullName().equals("");
	}

	public String getCountryName() {
		return this.getUser().getCountry().getCountryName()
				.getText(webSession.getCurrentLanguage());
	}

	public String getStateName() {
		return this.getUser().getState().getStateName()
				.getText(webSession.getCurrentLanguage());
	}

	public String getCityName() {
		return this.getUser().getCity().getCityName()
				.getText(webSession.getCurrentLanguage());
	}

	public String getWebsiteName() {
		return FormatUtils.getDefensiveLabel(this.getUser().getWebSite());
	}

	public String getWebsiteLink() {
		return FormatUtils.buildWebString(this.getUser().getWebSite(), false);
	}

	public String getSkypeName() {
		return FormatUtils.getDefensiveLabel(this.getUser().getSkype());
	}

	public String getLinkedInName() {
		return FormatUtils.getDefensiveLabel(this.getUser().getLinkedIn());
	}

	public String getLinkedInLink() {
		return FormatUtils.buildWebString(this.getUser().getLinkedIn(), true);
	}

	public boolean getWebsitePending() {
		return FormatUtils.isEmptyValue(this.getUser().getWebSite());
	}

	public boolean getTelephonePending() {
		return this.getUser().getTelephone() == null
				|| FormatUtils.isEmptyValue(this.getUser().getTelephone()
						.getNumber());
	}

	public boolean getSkypePending() {
		return FormatUtils.isEmptyValue(this.getUser().getSkype());
	}

	public boolean getLinkedInPending() {
		return FormatUtils.isEmptyValue(this.getUser().getLinkedIn());
	}


	public PropertyVO getProperty() {
		return property;
	}

	public void setProperty(PropertyVO property) {
		this.property = property;
	}

	public String getPropertyIdStr() {
		return propertyIdStr;
	}

	public void setPropertyIdStr(String propertyIdStr) {
		this.propertyIdStr = propertyIdStr;
	}
	
	public String getName(MultiLanguageText name) {
		return name.getText(webSession.getCurrentLanguage());
	}
	
	public String getUserCityName() {
		return getUser().getCity().getCityName()
				.getText(webSession.getCurrentLanguage());
	}
}

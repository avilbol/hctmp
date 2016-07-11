package com.hallocasa.viewmodel.user.properties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.controlaccess.ForbiddenException;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;
import com.hallocasa.view.utils.FormatUtils;

/**
 * View model for public profile page
 *
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean
@ViewScoped
public class PropertyDetailPage implements Serializable{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 6405708913149271548L;
	
	/* dependencies */
    @Inject
    private WebSession webSession;
	
	@EJB
    private UserServices userServices;
	
	@EJB
    private PropertyServices propertyServices;

	@Inject
    private NavigationHandler navigationHandler;
	
	@Inject
	private HallocasaApplicationImpl halloCasaApplication;
	
	/**
	 * Property to indicate that google maps component must be start in default
	 * coordinates
	 */
	private boolean initMapInDefaultCity;

	/**
	 * Property to indicate that google maps component must be start in city
	 * selected coordinates
	 */
	private boolean initMapInLayoutCity;

	/**
	 * Property to indicate that google maps component must be start in city
	 * selected coordinates
	 */
	private boolean initMapInMarker;
	
	/**
	 * Property id taken
	 */
	private String propertyIdStr;
	
	/**
	 * Property to show details
	 */
	private PropertyVO property;
	

	/**
	 * Initialize the bean
	 */
	public void initialize() {
		property = propertyServices.findByPropertyId(this.getPropertyIdStr());
	    if (property == null){
	        // it should never 
	        throw new ForbiddenException();
	    }
	    evaluateGoogleMapsInit();
	}

	/**
	 * Process click event over edit button
	 */
	public void goToPublicProfile() {
		HashMap<ViewParamEnum, String> params = new HashMap<ViewParamEnum, String>();
		params.put(ViewParamEnum.USER_ID, this.getUser().getId().toString());
		navigationHandler.redirectToPage(HallocasaViewEnum.PUBLIC_PROFILE, params);
	}
	
	private void evaluateGoogleMapsInit() {
		initMapInMarker = false;
		initMapInDefaultCity = false;
		initMapInLayoutCity = false;
		CityVO city = getProperty().getPropertyLocationInfo().getCity();
		Map<Long, BigDecimal> latMap = halloCasaApplication.getCityLatMap();
		Map<Long, BigDecimal> lngMap = halloCasaApplication.getCityLngMap();
		if (getProperty().getPropertyLocationInfo().getLatitude() != null
				&& getProperty().getPropertyLocationInfo().getLongitude() != null) {
			initMapInMarker = true;
		} else if (city != null && latMap.get(city.getId()) != null && lngMap.get(city.getId()) != null) {
			initMapInLayoutCity = true;
		} else {
			initMapInDefaultCity = true;
		}
	}
	
	public Double getLngCityLayout() {
		Map<Long, BigDecimal> lngMap = halloCasaApplication.getCityLngMap();
		BigDecimal vlu = lngMap.get(getProperty().getPropertyLocationInfo()
				.getCity().getId());
		if(vlu == null){
			return null;
		}
		return vlu.doubleValue();
	}

	public Double getLatCityLayout() {
		Map<Long, BigDecimal> latMap = halloCasaApplication.getCityLatMap();
		BigDecimal vlu = latMap.get(getProperty().getPropertyLocationInfo()
				.getCity().getId());
		if(vlu == null){
			return null;
		}
		return vlu.doubleValue();
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

	public boolean isInitMapInDefaultCity() {
		return initMapInDefaultCity;
	}

	public void setInitMapInDefaultCity(boolean initMapInDefaultCity) {
		this.initMapInDefaultCity = initMapInDefaultCity;
	}

	public boolean isInitMapInLayoutCity() {
		return initMapInLayoutCity;
	}

	public void setInitMapInLayoutCity(boolean initMapInLayoutCity) {
		this.initMapInLayoutCity = initMapInLayoutCity;
	}

	public boolean isInitMapInMarker() {
		return initMapInMarker;
	}

	public void setInitMapInMarker(boolean initMapInMarker) {
		this.initMapInMarker = initMapInMarker;
	}
}
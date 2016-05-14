package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.model.controlaccess.ForbiddenException;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;

/**
 * Managed bean for profile global data
 * 
 * @author avillamil
 *
 */
@ManagedBean
@ViewScoped
public class GlobalProfilePage implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -5595203894982061966L;

	/**
	 * Constant when I do not want to use creation wizard
	 */
	private static final boolean DO_NOT_USE_CREATION_WIZARD = false;

	/**
	 * Constant when I want to use creation wizard
	 */
	private static final boolean USE_CREATION_WIZARD = true;

	/**
	 * Default constant param
	 */
	private static final boolean NON_OPTION = false;

	/**
	 * Navigation handler
	 */
	@Inject
	private NavigationHandler navigationHandler;
	
	/**
	 * Property services
	 */
	@EJB
    private PropertyServices propertyServices;
	
	/**
	 * User services
	 */
	@EJB
    private UserServices userServices;

	/**
	 * Option selected for submenu
	 */
	private MenuOption selectedOption;

	/**
	 * Mode in property tab
	 */
	private PropertyTabMode propertyTabMode;

	/**
	 * Mode in profile tab
	 */
	private ProfileTabMode profileTabMode;

	/**
	 * User to manage in global profile page
	 */
	private UserVO user;
	
	/**
	 * Property VO list
	 */
	private List<PropertyVO> propertyVOList;
	
	/**
	 * Target property
	 */
	private PropertyVO propertyInEdition;
	
	@Inject
    private WebSession webSession;

	/**
	 * Option used in query string
	 */
	private static final String QUERY_STRING_OPTION = "option";

	/* Inner types */
	public enum MenuOption {

		PROFILE("properties/tabs/profile-tab.xhtml"), PROPERTIES("properties/tabs/property-tab.xhtml");

		private final String url;

		public String getUrl() {
			return url;
		}

		private MenuOption(String url) {
			this.url = url;
		}
	}

	public enum PropertyTabMode {
		CREATE("../sections/property-wizard.xhtml", USE_CREATION_WIZARD), EDIT("../sections/property-wizard.xhtml",
				DO_NOT_USE_CREATION_WIZARD), DETAIL("../sections/property-details.xhtml",
						NON_OPTION), VIEW("../property-list.xhtml", NON_OPTION);

		private final String url;

		private final boolean param;

		public String getUrl() {
			return url;
		}

		public boolean isParam() {
			return param;
		}

		private PropertyTabMode(String url, boolean param) {
			this.url = url;
			this.param = param;
		}
	}

	public enum ProfileTabMode {
		VIEW("../sections/profile-section.xhtml"), EDIT("../sections/edit-profile-section.xhtml");

		private final String url;

		public String getUrl() {
			return url;
		}

		private ProfileTabMode(String url) {
			this.url = url;
		}
	}

	/**
	 * Initialize
	 */
	@PostConstruct
	public void initialize() {
		refreshUser();
		refreshProperties();
		// select section
		String requestedOptionStr = navigationHandler.getPageParams().get(QUERY_STRING_OPTION);
		try {
			selectedOption = MenuOption.valueOf(requestedOptionStr.toUpperCase());
		} catch (IllegalArgumentException | NullPointerException e) {
			selectedOption = MenuOption.PROFILE;
		} finally {
			profileTabMode = ProfileTabMode.VIEW;
			propertyTabMode = PropertyTabMode.VIEW;
		}
	}
	
	/**
	 * Do a refresh of the current user instance
	 */
	public void refreshUser(){
		user = userServices.find(webSession.getCurrentUser().getId());
		if (user == null) {
			// it should never
			throw new ForbiddenException();
		}
	}
	
	/**
	 * Do a refresh of the current property list instance
	 */
	public void refreshProperties(){
		propertyVOList = propertyServices.find(user);
		if (propertyVOList == null) {
			// it should never
			throw new ForbiddenException();
		}
	}

	public void onProfileMenuSelect() {
		selectedOption = MenuOption.PROFILE;
		profileTabMode = ProfileTabMode.VIEW;
	}

	public void onPropertiesMenuSelect() {
		selectedOption = MenuOption.PROPERTIES;
		propertyTabMode = PropertyTabMode.VIEW;
	}

	public void goToCreateProperty() {
		selectedOption = MenuOption.PROPERTIES;
		propertyTabMode = PropertyTabMode.CREATE;
		propertyInEdition = new PropertyVO();
		propertyInEdition.setUser(user);
	}
	
	public void goToEditProperty(PropertyVO vo) {
		selectedOption = MenuOption.PROPERTIES;
		propertyTabMode = PropertyTabMode.EDIT;
		propertyInEdition = vo;
		propertyInEdition.setUser(user);
	}
	
	public void goToEditProfile() {
		selectedOption = MenuOption.PROFILE;
		profileTabMode = ProfileTabMode.EDIT;
	}

	public void goToEditView() {
		selectedOption = MenuOption.PROPERTIES;
		profileTabMode = ProfileTabMode.VIEW;
	}

	/**
	 * @return the activeSectionIndex
	 */
	public int getActiveSectionIndex() {
		return selectedOption.ordinal();
	}

	public MenuOption getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(MenuOption selectedOption) {
		this.selectedOption = selectedOption;
	}

	public PropertyTabMode getPropertyTabMode() {
		return propertyTabMode;
	}

	public void setPropertyTabMode(PropertyTabMode propertyTabMode) {
		this.propertyTabMode = propertyTabMode;
	}

	public ProfileTabMode getProfileTabMode() {
		return profileTabMode;
	}

	public void setProfileTabMode(ProfileTabMode profileTabMode) {
		this.profileTabMode = profileTabMode;
	}

	public String getUrlPage(String value) {
		HashMap<ViewParamEnum, String> params = new HashMap<>();
		params.put(ViewParamEnum.OPTION, value);
		return navigationHandler.buildAbsoluteUrl(HallocasaViewEnum.MY_PROFILE_TEMP, params);
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public PropertyVO getPropertyInEdition() {
		return propertyInEdition;
	}

	public void setPropertyInEdition(PropertyVO propertyInEdition) {
		this.propertyInEdition = propertyInEdition;
	}

	public List<PropertyVO> getPropertyVOList() {
		return propertyVOList;
	}

	public void setPropertyVOList(List<PropertyVO> propertyVOList) {
		this.propertyVOList = propertyVOList;
	}
}

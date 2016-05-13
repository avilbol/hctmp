package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;

/**
 * Managed bean for profile global data
 * @author avillamil
 *
 */
@ManagedBean
@ViewScoped
public class GlobalProfilePage implements Serializable{
	
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
	 * Option used in query string
	 */
	private static final String QUERY_STRING_OPTION = "option";
	
	 /* Inner types */
    public enum MenuOption {

        PROFILE("properties/tabs/profile-tab.xhtml"),
        PROPERTIES("properties/tabs/property-tab.xhtml");
        
        private final String url;

		public String getUrl() {
			return url;
		}

		private MenuOption(String url) {
            this.url = url;
        }        
    }
    
    public enum PropertyTabMode {
        CREATE("../sections/property-wizard.xhtml", USE_CREATION_WIZARD),
        EDIT("../sections/property-wizard.xhtml", DO_NOT_USE_CREATION_WIZARD),
        DETAIL("../sections/property-details.xhtml", NON_OPTION),
        VIEW("../property-list.xhtml", NON_OPTION);
        
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
        VIEW("../sections/profile-section.xhtml"),
        EDIT("../sections/edit-profile-section.xhtml");
        
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
        // select section
        String requestedOptionStr
                = navigationHandler.getPageParams().get(QUERY_STRING_OPTION);
        try{
        	selectedOption = MenuOption.valueOf(requestedOptionStr.toUpperCase());
        } catch(IllegalArgumentException | NullPointerException e){
        	selectedOption = MenuOption.PROFILE;
        } finally{
        	profileTabMode = ProfileTabMode.VIEW;
        	propertyTabMode = PropertyTabMode.VIEW;
        }
    }
    
    public void onProfileMenuSelect(){
    	selectedOption = MenuOption.PROFILE;
    	profileTabMode = ProfileTabMode.VIEW;
    }
    
    public void onPropertiesMenuSelect(){
    	selectedOption = MenuOption.PROPERTIES;
    	propertyTabMode = PropertyTabMode.VIEW;
    } 
    
    public void goToEditProfile(){
    	selectedOption = MenuOption.PROFILE;
    	profileTabMode = ProfileTabMode.EDIT;
    } 
    
    public void goToEditView(){
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
	
	public String getUrlPage(String value){
		HashMap<ViewParamEnum, String> params = new HashMap<>();
		params.put(ViewParamEnum.OPTION, value);
		return navigationHandler.buildAbsoluteUrl(HallocasaViewEnum.MY_PROFILE_TEMP, params);
	}
}

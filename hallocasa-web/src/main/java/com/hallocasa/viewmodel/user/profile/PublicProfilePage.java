package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;
import java.util.List;

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
import com.hallocasa.view.utils.FormatUtils;

/**
 * View model for public profile page
 *
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean
@ViewScoped
public class PublicProfilePage implements Serializable{

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
	
	private String userIdStr;
	
	private UserVO user;
	
	private List<PropertyVO> propertyList;
	
	public void initialize(){
		user = userServices.find(Integer.parseInt(this.getUserIdStr()));
	    if (user == null){
	        // it should never 
	        throw new ForbiddenException();
	    }
	    propertyList = propertyServices.find(user);
		if (propertyList == null) {
			// it should never
			throw new ForbiddenException();
		}
	}
	
	public void goToViewProperty(){
		navigationHandler.redirectToPage(HallocasaViewEnum.PROPERTY_DETAIL);
	}
	
	public boolean getWebsitePending(){
        return FormatUtils.isEmptyValue(user.getWebSite());
    }
	
	public boolean getSkypePending(){
        return FormatUtils.isEmptyValue(user.getSkype());
    }
	
	public boolean getTelephonePending(){
        return user.getTelephone() == null ||
        		FormatUtils.isEmptyValue(user.getTelephone().getNumber());
    }
	
	public boolean getLinkedInPending(){
        return FormatUtils.isEmptyValue(user.getLinkedIn());
    }

	public String getUserIdStr() {
		return userIdStr;
	}

	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
	
	public String getUsername(){
	    return FormatUtils.getDefensiveLabel(user.getFullName());
	}
	
	public String getCountryName(){
        return user.getCountry().getCountryName().getText(webSession.getCurrentLanguage());
    }
    
    public String getStateName(){
        return user.getState().getStateName().getText(webSession.getCurrentLanguage());
    }
    
    public String getCityName(){
        return user.getCity().getCityName().getText(webSession.getCurrentLanguage());
    }
    
    public String getWebsiteName(){
        return FormatUtils.getDefensiveLabel(user.getWebSite());
    }
    
    public String getWebsiteLink(){
        return FormatUtils.buildWebString(user.getWebSite(), false);
    }
    
    public String getSkypeName(){
        return FormatUtils.getDefensiveLabel(user.getSkype());
    }
    
    public String getLinkedInName(){
        return FormatUtils.getDefensiveLabel(user.getLinkedIn());
    }
    
    public String getLinkedInLink(){
        return FormatUtils.buildWebString(user.getLinkedIn(), true);
    }

	public List<PropertyVO> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<PropertyVO> propertyList) {
		this.propertyList = propertyList;
	}
}

package com.hallocasa.viewmodel.user.present;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.StrategySort;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;

/**
 * View model for profile browser
 * 
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean
@ViewScoped
public class ProfileBrowserPage {

	/* dependencies */
	@Inject
	private WebSession webSession;

	@EJB
	private UserServices userServices;

	private Language language;

	private List<UserVO> userList;
    
    @Inject
    private NavigationHandler navigationHandler;
	

	private static final Integer USER_AMMOUNT_TO_SHOW = 10;

	@PostConstruct
	public void init() {
		this.language = webSession.getCurrentLanguage();
		userList = userServices.loadUserVOList(USER_AMMOUNT_TO_SHOW,
				StrategySort.RANDOM);
	}

	public void onLoadMoreProfiles() {
		userList = userServices.loadUserVOList(userList, USER_AMMOUNT_TO_SHOW,
				StrategySort.RANDOM);
	}
	
	public void onProfileSelect(UserVO userVO){
		HashMap<ViewParamEnum, String> params = new HashMap<ViewParamEnum, String>();
		params.put(ViewParamEnum.USER_ID, userVO.getId().toString());
		navigationHandler.redirectToPage(HallocasaViewEnum.PUBLIC_PROFILE, params);
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<UserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
}

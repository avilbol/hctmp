/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.user.profile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.imagemanager.UserProfileImageManager;
import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.StateVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.services.location.local.CountryServices;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.i18n.Messages;
import com.hallocasa.view.utils.FormatUtils;

/**
 * View model for profile page
 *
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean
@ViewScoped
public class ProfileEditPage implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -6148027555237326384L;

	/* class variables */
	private static final Logger LOG = Logger.getLogger(ProfileEditPage.class.getName());

	@EJB
	CountryServices countryServices;

	@EJB
	private UserServices userServices;

	/* dependencies */
	@Inject
	private WebSession webSession;
	@Inject
	private ViewContext viewContext;
	@Inject
	private HallocasaApplicationImpl halloCasaApplication;

	@ManagedProperty(value = "#{globalProfilePage}")
	private GlobalProfilePage globalProfilePage;

	/**
	 * Language list
	 */
	private List<Language> languages;

	/**
	 * States list
	 */
	private List<StateVO> states;

	/**
	 * States list
	 */
	private List<CityVO> cities;

	/**
	 * User description
	 */
	private Map<Language, String> userDescription;

	/**
	 * User images path
	 */
	private String userImagesPath;

	/**
	 * Default constructor
	 */
	public ProfileEditPage() {
	}

	/**
	 * Initialize the bean
	 */
	@PostConstruct
	public void initialize() {
		if (this.getUser().getCountry() != null) {
			states = countryServices.getStates(this.getUser().getCountry().getId());
		}
		if (this.getUser().getState() != null) {
			cities = countryServices.getCities(this.getUser().getState().getId());
		}
		languages = halloCasaApplication.getLanguages();
	}

	/**
	 * Process change on checkboxes of languages selected
	 */
	public void processLanguagesSelectedChange() {
		if (this.getUser().getUserDescription() == null) {
			this.getUser().setUserDescription(new MultiLanguageText());
		}
		for (Language lang : this.getUser().getSpokenLanguages()) {
			if (FormatUtils.isEmptyValue(this.getUser().getUserDescription().getLangValue(lang))) {
				this.getUser().getUserDescription().setLangValue(lang, "");
			}
		}
	}

	public void onPostUpload() {
		viewContext.showGlobalInfoMessage("Profile.Save.Succesful.Detail", "Profile.Save.Succesful.Detail");
	}

	/**
	 * Process click event over save button
	 */
	public void processSaveClick() {
		try {
			this.getUser().setConfirmedFlag(Boolean.TRUE);
			if (this.getUser().getImage() != null) {
				manageUserImage();
			}
			userServices.save(this.getUser());
			viewContext.showGlobalInfoMessage("Profile.Save.Succesful.Detail", "Profile.Save.Succesful.Detail");
			globalProfilePage.refreshUser();
			globalProfilePage.onProfileMenuSelect();
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, "Unexpected error", ex);
			viewContext.showGlobalErrorMessage(Messages.UNEXPECTED_ERROR, null);
			viewContext.showGlobalCustomErrorMessage(ex.getMessage(), "");
		}
	}

	public void manageUserImage() {
		try {
			UserProfileImageManager imageManager = new UserProfileImageManager(this.getUser().getImage(),
					this.getUser().getId());
			imageManager.setDoClean(true);
			imageManager.manageImage();
		} catch (IOException e) {
			viewContext.showGlobalErrorMessage("Common.UnexpectedError.Message", "Common.UnexpectedError.Message");
		}
	}

	/**
	 * Process when button "Edit image" is clicked
	 */
	public void processEditImageClick() {

	}

	/**
	 * Process click event on cancel button
	 */
	public void processCancelClick() {
		globalProfilePage.onProfileMenuSelect();
	}

	/**
	 * Process country selection event
	 */
	public void processCountrySelect() {
		cities = new ArrayList<>();
		this.getUser().setCity(null);
		this.getUser().setState(null);
		if (this.getUser().getCountry() != null)
			states = countryServices.getStates(this.getUser().getCountry().getId());
		else
			states = new ArrayList<>();
	}

	/**
	 * Process country selection event
	 */
	public void processStateSelect() {
		this.getUser().setCity(null);
		if (this.getUser().getState() != null)
			cities = countryServices.getCities(this.getUser().getState().getId());
		else
			cities = new ArrayList<>();
	}

	public String getCountryName(CountryVO country) {
		return country.getCountryName().getText(webSession.getCurrentLanguage());
	}

	public String getStateName(StateVO state) {
		return state.getStateName().getText(webSession.getCurrentLanguage());
	}

	public String getCityName(CityVO city) {
		return city.getCityName().getText(webSession.getCurrentLanguage());
	}

	/**
	 * Getter for user
	 *
	 * @return user
	 */
	public UserVO getUser() {
		return globalProfilePage.getUser();
	}

	public List<StateVO> getStates() {
		return states;
	}

	public void setStates(List<StateVO> states) {
		this.states = states;
	}

	public List<CityVO> getCities() {
		return cities;
	}

	public void setCities(List<CityVO> cities) {
		this.cities = cities;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public String getUserDescription(Language language) {
		return this.getUser().getUserDescription().getText(language);
	}

	public Map<Language, String> getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(Map<Language, String> userDescription) {
		this.userDescription = userDescription;
	}

	public String getUserImagesPath() {
		return userImagesPath;
	}

	public void setUserImagesPath(String userImagesPath) {
		this.userImagesPath = userImagesPath;
	}

	public GlobalProfilePage getGlobalProfilePage() {
		return globalProfilePage;
	}

	public void setGlobalProfilePage(GlobalProfilePage globalProfilePage) {
		this.globalProfilePage = globalProfilePage;
	}
}

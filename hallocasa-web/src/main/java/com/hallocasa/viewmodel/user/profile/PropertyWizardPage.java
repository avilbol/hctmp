package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.StateVO;
import com.hallocasa.commons.vo.properties.PropertyBasicInfo;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.location.local.CountryServices;
import com.hallocasa.view.utils.FormatUtils;
import com.hallocasa.viewmodel.user.profile.GlobalProfilePage.PropertyTabMode;

/**
 * Managed bean for wizard in properties
 * 
 * @author avillamil
 * 
 */
@ManagedBean
@ViewScoped
public class PropertyWizardPage implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 5974980192541279963L;

	@Inject
	private WebSession webSession;

	@Inject
	private HallocasaApplicationImpl halloCasaApplication;

	/**
	 * Country services
	 */
	@EJB
	CountryServices countryServices;

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
	 * Property description
	 */
	private Map<Language, String> propertyDescription;

	/**
	 * Property description
	 */
	private Map<Language, String> locationDescription;

	/**
	 * Wizard for creation
	 */
	private boolean wizardCreation;

	@ManagedProperty(value = "#{globalProfilePage}")
	private GlobalProfilePage globalProfilePage;

	/**
	 * Phase of the process in wizard
	 */
	private PropertyWizardPhase wizardPhase;

	/**
	 * Initialize
	 */
	@PostConstruct
	public void initialize() {
		PropertyTabMode mode = globalProfilePage.getPropertyTabMode();
		if (mode.equals(PropertyTabMode.CREATE)) {
			wizardPhase = PropertyWizardPhase.INIT;
			wizardCreation = true;
		}
		if (mode.equals(PropertyTabMode.EDIT)) {
			wizardPhase = PropertyWizardPhase.EDITION;
			wizardCreation = false;
		}
	}

	public void exit() {
		globalProfilePage.onPropertiesMenuSelect();
	}

	/**
	 * Process change on checkboxes of languages selected
	 */
	public void processLanguagesSelectedChange() {
		PropertyBasicInfo pbInfo = this.getPropertyInEdition().getPropertyBasicInfo();
		if (pbInfo.getLocationDescription() == null) {
			pbInfo.setLocationDescription(new MultiLanguageText());
		}
		if (pbInfo.getPropertyDescription() == null) {
			pbInfo.setPropertyDescription(new MultiLanguageText());
		}
		if (pbInfo.getTitle() == null) {
			pbInfo.setTitle(new MultiLanguageText());
		}
		for (Language lang : halloCasaApplication.getLanguages()) {
			if (FormatUtils.isEmptyValue(pbInfo.getPropertyDescription().getLangValue(lang))) {
				pbInfo.getPropertyDescription().setLangValue(lang, "");
			}
			if (FormatUtils.isEmptyValue(pbInfo.getLocationDescription().getLangValue(lang))) {
				pbInfo.getLocationDescription().setLangValue(lang, "");
			}
			if (FormatUtils.isEmptyValue(pbInfo.getTitle().getLangValue(lang))) {
				pbInfo.getTitle().setLangValue(lang, "");
			}
		}
	}

	/**
	 * Process country selection event
	 */
	public void processCountrySelect() {
		cities = new ArrayList<>();
		this.getPropertyInEdition().getPropertyLocationInfo().setCity(null);
		this.getPropertyInEdition().getPropertyLocationInfo().setState(null);
		if (this.getPropertyInEdition().getCountry() != null)
			states = countryServices.getStates(this.getPropertyInEdition().getCountry().getId());
		else
			states = new ArrayList<>();
	}

	/**
	 * Process country selection event
	 */
	public void processStateSelect() {
		StateVO stateSelected = this.getPropertyInEdition().getPropertyLocationInfo().getState();
		this.getPropertyInEdition().getPropertyLocationInfo().setCity(null);
		if (stateSelected != null)
			cities = countryServices.getCities(stateSelected.getId());
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

	public String getValueName(MultiLanguageText name) {
		return name.getText(webSession.getCurrentLanguage());
	}

	/**
	 * Constants to represent possible phases of process.
	 */
	private enum PropertyWizardPhase {
		INIT, EDITION
	};

	public boolean isWizardCreation() {
		return wizardCreation;
	}

	public void setWizardCreation(boolean wizardCreation) {
		this.wizardCreation = wizardCreation;
	}

	public boolean getShowInitWizard() {
		return this.wizardCreation && this.wizardPhase.equals(PropertyWizardPhase.INIT);
	}

	public boolean getShowEditWizard() {
		return this.wizardPhase.equals(PropertyWizardPhase.EDITION);
	}

	public void goToInitZone() {
		this.wizardPhase = PropertyWizardPhase.INIT;
	}

	public void onProcessInitZone() {
		this.wizardPhase = PropertyWizardPhase.EDITION;
	}

	public GlobalProfilePage getGlobalProfilePage() {
		return globalProfilePage;
	}

	public void setGlobalProfilePage(GlobalProfilePage globalProfilePage) {
		this.globalProfilePage = globalProfilePage;
	}

	public PropertyVO getPropertyInEdition() {
		return this.getGlobalProfilePage().getPropertyInEdition();
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
}

package com.hallocasa.view.components.utils;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.view.components.base.BaseComponent;
import com.hallocasa.view.utils.FormatUtils;

/**
 * 
 * @author Alexander Villamil
 */
@FacesComponent("profileEntryComponent")
@ViewScoped
public class ProfileEntryComponent extends BaseComponent {

	enum Attributes {
		value, size, language, onClick, presentation
	}

	/**
	 * User for component data
	 */
	private UserVO profile;

	/**
	 * Component for apply command link
	 */
	private UIComponent commandLink;

	/**
	 * Language for presentation
	 */
	private Language language;

	/**
	 * Identifier of complete presentation
	 */
	public static final String COMPLETE_PRESENTATION = "complete";

	/**
	 * Identifier of lite presentation
	 */
	public static final String LITE_PRESENTATION = "lite";

	/**
	 * Maximum allowed of languages in lite version
	 */
	private static final int MAX_SPOKEN_LANGUAGES_ALLOWED_IN_LITE = 4;

	/**
	 * Maximum allowed of characters before description truncation
	 */
	private static final int MAX_SIZE_OF_CHARACTERS_IN_DESCRIPTION = 70;

	/* dependencies */
	@Inject
	private WebSession webSession;

	public String getCityName() {
		return profile.getCity().getCityName()
				.getText(webSession.getCurrentLanguage());
	}

	@Override
	public void initialize() {
		language = (Language) this.getAttributes().get(
				Attributes.language.toString());
		if(language == null){
			language = Language.en;
		}
		profile = (UserVO) this.getAttributes()
				.get(Attributes.value.toString());
	}

	@Override
	protected void saveComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
		map.put(Attributes.value.toString(), this.profile);
		map.put(Attributes.language.toString(), this.language);
	}

	@Override
	protected void restoreComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
		this.profile = (UserVO) map.get(Attributes.value.toString());
		this.language = (Language) map.get(Attributes.language.toString());
	}

	public String load(UserTypeVO userType) {
		return userType.getUserTypeName().getLangValue(this.language);
	}

	public String loadDescription() {
		return FormatUtils.truncateWithPoints(this.profile.getUserDescription()
				.getLangValue(this.profile.getMainSpokenLanguage()),
				MAX_SIZE_OF_CHARACTERS_IN_DESCRIPTION);
	}

	public List<Language> loadSpokenLanguageList() {
		List<Language> languageList = this.profile.getSpokenLanguages();
		if (languageList.size() <= MAX_SPOKEN_LANGUAGES_ALLOWED_IN_LITE
				|| getAttributes().get(Attributes.presentation.toString())
						.equals(COMPLETE_PRESENTATION)) {
			return languageList;
		}
		return languageList.subList(0, MAX_SPOKEN_LANGUAGES_ALLOWED_IN_LITE);
	}

	public String loadAdditionalLanguagesMark() {
		List<Language> languageList = this.profile.getSpokenLanguages();
		if (languageList.size() <= MAX_SPOKEN_LANGUAGES_ALLOWED_IN_LITE
				|| getAttributes().get(Attributes.presentation.toString())
						.equals(COMPLETE_PRESENTATION)) {
			return "";
		}
		return "...";
	}

	public UserVO getProfile() {
		return profile;
	}

	public void setProfile(UserVO profile) {
		this.profile = profile;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public UIComponent getCommandLink() {
		return commandLink;
	}

	public void setCommandLink(UIComponent commandLink) {
		this.commandLink = commandLink;
	}

	public String getLitePresentation() {
		return LITE_PRESENTATION;
	}

	public String getCompletePresentation() {
		return COMPLETE_PRESENTATION;
	}
}

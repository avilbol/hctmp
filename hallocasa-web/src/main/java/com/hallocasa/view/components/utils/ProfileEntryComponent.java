package com.hallocasa.view.components.utils;

import java.util.HashMap;

import javax.enterprise.context.RequestScoped;
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
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;

/**
 * 
 * @author Alexander Villamil
 */
@FacesComponent("profileEntryComponent")
@ViewScoped
public class ProfileEntryComponent extends BaseComponent {

	enum Attributes {
		user, size, language, onClick
	}

	/**
	 * User for component data
	 */
	private UserVO user;

	/**
	 * Component for apply command link
	 */
	private UIComponent commandLink;
	
	/**
	 * Language for presentation
	 */
	private Language language;
	
	/* dependencies */
    @Inject
    private WebSession webSession;

    public String getCityName(){
        return user.getCity().getCityName().getText(webSession.getCurrentLanguage());
    }

	@Override
	public void initialize() {
		language =  (Language) this.getAttributes().get(
				Attributes.language.toString());
		user = (UserVO) this.getAttributes().get(
				Attributes.user.toString());
	}

	@Override
	protected void saveComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
		map.put(Attributes.user.toString(), this.user);
		map.put(Attributes.language.toString(), this.language);
	}

	@Override
	protected void restoreComponent(FacesContext facesContext,
			HashMap<String, Object> map){
		this.user = (UserVO) map.get(Attributes.user.toString());
		this.language = (Language) map.get(Attributes.language.toString());
	}

	public String load(UserTypeVO userType) {
		return userType.getUserTypeName().getLangValue(this.language);
	}

	public String loadDescription() {
		return this.user.getUserDescription().getLangValue(
				this.user.getMainSpokenLanguage());
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
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
}

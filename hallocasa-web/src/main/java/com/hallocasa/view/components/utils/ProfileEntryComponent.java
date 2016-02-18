package com.hallocasa.view.components.utils;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.view.components.base.BaseComponent;

/**
 * 
 * @author Alexander Villamil
 */
@FacesComponent("profileEntryComponent")
@ViewScoped
public class ProfileEntryComponent extends BaseComponent {

	enum Attributes {
		user, size, language
	}

	/**
	 * User for component data
	 */
	private UserVO user;

	/**
	 * Language for presentation
	 */
	private Language language;

	@Override
	@PostConstruct
	protected void initialize() {
		
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
		if(user == null){
			user = (UserVO) this.getAttributes().get(
					Attributes.user.toString());
		}
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public Language getLanguage() {
		if(language == null){
			language = Language.valueOf((String) this.getAttributes().get(
					Attributes.language.toString()));
		}
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	

}

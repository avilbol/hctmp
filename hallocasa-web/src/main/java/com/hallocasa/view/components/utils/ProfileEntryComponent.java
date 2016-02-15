package com.hallocasa.view.components.utils;

import java.util.HashMap;

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
		USER, SIZE, LANGUAGE
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
	protected void initialize() {
		this.user = (UserVO) this.getAttributes().get(
				Attributes.USER.toString());
		this.language = Language.valueOf((String) this.getAttributes().get(
				Attributes.LANGUAGE.toString()));
	}

	@Override
	protected void saveComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
		map.put(Attributes.USER.toString(), this.user);
		map.put(Attributes.LANGUAGE.toString(), this.language);
	}

	@Override
	protected void restoreComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
		this.user = (UserVO) map.get(Attributes.USER.toString());
		this.language = (Language) map.get(Attributes.LANGUAGE.toString());
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

}

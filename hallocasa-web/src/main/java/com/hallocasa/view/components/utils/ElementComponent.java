package com.hallocasa.view.components.utils;

import java.util.HashMap;

import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.view.components.base.BaseComponent;

@FacesComponent("elementComponent")
@ViewScoped
public class ElementComponent extends BaseComponent {

	private UserVO user;
	
	@Override
	public void initialize() {
		user = (UserVO) this.getAttributes().get(
				"attr");
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	@Override
	protected void saveComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
	}

	@Override
	protected void restoreComponent(FacesContext facesContext,
			HashMap<String, Object> map) {	
	}
}

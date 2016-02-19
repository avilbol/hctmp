package com.hallocasa.view.components.utils;

import java.util.HashMap;

import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.view.components.base.BaseComponent;

@FacesComponent("elementComponent")
@ViewScoped
public class ElementComponent extends BaseComponent {

	UserVO user;
	
	protected void initialize(ComponentSystemEvent event) {
		user = (UserVO) this.getAttributes().get(
				"attr");
	}

	@Override
	protected void saveComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void restoreComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
}

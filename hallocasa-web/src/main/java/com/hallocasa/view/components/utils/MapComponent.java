package com.hallocasa.view.components.utils;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import com.hallocasa.view.components.base.BaseComponent;
import com.hallocasa.viewmodel.security.LoginDialog;

/**
 * Backing bean for google maps component
 * 
 * @author Alexander Villamil
 */
@FacesComponent("mapComponent")
@ViewScoped
public class MapComponent extends BaseComponent {

	private static final Logger LOG = Logger.getLogger(LoginDialog.class.getName());

	private enum Attributes {
		imageUrl, imageType, readOnly, defaultImageUrl, postUploadAction
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void saveComponent(FacesContext facesContext, HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void restoreComponent(FacesContext facesContext, HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}
}

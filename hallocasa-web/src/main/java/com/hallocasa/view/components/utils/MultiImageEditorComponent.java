package com.hallocasa.view.components.utils;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.view.components.base.BaseComponent;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.viewmodel.security.LoginDialog;

/**
 * Backing bean for multiimage edition component
 * 
 * @author Alexander Villamil
 */
@FacesComponent("multiImageEditorComponent")
@ViewScoped
public class MultiImageEditorComponent extends BaseComponent{

	private static final Logger LOG = Logger.getLogger(LoginDialog.class.getName());

	private enum Attributes {
		imageUrl, imageType, readOnly, defaultImageUrl
	}

	private List<ImageContainer> imageContainerList;

	@Inject
	private ViewContext viewContext;

	@Override
	@PostConstruct
	protected void initialize() {
		String imageUrl = (String) this.getAttributes().get(Attributes.imageUrl.toString());
	}

	@Override
	protected void saveComponent(FacesContext facesContext, HashMap<String, Object> map) {
	
	}

	@Override
	protected void restoreComponent(FacesContext facesContext, HashMap<String, Object> map) {
		
	}

	public void onImageUpload() {
		
	}

	public List<ImageContainer> getImageContainerList() {
		return imageContainerList;
	}

	public void setImageContainerList(List<ImageContainer> imageContainerList) {
		this.imageContainerList = imageContainerList;
	}	
}

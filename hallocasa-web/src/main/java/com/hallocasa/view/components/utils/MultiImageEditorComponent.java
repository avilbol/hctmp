package com.hallocasa.view.components.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.utils.ApplicationFileUtils;
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
@SuppressWarnings("unchecked")
public class MultiImageEditorComponent extends BaseComponent {

	private static final Logger LOG = Logger.getLogger(LoginDialog.class.getName());

	private enum Attributes {
		imageList, imageType, indexMainImage
	}

	private List<ImageContainer> imageContainerList;

	private AtomicInteger indexMainImageContainer;
	
	private ImageContainer imageInEdition;
	
	private static final String IMAGE_ATTR_INITIAL = "/propertyimage/default.jpg";

	@Inject
	private ViewContext viewContext;

	/**
	 * Init / restart image edition container
	 */
	private void startCurrentImgEdition() {
		imageInEdition = new ImageContainer(IMAGE_ATTR_INITIAL);
	}

	@Override
	protected void saveComponent(FacesContext facesContext, HashMap<String, Object> map) {
	}

	@Override
	protected void restoreComponent(FacesContext facesContext, HashMap<String, Object> map) {
	}

	public void onImageUploaded() {
		getImageContainerList().add(imageInEdition);
		startCurrentImgEdition();
	}
	
	/**
	 * Method to execute when user requests delete an image
	 * @param index
	 */
	public void onDeleteImage(int index){
		getImageContainerList().remove(index);
		if(getIndexMainImageContainer().get() == index){
			indexMainImageContainer.set(0);
		}
		if(indexMainImageContainer.get() > index){
			indexMainImageContainer.decrementAndGet();
		}
	}
	
	public boolean isMainImage(int index){
		return getIndexMainImageContainer().get() == index;
	}
	
	/**
	 * Method to execute when user requests select image as main
	 * @param index
	 */
	public void onSelectMainImage(int index){
		getIndexMainImageContainer().set(index);
	}

	public List<ImageContainer> getImageContainerList() {
		if(imageContainerList == null){
			imageContainerList = (List<ImageContainer>) this.getAttributes()
					.get(Attributes.imageList.toString());
		}
		return imageContainerList;
	}

	public void setImageContainerList(List<ImageContainer> imageContainerList) {
		this.imageContainerList = imageContainerList;
	}

	public ImageContainer getImageInEdition() {
		return imageInEdition;
	}

	public void setImageInEdition(ImageContainer imageInEdition) {
		this.imageInEdition = imageInEdition;
	}

	public AtomicInteger getIndexMainImageContainer() {
		if(indexMainImageContainer == null){
			indexMainImageContainer = (AtomicInteger) this.getAttributes()
					.get(Attributes.indexMainImage.toString());
		}
		return indexMainImageContainer;
	}

	public void setIndexMainImageContainer(AtomicInteger indexMainImageContainer) {
		this.indexMainImageContainer = indexMainImageContainer;
	}

	public void initialize() {
		startCurrentImgEdition();
	}
}

package com.hallocasa.commons.vo.properties;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.commons.annotations.PropertyFieldValueParser;
import com.hallocasa.commons.vo.ImageContainer;
import static com.hallocasa.commons.constants.PropertyConstants.*;

@SuppressWarnings("serial")
public class PropertyImageInfo implements Serializable {

	@PropertyFieldValueParser(id = IMAGES_FIELD, methodToExecute = "parseToImageContainerList")
	private List<ImageContainer> imageContainerList;
	
	@PropertyFieldValueParser(id = MAIN_IMAGE_FIELD, methodToExecute = "parseToImageContainer")
	private ImageContainer mainImage;

	public List<ImageContainer> getImageContainerList() {
		return imageContainerList;
	}

	public void setImageContainerList(List<ImageContainer> imageContainerList) {
		this.imageContainerList = imageContainerList;
	}

	public ImageContainer getMainImage() {
		return mainImage;
	}

	public void setMainImage(ImageContainer mainImage) {
		this.mainImage = mainImage;
	}
}

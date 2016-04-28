package com.hallocasa.commons.vo.properties;

import java.util.List;

import com.hallocasa.commons.vo.ImageContainer;

public class PropertyImageInfo {

	private List<ImageContainer> imageContainerList;
	
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

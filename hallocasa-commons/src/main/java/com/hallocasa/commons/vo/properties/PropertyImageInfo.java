package com.hallocasa.commons.vo.properties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.hallocasa.commons.annotations.PropertyFieldValueParser;
import com.hallocasa.commons.vo.ImageContainer;
import static com.hallocasa.commons.constants.PropertyConstants.*;

@SuppressWarnings("serial")
public class PropertyImageInfo implements Serializable {

	@PropertyFieldValueParser(id = IMAGES_FIELD, methodToExecute = "parseToImageContainerList")
	private List<ImageContainer> imageContainerList;
	
	@PropertyFieldValueParser(id = MAIN_IMAGE_FIELD, methodToExecute = "parseToImageContainer")
	private ImageContainer mainImage;

	private AtomicInteger indexMainImage;
	
	public List<ImageContainer> getImageContainerList() {
		if(imageContainerList == null){
			imageContainerList = new ArrayList<ImageContainer>();
		}
		return imageContainerList;
	}

	public void setImageContainerList(List<ImageContainer> imageContainerList) {
		this.imageContainerList = imageContainerList;
	}

	public ImageContainer getMainImage() {
		if(imageContainerList != null && mainImage == null){
			mainImage = imageContainerList.get(indexMainImage.get());
		}
		return mainImage;
	}

	public void setMainImage(ImageContainer mainImage) {
		this.mainImage = mainImage;
	}

	public AtomicInteger getIndexMainImage() {
		if(indexMainImage == null){
			return new AtomicInteger(0);
		}
		return indexMainImage;
	}

	public void setIndexMainImage(AtomicInteger indexMainImage) {
		this.indexMainImage = indexMainImage;
	}
}

package com.hallocasa.commons.imagemanager;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.utils.ApplicationFileUtils;

/**
 * Manager for image system location
 * 
 * @author Alexander Villamil
 */
public abstract class ImageManager {

	/**
	 * Params
	 */
	protected String[] params;

	/**
	 * Image container
	 */
	protected ImageContainer imageContainer;

	/**
	 * Flag for clean
	 */
	private boolean doClean;

	public ImageManager(ImageContainer imageContainer, String... params) {
		super();
		this.setParams(params);
		this.imageContainer = imageContainer;
	}

	public void manageImage() throws IOException {
		String imageUrl = imageContainer.getUrl();
		File sourceFile = new File(ApplicationFileUtils.getAbsoluteUrl(imageUrl));
		String userRelativePath = buildUserAbsolutePath();
		String userImageFilename = buildUserImageFilename();
		File destFile = new File(userRelativePath + userImageFilename);
		if (!sourceFile.getName().equals(destFile.getName())) {
			FileUtils.deleteQuietly(destFile);
			FileUtils.copyFile(sourceFile, destFile); 
		}
		String newImageUrl = ApplicationFileUtils.getRelativePath(imageUrl) 
				+ "/" + destFile.getName();
		imageContainer.setUrl(newImageUrl);
		if (doClean) {
			clean();
		}
	}

	/**
	 * Gets the filename of the file, according to params
	 * 
	 * @return the filename built
	 * @throws IOException
	 */
	public abstract String buildUserImageFilename() throws IOException;

	/**
	 * Gets the relative path of the file
	 * 
	 * @return the relative path appropriate
	 */
	public abstract String buildUserAbsolutePath();

	/**
	 * Clean the images generated randomly
	 * 
	 * @throws IOException
	 */
	public abstract void clean() throws IOException;

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public ImageContainer getImageContainer() {
		return imageContainer;
	}

	public void setImageContainer(ImageContainer imageContainer) {
		this.imageContainer = imageContainer;
	}

	public boolean isDoClean() {
		return doClean;
	}

	public void setDoClean(boolean doClean) {
		this.doClean = doClean;
	}
}

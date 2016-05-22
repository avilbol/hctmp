package com.hallocasa.commons.imagemanager;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.commons.utils.FormatUtils;
import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.utils.ApplicationFileUtils;

/**
 * Image manager for user property images
 * 
 * @author avillamil
 */
public class UserPropertyImageManager extends ImageManager {

	/**
	 * Enum for @UserPropertyImageManagerParams
	 * 
	 * @author avillamil
	 *
	 */
	private enum UserPropertyImageManagerParam {
		USER_ID, PROPERTY_ID
	}

	public UserPropertyImageManager(ImageContainer imageContainer, Long userId, String propertyId) {
		super(imageContainer, String.valueOf(userId), propertyId);
	}

	@Override
	public String buildUserImageFilename() throws IOException {
		File sourceFile = new File(ApplicationFileUtils.getAbsoluteUrl(imageContainer.getUrl()));
		String mimeType = ApplicationFileUtils.getMimeType(sourceFile);
		String ext = mimeType.equals("image/png") ? "png" : "jpg";
		String userNumber = params[UserPropertyImageManagerParam.USER_ID.ordinal()];
		String propertyId = params[UserPropertyImageManagerParam.PROPERTY_ID.ordinal()];
		String imageId = FormatUtils.randomStrId();
		return "/propertyu" + userNumber + "p" + propertyId + "i" + imageId + "." + ext;
	}

	@Override
	public String buildUserAbsolutePath() {
		return ApplicationFileUtils.getAbsolutePath(imageContainer.getUrl());
	}

	@Override
	public void clean() throws IOException {
		File f = new File(SystemConstants.PROPERTY_IMAGES_PATH);
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return !name.startsWith("propertyu");
			}
		});
		for (File file : matchingFiles) {
			FileUtils.forceDelete(file);
		}
	}

}

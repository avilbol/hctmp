package com.hallocasa.commons.imagemanager;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.utils.ApplicationFileUtils;

/**
 * Image manager for user profile images
 * @author avillamil
 */
public class UserProfileImageManager extends ImageManager {

	/**
	 * Enum for @UserProfileImageManagerParams
	 * 
	 * @author avillamil
	 *
	 */
	private enum UserProfileImageManagerParam {
		USER_ID
	}

	public UserProfileImageManager(ImageContainer imageContainer, Long userId) {
		super(imageContainer, String.valueOf(userId));
	}

	@Override
	public String buildUserImageFilename() throws IOException {
		File sourceFile = new File(ApplicationFileUtils.getAbsoluteUrl(imageContainer.getUrl()));
		String mimeType = ApplicationFileUtils.getMimeType(sourceFile);
		String ext = mimeType.equals("image/png") ? "png" : "jpg";
		String userNumber = params[UserProfileImageManagerParam.USER_ID.ordinal()];
		return "/user" + userNumber + "." + ext;
	}

	@Override
	public String buildUserAbsolutePath() {
		return ApplicationFileUtils.getAbsolutePath(imageContainer.getUrl());
	}

	@Override
	public void clean() throws IOException {
		File f = new File(SystemConstants.USER_IMAGES_PATH);
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return !name.startsWith("user");
			}
		});
		for (File file : matchingFiles) {
			FileUtils.forceDelete(file);
		}
	}
}

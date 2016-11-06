/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.commons.vo.ImageContainer.ImageContainerValue;

/**
 *
 * @author juan
 */
public class ApplicationFileUtils {

	private static final String SLASH = "/";

	/**
	 * Obtiene la ruta relativa de un archivo, usando su url completa
	 * 
	 * @param relativeUrl
	 *            Url del archivo
	 * @return La ruta relativa, sin especificar el nombre de archivo
	 */
	public static String getRelativePath(String relativeUrl) {
		if (relativeUrl == null || relativeUrl.equals("") || relativeUrl.length() == 1) {
			return "";
		}
		String[] elmts = relativeUrl.substring(1).split(SLASH);

		String firstElmt = elmts[0];
		String prefix = firstElmt;

		for (int i = 1; i < elmts.length - 1; i++) {
			prefix += (SLASH + elmts[i]);
		}
		return SLASH + prefix;
	}

	/**
	 * Obtiene la ruta f&iacute;sica de un archivo, usando la url relativa del
	 * archivo
	 * 
	 * @param relativeUrl
	 *            Ruta relativa del archivo
	 * @return Ruta f&iacute;sica del archivo
	 */
	public static String getAbsolutePath(String relativeUrl) {
		ImageContainerValue cvalue = ImageContainerValue.load(getRelativePath(relativeUrl));
		switch (cvalue) {
		case USER_IMAGE:
			return SystemConstants.USER_IMAGES_PATH;
		case PROPERTY_IMAGE:
			return SystemConstants.PROPERTY_IMAGES_PATH;
		case DEFAULT:
			return "";
		}
		return null;
	}

	public static String getFilename(String url) {
		if (url == null || url.equals("") || url.length() == 1) {
			return "";
		}
		String[] elmts = url.substring(1).split(SLASH);
		return elmts[elmts.length - 1];
	}

	public static String getMimeType(File file) throws IOException {
		try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
			return URLConnection.guessContentTypeFromStream(is);
		} catch (IOException e) {
			throw e;
		} 
	}

	public static String getImageMimeType(File file) throws IOException {
		return getMimeType(file).equals("image/png") ? "png" : "jpg";
	}

	public static String getAbsoluteUrl(String relativeUrl) {
		return getAbsolutePath(relativeUrl) + SLASH + getFilename(relativeUrl);
	}

	public static void main(String[] args) {
		System.out.println(ApplicationFileUtils.getAbsoluteUrl("/userimage/uuu.jpg"));
	}
}

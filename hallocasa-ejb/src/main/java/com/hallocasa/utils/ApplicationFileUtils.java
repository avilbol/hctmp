/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.utils;

import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.services.FileServices;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/**
 *
 * @author juan
 */
public class ApplicationFileUtils {

    private static final String SLASH = "/";
    
    public static String getRelativePath(String localUrl) {
        if (localUrl == null || localUrl.equals("") || localUrl.length() == 1) {
            return "";
        }
        String[] elmts = localUrl.substring(1).split(SLASH);

        String firstElmt = elmts[0];
        String prefix = firstElmt;

        for (int i = 1; i < elmts.length - 1; i++) {
            prefix += elmts[i];
        }
        return SLASH + prefix;
    }

    public static String getAbsolutePath(String localUrl) {
        ImageContainer.ImageContainerValue cvalue = ImageContainer.
                ImageContainerValue.load(getRelativePath(localUrl));
        switch (cvalue) {
            case USER_IMAGE:
                return FileServices.USER_IMAGES_PATH;
            case PROPERTY_IMAGE:
            	return "";
            case DEFAULT:
                return ""; // TODO : review
        }
        return null;
    }

    public static String getFilename(String localUrl) {
        if (localUrl == null || localUrl.equals("") || localUrl.length() == 1) {
            return "";
        }
        String[] elmts = localUrl.substring(1).split(SLASH);
        return elmts[elmts.length - 1];
    }

     public static String getMimeType(File file) throws IOException {
         InputStream is = new BufferedInputStream(new FileInputStream(file));
         return URLConnection.guessContentTypeFromStream(is);
    }
     
    public static String getImageMimeType(File file) throws IOException {
    	return getMimeType(file).equals("image/png") ? "png" : "jpg";
    }
    
    public static String getAbsoluteUrl(String localUrl) {
        return getAbsolutePath(localUrl) + SLASH + getFilename(localUrl);
    }
}

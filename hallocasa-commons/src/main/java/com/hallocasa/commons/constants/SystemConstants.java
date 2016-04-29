/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.constants;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;


/**
 *
 * @author David Mantilla
 */
public class SystemConstants {
    
    public static String SERVER_URL;
    
    static {
        Properties properties = new Properties();
        try {
            properties.load(SystemConstants.class.getClassLoader()
                    .getResourceAsStream(
                            "hallocasa.properties"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        APP_CONTEXT = properties.getProperty("context.root");
    }
    
    // TODO: these variables should be loaded from a properties file or database table
    public static String APP_CONTEXT;
    public static final String MAIL_CHIMP_API_KEY = "6ee4908f232f1f3f7b2d8d8319a8cb90-us10";
    public static final String MAIL_CHIMP_TEST1_LIST_ID = "645de5e37b"; // test1 list;
    public static final String DOWNLOADS_PATH = "/home/hallocasa/webcontent/downloads/";
    public static final String WEBCONTENT_IMAGES_PATH = "/home/hallocasa/webcontent/images/";
    public static final String IMAGES_PATH = APP_CONTEXT + "/resources/images";
    public static final String RESOURCES_PATH = APP_CONTEXT + "/resources";
    public static final Long PUBLIC_PROFILE_ID = 1L;
    
    //public static final String MAIL_CHIMP_NEWSLETTER_LIST_ID = "c2730bb54f"; // Newsletter list

}

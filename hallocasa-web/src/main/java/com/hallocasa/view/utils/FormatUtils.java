/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.utils;

/**
 *
 * @author avillamil
 */
public class FormatUtils {
	
	private static final String HTTP_PREFIX = "http://";
	
	private static final String HTTPS_PREFIX = "https://";
    
    public static boolean isNumeric(String value){
        try{
           Integer.parseInt(value);
           Double.parseDouble(value);
           Long.parseLong(value);
        }catch(NumberFormatException e){
           return false; 
        }
        return true;
    }
    
    public static String getDefensiveLabel(String value){
        if(value == null || value.equals("")){
            return JSFUtils.getViewBundleString("Common.Label.NotSpecified");
        }
        return value;
    }
    
    public static boolean isEmptyValue(String value){
        return value == null || value.equals("");
    }
    
    public static String buildWebString(String webStr, boolean https){
    	if(webStr == null || webStr.equals("")){
            return JSFUtils.getViewBundleString("Common.Label.NotSpecified");
        }
    	if(!webStr.startsWith(HTTP_PREFIX)){
    		return (https ? HTTPS_PREFIX : HTTP_PREFIX) + webStr;
    	}
    	return webStr;
    }
}

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
}

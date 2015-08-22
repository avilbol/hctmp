/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.conversion;

/**
 *
 * @author David Mantilla
 */
public class ConversionUtils {

    /**
     * Converts a string to integer silently (no exception). In case exception
     * ocrrus in the conversion, then default value is return instead.
     * Also in case value is null, then default value is return instead.
     * @param value String value to convert to Integer
     * @param defaultValue
     * @return The converted value
     */
    public static Integer silentIntToStr(String value, Integer defaultValue) {
        try {
            Integer intValue = new Integer(value);
            return intValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}

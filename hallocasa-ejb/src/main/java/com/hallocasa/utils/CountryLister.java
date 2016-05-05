/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.utils;

import java.util.Locale;

/**
 *
 * @author david
 */
public class CountryLister {

    public static void main(String[] args) {
        CountryLister countryLister = new CountryLister();
        countryLister.run();
    }

    public void run() {
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            System.out.println("Country Code = " + obj.getCountry()
                    + ", Country Name = " + obj.getDisplayCountry(Locale.US)
                    + "\t" + obj.getDisplayCountry(Locale.GERMAN)
                    + "\t" + obj.getDisplayCountry(new Locale("es", "CO")));
        }

        System.out.println("Done");
    }

}

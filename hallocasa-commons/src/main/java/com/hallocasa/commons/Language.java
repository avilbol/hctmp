/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons;

import java.util.Locale;

/**
 *
 * @author David Mantilla
 */
public enum Language {

    en("English"), 
    es("Espa\u00F1ol"), 
    de("Deutsch"),
    af("Africaans"),
    al("Algerian"),
    ar("Arabic"),
    am("Armenian"),
    bo("Bosnian"),
    bu("Bulgarian"),
    cn("Cantonese"),
    ct("Catalan"),
    ce("Creole"),
    cr("Croatian"),
    cz("Czech"),
    da("Danish"),
    dt("Dutch"),
    et("Estonian"),
    fi("Filipino"),
    fn("Finnish"),
    fl("Flemish"),
    fr("French"),
    ga("Gaelic"),
    gr("Greek"),
    he("Hebrew"),
    hi("Hindi"),
    hu("Hungarian"),
    ic("Icelandic"),
    ir("Irish"),
    it("Italian"),
    ja("Japanese"),
    ko("Korean"),
    la("Latvian"),
    li("Lithuanian"),
    ma("Mandarin"),
    mo("Moldovian"),
    mr("Moroccan"),
    no("Norwegian"),
    po("Polish"),
    pr("Portuguese"),
    ro("Romanian"),
    ru("Russian"),
    sc("Scottish"),
    sl("Slovakian"),
    sv("Slovene"),
    sw("Swedish"),
    sf("Swiss French"),
    sg("Swiss German"),
    th("Thai"),
    tu("Tunisian"),
    tk("Turkish"),
    ur("Ukrainian"),
    vi("Vietnamese");

    private final Locale locale;
    private final String languageName;

    private Language(String languageName) {
        locale = new Locale(this.name());
        this.languageName = languageName;
    }

    /**
     * Return the language Name
     *
     * @return
     */
    public String getLanguageName() {
        return languageName;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }
}

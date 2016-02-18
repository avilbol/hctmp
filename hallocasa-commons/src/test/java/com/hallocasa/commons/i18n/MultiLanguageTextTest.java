/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.i18n;

import com.hallocasa.commons.Language;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author david
 */
public class MultiLanguageTextTest {

    private static final String SPANISH_TEXT = "Imagine this text is in spanish";
    private static final String ENGLISH_TEXT = "Imagine this text is in english";
    private static final String GERMAN_TEXT = "Imagine this text is in german";
    private static final String JSON_TEXT = "{\"de\":\"" + GERMAN_TEXT
            + "\",\"en\":\"" + ENGLISH_TEXT + "\",\"es\":\"" + SPANISH_TEXT + "\"}";
    private static final String ONLY_2_LANG_JSON_TEXT = "{\"de\":\"" + GERMAN_TEXT
            + "\",\"en\":\"" + ENGLISH_TEXT + "\"}";
    private static final String WRONG_JSON_TEXT_2 = "{\"de\":\"" + GERMAN_TEXT
            + "\",\"en\":\"" + ENGLISH_TEXT + "\",\"es\":\"" + SPANISH_TEXT + "\"}";

    @Test
    public void testToJsonOk() {
        MultiLanguageText multiLanguageText = new MultiLanguageText();
        multiLanguageText.setLangValue(Language.de, GERMAN_TEXT);
        multiLanguageText.setLangValue(Language.en, ENGLISH_TEXT);
        multiLanguageText.setLangValue(Language.es, SPANISH_TEXT);
        Assert.assertEquals(JSON_TEXT,
                multiLanguageText.toJSON());
    }

    @Test
    public void testFromJsonOk() {
        MultiLanguageText multiLanguageText = new MultiLanguageText(JSON_TEXT);
        Assert.assertEquals(GERMAN_TEXT, multiLanguageText.getLangValue(Language.de));
        Assert.assertEquals(ENGLISH_TEXT, multiLanguageText.getLangValue(Language.en));
        Assert.assertEquals(SPANISH_TEXT, multiLanguageText.getLangValue(Language.es));
    }

    @Test
    public void testFrom2LangJsonOk() {
        MultiLanguageText multiLanguageText = new MultiLanguageText(ONLY_2_LANG_JSON_TEXT);
        Assert.assertEquals(GERMAN_TEXT, multiLanguageText.getLangValue(Language.de));
        Assert.assertEquals(ENGLISH_TEXT, multiLanguageText.getLangValue(Language.en));
        Assert.assertEquals(null, multiLanguageText.getLangValue(Language.es));
    }

    @Test
    public void testFromJsonFail() {
        MultiLanguageText multiLanguageText = new MultiLanguageText(ONLY_2_LANG_JSON_TEXT);
        Assert.assertEquals(GERMAN_TEXT, multiLanguageText.getLangValue(Language.de));
        Assert.assertEquals(ENGLISH_TEXT, multiLanguageText.getLangValue(Language.en));
        Assert.assertEquals(null, multiLanguageText.getLangValue(Language.es));
    }

    @Test
    public void testFromJsonWithEmptyStringOk() {
        MultiLanguageText multiLanguageText = new MultiLanguageText("");
        Assert.assertEquals(null, multiLanguageText.getLangValue(Language.de));
        Assert.assertEquals(null, multiLanguageText.getLangValue(Language.en));
        Assert.assertEquals(null, multiLanguageText.getLangValue(Language.es));
    }
}

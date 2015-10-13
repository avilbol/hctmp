/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.converters;

import com.hallocasa.commons.Language;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author david
 */
public class SpokenLanguagesConverterTest {

    private static final String SPOKEN_LANGUAGES_JSON = "[\"en\",\"es\"]";

    @Test
    public void testConvertLanguagesToJSON() {
        SpokenLanguagesConverter slc = new SpokenLanguagesConverter();

        ArrayList<Language> languages = new ArrayList<>();
        languages.add(Language.en);
        languages.add(Language.es);

        String json = slc.convertToDatabaseColumn(languages);
        Assert.assertEquals(SPOKEN_LANGUAGES_JSON, json);
    }

    @Test
    public void testConvertJSONToLanguages() {
        SpokenLanguagesConverter slc = new SpokenLanguagesConverter();

        List<Language> languages = slc.convertToEntityAttribute(SPOKEN_LANGUAGES_JSON);
        Assert.assertEquals(2, languages.size());
        Assert.assertTrue(languages.contains(Language.en));
        Assert.assertTrue(languages.contains(Language.es));
    }

}

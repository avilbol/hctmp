package com.hallocasa.dataentities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.hallocasa.commons.Language;

/**
 * Converter for operationContactType column
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Converter
public class LanguageConverter implements AttributeConverter<Language, String> {

    /* static fields */

    /**
     * Constructor
     */
    public LanguageConverter() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang
     * .Object)
     */
    @Override
    public String convertToDatabaseColumn(Language language) {
        if ( language == null ){
            return null;
        }
        return language.name();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang
     * .Object)
     */
    @Override
    public Language convertToEntityAttribute(String languageStr) {
        if ( languageStr == null ){
            return null;
        }
        for (Language language : Language.values()) {
            if (language.name().equals(languageStr)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unsupported language type id: "
                + languageStr);
    }

    /* instance variables */

    /* constructors */

    /* Methods */

    /* Getters & Setters */
}

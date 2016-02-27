/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.wcm;

import com.hallocasa.commons.Language;


/**
 *
 * @author David Mantilla
 */
public interface TranslationInterface {

    public String getTextDe();

    public String getTextEn();

    public String getTextEs();

    public String getText(Language language);

}

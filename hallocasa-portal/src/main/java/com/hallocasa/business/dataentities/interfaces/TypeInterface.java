/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.dataentities.interfaces;

import com.hallocasa.business.dataentities.TranslationInterface;
import com.hallocasa.commons.beans.Listable;

/**
 *
 * @author David Mantilla
 */
public interface TypeInterface extends Listable<Integer> {

    public TranslationInterface getTranslationName();

}

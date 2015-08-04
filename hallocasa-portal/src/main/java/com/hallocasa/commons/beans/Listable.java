/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.beans;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.beans.Identificable;

/**
 *
 * @author David Mantilla
 * @param <T>
 */
public interface Listable<T> extends Identificable<T> {

    /**
     * Return a label to show this item in a list
     *
     * @param language
     * @return
     */
    public String getLabel(Language language);
}

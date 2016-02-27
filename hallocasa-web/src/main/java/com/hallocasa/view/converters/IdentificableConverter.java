/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.converters;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.hallocasa.commons.beans.Identificable;

/**
 *
 * @author David Mantilla
 * @param <T>
 */
public class IdentificableConverter<T extends Identificable<?>> implements Converter, StateHolder, Serializable {


    private static final long serialVersionUID = 3357612825662940352L;

    protected HashMap<String, T> objectMap = new HashMap<>();
    public boolean transientFlag;

    /**
     * Initializes an instance of ProjObjectConverter
     */
    public IdentificableConverter() {
    }

    /**
     * Initializes an instance of ProjObjectConverter from a hashmap
     *
     * @param objectMap
     */
    public IdentificableConverter(HashMap<String, T> objectMap) {
        this.objectMap = objectMap;
    }

    /**
     * Initializes an instance of ProjObjectConverter from a list
     *
     * @param objectList List of options of the converter
     */
    public IdentificableConverter(List<T> objectList) {
        objectMap = new HashMap<>();
        for (T actual : objectList) {
            String id;
            id = actual.getId() == null ? null : actual.getId().toString();
            objectMap.put(id, actual);
        }
    }

    /**
     *
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     */
    @Override
    public T getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        T object = objectMap.get(arg2);
        if (object == null) {
            //logger.debug( "The id has not been found in the object map");
        }
        return object;
    }

    /**
     *
     * @param arg0
     * @param arg1
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
        if (value == null) {
            return null;
        }
        String id;
        T indentificable = (T) value;
        id = indentificable.getId() == null ? null : indentificable.getId().toString();
        return id;
    }

    /**
     * Adds and object to the converter hash Metodo que agrega un objeto al
     * hashmap de conversion
     *
     * @param object object to add
     */
    public void addElement(T object) {
        objectMap.put(object.getId() == null ? null : object.getId().toString(), object);
    }

    /**
     * Removes an object from the converter hash Método que elimina un objeto
     * del hashmap de conversion
     *
     * @param object object to remove
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void removeElement(T object) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        objectMap.remove(object.getId() == null ? null : object.getId().toString());
    }

    /**
     * @return
     */
    public int getElementMapSize() {
        return objectMap.keySet().size();
    }

    /* (non-Javadoc)
     * @see javax.faces.component.StateHolder#isTransient()
     */
    @Override
    public boolean isTransient() {
        return transientFlag;
    }

    /* (non-Javadoc)
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void restoreState(FacesContext arg0, Object arg1) {
        objectMap = (HashMap<String, T>) arg1;
    }

    /* (non-Javadoc)
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    @Override
    public Object saveState(FacesContext arg0) {
        return objectMap;
    }

    /* (non-Javadoc)
     * @see javax.faces.component.StateHolder#setTransient(boolean)
     */
    @Override
    public void setTransient(boolean arg0) {
        transientFlag = arg0;

    }

    /**
     * Clear the converter added values
     */
    public void clear() {
        objectMap.clear();
    }

    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */
}

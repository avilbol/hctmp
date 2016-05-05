/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hallocasa.dataentities.interfaces.TypeInterface;
import com.hallocasa.commons.Language;

/**
 *
 * @author David Mantilla
 */
@Stateless
public class TypeServices implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "RealStateDatabasePU")
    EntityManager em;

    /**
     * Return a list of values of a particular type
     *
     * @param <T>
     * @param entityClass
     * @param language
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends TypeInterface> List<T> getTypeList(Class<T> entityClass,
            Language language) {
        String entityClassName = entityClass.getSimpleName();

        StringBuilder str = new StringBuilder();
        str.append("select entity from ").append(entityClassName);
        str.append(" entity order by entity.translationName.text");
        str.append(language.name().substring(0, 1).toUpperCase());
        str.append(language.name().substring(1, 2));

        Query q = em.createQuery(str.toString());
        List<T> list = q.getResultList();
        return list;
    }

    /*
     * ------------------------------------------------------------ Getters y
     * setters ------------------------------------------------------------
     */
}

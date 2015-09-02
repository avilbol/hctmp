/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.tests.database;

import com.hallocasa.dataentities.app.UserType;
import javax.persistence.EntityManager;

/**
 *
 * @author david
 */
public class BasicDataFiller implements DatabaseFiller {
    
    @Override
    public void fillDatabase(EntityManager em) {
        createUserTypes(em);
    }
    
    private void createUserTypes(EntityManager em) {
        UserType userType = new UserType(1L, "Inmobiliario");
        em.persist(userType);
    }
    
}

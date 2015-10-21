/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.tests.database;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.dataentities.app.Country;
import com.hallocasa.dataentities.app.State;
import com.hallocasa.dataentities.app.UserType;
import javax.persistence.EntityManager;

/**
 *
 * @author david
 */
public class BasicDataFiller implements DatabaseFiller {

    public static long COLOMBIA_ID;
    public static long USA_ID;
    public static long GERMANY_ID;
    public static long CUNDINAMARCA_ID;
    public static long BOYACA_ID;
    public static long META_ID;

    @Override
    public void fillDatabase(EntityManager em) {
        createUserTypes(em);
    }

    private void createUserTypes(EntityManager em) {
        // user types
        UserType userType = new UserType(1L, new MultiLanguageText("{'en':'Inmobiliario'}"));
        em.persist(userType);

        // countries
        Country country = new Country(null, new MultiLanguageText("{'en':'Colombia'}"), "CO");
        em.persist(country);
        COLOMBIA_ID = country.getId();
        Country colombia = country;
        country = new Country(null, new MultiLanguageText("{'en':'United States'}"), "US");
        em.persist(country);
        USA_ID = country.getId();
        country = new Country(null, new MultiLanguageText("{'en':'Germany'}"), "DE");
        em.persist(country);
        GERMANY_ID = country.getId();

        // states 
        State state = new State(null, "Cundinamarca", colombia);
        em.persist(state);
        CUNDINAMARCA_ID = state.getId();
        state = new State(null, "Boyaca", colombia);
        em.persist(state);
        BOYACA_ID = state.getId();
        state = new State(null, "Meta", colombia);
        em.persist(state);
        META_ID = state.getId();
    }

}

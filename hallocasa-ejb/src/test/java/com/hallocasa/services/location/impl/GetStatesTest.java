/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.location.impl;

import com.hallocasa.commons.vo.StateVO;
import com.hallocasa.services.location.local.CountryServices;
import com.hallocasa.services.persistence.impl.AppPersistenceServicesImpl;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.tests.database.BasicDataFiller;
import com.hallocasa.tests.database.DatabaseCreator;
import com.hallocasa.tests.database.DatabaseUtils;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author david
 */
public class GetStatesTest {

    /* static variables */
    private static final Locale EN = new Locale("en");
    private static final Logger LOG = Logger
            .getLogger(GetStatesTest.class.getName());

    /* instance variables */
    private CountryServices countryServices;
    private EntityManagerFactory emf;
    private EntityManager em;
    private AppPersistenceServices persistenceServices;

    @Before
    public void initialize() {
        Locale.setDefault(EN);

        // Open persistence unit
        try {
            emf = DatabaseUtils.loadTestAppPersistenceUnit();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            throw new RuntimeException(e);
        }
        em = emf.createEntityManager();

        // creates database
        DatabaseCreator databaseBuilder = new DatabaseCreator(emf);
        databaseBuilder.fillDatabase();

        // services
        persistenceServices = new AppPersistenceServicesImpl(em);
        countryServices = new CountryServicesImpl(persistenceServices);
    }

    @Test
    public void testReturnListWithValuesOk() {
        List<StateVO> states = countryServices.getStates(BasicDataFiller.COLOMBIA_ID);
        Assert.assertEquals(3, states.size());
        Assert.assertTrue(states.contains(new StateVO(BasicDataFiller.CUNDINAMARCA_ID)));
        Assert.assertTrue(states.contains(new StateVO(BasicDataFiller.BOYACA_ID)));
        Assert.assertTrue(states.contains(new StateVO(BasicDataFiller.META_ID)));
    }

    @Test
    public void testReturnEmptyListOk() {
        List<StateVO> states = countryServices.getStates(BasicDataFiller.GERMANY_ID);
        Assert.assertEquals(0, states.size());
    }

    @Test
    public void testCountryIdValidationFail() {
        try {
            List<StateVO> states = countryServices.getStates(9999L);
            Assert.fail("Exception IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Country with id 9999 doesn't exist", e.getMessage());
        }
    }

}

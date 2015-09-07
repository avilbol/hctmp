package com.hallocasa.services.security.impl;

import com.hallocasa.commons.exceptions.services.ValidationException;
import com.hallocasa.commons.test.AssertUtils;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.services.persistence.impl.AppPersistenceServicesImpl;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.user.impl.SignUpServicesImpl;
import com.hallocasa.services.user.local.SignUpServices;
import com.hallocasa.tests.database.DatabaseCreator;
import com.hallocasa.tests.database.DatabaseUtils;
import com.hallocasa.tests.database.LouisaLaneDataFiller;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActivateUserTest {

    private static final String METHOD_NAME = "activateUser";
    /* static fields */
    private static final Logger LOG = Logger.getLogger(ActivateUserTest.class
            .getName());
    private static final Locale EN = new Locale("en");

    /* instance variables */
    private AppPersistenceServices persistenceServices;
    private EntityManagerFactory emf;
    private SignUpServices signUpServices;
    private Object[] goodParameters;
    private EntityManager em;

    /* constructors */
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
        databaseBuilder.addDatabaseFiller(new LouisaLaneDataFiller());
        databaseBuilder.fillDatabase();

        // services
        persistenceServices = new AppPersistenceServicesImpl(em);
        signUpServices = new SignUpServicesImpl(persistenceServices, null);

        goodParameters = new Object[]{
            LouisaLaneDataFiller.LUISA_LANE_ID,
            LouisaLaneDataFiller.LUISA_LANE_ID};
    }

    /* Methods */
    @Test
    public void testAccoundIdValidationFail() {
        Object[] params = cloneGoodProperties();
        params[0] = 99999L;
        testValidationFailed(params, "User with id 99999 doesn't exist");
    }

    @Test
    public void testUserIsActivated() {
        em.getTransaction().begin();
        failOnException(cloneGoodProperties(), ValidationException.class);
        em.getTransaction().commit();

        User user = em.find(User.class,
                LouisaLaneDataFiller.LUISA_LANE_ID);
        Assert.assertEquals(Boolean.TRUE, user.getConfirmedFlag());
    }

    @Test
    public void testValidationOk() {
        failOnException(cloneGoodProperties(), ValidationException.class);
    }

    /* private utilities */
    private Object[] cloneGoodProperties() {
        Object[] parameters = new Object[1];
        parameters[0] = goodParameters[0];
        return parameters;
    }

    /* private utilities */
    /**
     * Validates an exception in expected failure
     *
     * @param params
     * @param expectedMessage
     */
    private void testValidationFailed(Object[] params, String expectedMessage) {
        ValidationException ve = assertOnException(params,
                ValidationException.class);

        Assert.assertEquals(expectedMessage, ve.getMessage());
    }

    /**
     * Assert on exception
     *
     * @param <T>
     * @param params
     * @param expectedException
     * @return
     */
    private <T extends Exception> T assertOnException(Object[] params,
            Class<T> expectedException) {
        T ve = AssertUtils.assertOnException(signUpServices, METHOD_NAME,
                params, new Class[]{long.class}, expectedException);
        return ve;
    }

    /**
     * Fails on exception
     *
     * @param <T>
     * @param params
     * @param exceptionToFail
     */
    private <T extends Exception> void failOnException(Object[] params,
            Class<T> exceptionToFail) {
        AssertUtils.failOnException(signUpServices,
                METHOD_NAME, params, new Class[]{long.class},
                exceptionToFail);
    }
}

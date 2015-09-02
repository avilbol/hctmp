package com.hallocasa.tests.database;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseUtils {

    private static final Logger LOG = Logger.getLogger(DatabaseUtils.class
            .getName());

    /* static fields */
    public static EntityManagerFactory loadTestAppPersistenceUnit() {
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("App_Test");
            return emf;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            throw new RuntimeException(e);
        }
    }

    /* instance variables */

    /* constructors */

    /* Methods */

    /* Getters & Setters */
}

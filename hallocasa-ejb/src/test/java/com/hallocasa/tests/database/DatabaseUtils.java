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
                    .createEntityManagerFactory("TestApp");
            return emf;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            throw new RuntimeException(e);
        }
    }
    
    /* static fields */
    public static EntityManagerFactory loadMainAppPersistenceUnit() {
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("App");
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

package com.hallocasa.tests.database;

import javax.persistence.EntityManager;

/**
 * Interface for all database fillers, that means object wich insert some test
 * data in database
 *
 * @author David Mantilla
 *
 * @since 1.7
 */
public interface DatabaseFiller {

    /* static fields */

    /* Methods */
    /**
     * Executes the data insertion
     *
     * @param em
     */
    void fillDatabase(EntityManager em);

    /* Getters & Setters */
}

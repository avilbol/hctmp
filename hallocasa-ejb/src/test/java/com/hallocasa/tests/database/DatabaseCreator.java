package com.hallocasa.tests.database;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DatabaseCreator {

    /* static fields */
    public static final Logger LOG = Logger.getLogger(DatabaseCreator.class
            .getName());
    public static Long ENGLISH_ID;
    public static Long COLOMBIA_ID;
    public static Long TIME_ZONE_ID;
    public static final String TIME_ZONE_AMERICA_BOGOTA_NAME = "America/Bogota";
    private final List<DatabaseFiller> databaseFillers;

    /* instance variables */
    private final EntityManagerFactory emf;

    /* constructors */
    public DatabaseCreator(EntityManagerFactory emf) {
        this.emf = emf;
        this.databaseFillers = new ArrayList<>();

        // add a basic data structure, like language, etc
        BasicDataFiller basicDataFiller = new BasicDataFiller();
        this.databaseFillers.add(basicDataFiller);
    }

    public void fillDatabase() {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            // execute other fillers
            for (DatabaseFiller df : databaseFillers) {
                df.fillDatabase(em);
            }

            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            throw e;
        }
    }

    /**
     * Adds a database filler
     *
     * @param databaseFiller
     */
    public void addDatabaseFiller(DatabaseFiller databaseFiller) {
        databaseFillers.add(databaseFiller);
    }

    /**
     * Getter for databaseFillers
     *
     * @return the databaseFillers
     */
    public List<DatabaseFiller> getDatabaseFillers() {
        return databaseFillers;
    }

    /* Getters & Setters */
}

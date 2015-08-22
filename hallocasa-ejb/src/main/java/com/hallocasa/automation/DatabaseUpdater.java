/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.dbmaintain.DbMaintainer;
import org.dbmaintain.MainFactory;

/**
 *
 * @author david
 */
@Singleton(name = "databaseUpdater")
@Startup
public class DatabaseUpdater {

    private static final Logger LOG = Logger.getLogger(DatabaseUpdater.class.getName());

    /**
     * Initializes database process
     */
    @PostConstruct
    public void initialize() {
        try {
            Properties properties = new Properties();
            InputStream is =  DatabaseUpdater.class.getResourceAsStream("/dbmaintain.properties");
            properties.load(is);
            is.close();
            MainFactory mainFactory = new MainFactory(properties);
            DbMaintainer dbMaintainer = mainFactory.createDbMaintainer();
            dbMaintainer.updateDatabase(false);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error executing dbMaintain update process", ex);
        }
    }
}

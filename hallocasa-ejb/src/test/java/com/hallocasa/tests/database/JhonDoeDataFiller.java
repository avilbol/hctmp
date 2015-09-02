/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.tests.database;

import com.hallocasa.commons.codec.CodecUtils;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.UserType;
import javax.persistence.EntityManager;

/**
 *
 * @author david
 */
public class JhonDoeDataFiller implements DatabaseFiller {
    
    public static long JHON_DOE_ID;
    public static String JHON_DOE_PASSWORD = "mypassword";
    public static String JHON_DOE_EMAIL = "jhon@doe.com";
    
    @Override
    public void fillDatabase(EntityManager em) {
        User user = new User();
        user.setEmail(JHON_DOE_EMAIL);
        user.setPassword(CodecUtils.encryptPassword(JHON_DOE_PASSWORD));
        user.setUserType(new UserType(1L));
        em.persist(user);
        JHON_DOE_ID = user.getId();
    }
    
}

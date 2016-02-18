/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.tests.database;

import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.UserType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Louisa Lane is a registered but not confirmed user
 *
 * @author david
 */
public class LouisaLaneDataFiller implements DatabaseFiller {
    
    public static Long LUISA_LANE_ID;
    public static final String LUISA_LANE_EMAIL = "louisalane@theplanet.com";
    
    @Override
    public void fillDatabase(EntityManager em) {
        User user = new User();
        user.setEmail(LUISA_LANE_EMAIL);
        user.setConfirmedFlag(Boolean.TRUE);
        
        List<UserType> userTypes = new ArrayList<>();
        userTypes.add(new UserType(1L));
        user.setUserType(userTypes);
        
        em.persist(user);
        LUISA_LANE_ID = user.getId();
    }
    
}

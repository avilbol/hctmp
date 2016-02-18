/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import com.hallocasa.dataentities.app.Token;
import com.hallocasa.services.base.ServicesBase;
import com.hallocasa.services.interfaces.SecurityServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alexander
 */
@Stateless
public class SecurityServicesImpl extends ServicesBase implements SecurityServices{

    @EJB
    private AppPersistenceServices appPersistenceServices;
   
    
    @Override
    public Boolean saveToken(Token token) {
        appPersistenceServices.mergeEntity(token);
        return true;
    }

    @Override
    public Token getToken(String tokenContent) {
        List<Token> tokenList  = appPersistenceServices.executeNamedQuery(
                Token.QUERY_FIND_BY_CONTENT_AND_ACTIVE, 
                new Object[]{tokenContent}, Token.class);
        if(tokenList != null && tokenList.size() > 0){
            return tokenList.get(0);
        }
        return null;
    }

    @Override
    public Boolean deleteToken(Integer idAssociated) {
        appPersistenceServices.executeNamedQuery(Token.QUERY_DELETE_BY_ID, 
                new Object[]{idAssociated});
        return true;
    }
    
}

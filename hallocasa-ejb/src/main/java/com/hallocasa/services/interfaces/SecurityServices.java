/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import com.hallocasa.dataentities.app.Token;

/**
 *
 * @author Alexander
 */
public interface SecurityServices {
    
    public Boolean saveToken(Token token);
    
    public Boolean deleteToken(Integer idAssociated);
    
    public Token getToken(String tokenContent);
    
}

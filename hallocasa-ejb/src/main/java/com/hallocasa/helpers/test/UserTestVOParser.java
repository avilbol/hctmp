/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.helpers.test;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.UserType;
import com.hallocasa.dataentities.app.test.UserTest;
import com.hallocasa.dataentities.types.LanguageList;
import com.hallocasa.helpers.HallocasaVOParser;
import com.hallocasa.helpers.ParsersContext;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 *
 * @author juan
 */
public class UserTestVOParser extends HallocasaVOParser<UserTest, UserVO> {

    /**
     * Constructor
     */
    public UserTestVOParser() {
        super();
    }

    /* static fields */

    /* instance variables */

    /* constructors */

    /* Methods */
    @Override
    protected void copyEntityPropertyToValueObjectProperty(UserVO vo,
            UserTest entity, String propertyName, Object propertyValue,
            Object[] options) throws IllegalAccessException,
            InvocationTargetException {
        if (propertyName.equals(UserVO.spokenLanguages_)) {
            vo.setSpokenLanguages(new ArrayList<Language>());
            vo.getSpokenLanguages().addAll(entity.getSpokenLanguages());
        } else {
            super.copyEntityPropertyToValueObjectProperty(vo, entity,
                    propertyName, propertyValue, options);
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see com.mobiera.social.commons.vo.helpers.GenericVOParser#
     * copyVOPropertyToEntityProperty
     * (com.mobiera.social.commons.vo.interfaces.ValueObject,
     * com.mobiera.social.commons.vo.interfaces.SocialEntity, java.lang.String,
     * java.lang.Object)
     */
    @Override
    protected void copyVOPropertyToEntityProperty(UserVO vo, UserTest entity,
            String propertyName, Object propertyValue, Object[] options)
            throws IllegalAccessException, InvocationTargetException {
        if (propertyName.equals(User.spokenLanguages_)) {
            entity.setSpokenLanguages(new LanguageList());
            entity.getSpokenLanguages().addAll(vo.getSpokenLanguages());
        }
        else if (propertyName.equals(User.userTypes_)) {
            for (UserTypeVO userTypeVO : vo.getUserTypes()) {
                entity.getUserTypes().add(ParsersContext.USER_TYPE_VO_PARSER
                        .toEntity(userTypeVO, UserType.class));
            }
        }
        else{
            super.copyVOPropertyToEntityProperty(vo, entity, propertyName,
                propertyValue, options);   
        }
    }

    /**
     *
     * @param vo
     * @param entity
     * @param excludeIpList If this is true, the ip list is not copied
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void copyVOToEntity(UserVO vo, UserTest entity,
            boolean excludeIpList) throws IllegalAccessException,
            InvocationTargetException {
        copyVOToEntity(vo, entity, buildOptions(excludeIpList));
    }

    /**
     * @param excludeIpList
     * @return
     */
    private Object[] buildOptions(boolean excludeIpList) {
        return new Object[]{excludeIpList};
    }
}

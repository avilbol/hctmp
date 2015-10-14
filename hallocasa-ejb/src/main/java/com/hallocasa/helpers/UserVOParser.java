package com.hallocasa.helpers;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.types.LanguageList;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Parser of accountVO to account and vice-versa
 *
 * @author David Mantilla
 * @since 1.7
 */
public class UserVOParser extends HallocasaVOParser<User, UserVO> {

    /**
     * Constructor
     */
    public UserVOParser() {
        super();
    }

    /* static fields */

    /* instance variables */

    /* constructors */

    /* Methods */
    @Override
    protected void copyEntityPropertyToValueObjectProperty(UserVO vo,
            User entity, String propertyName, Object propertyValue,
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
    protected void copyVOPropertyToEntityProperty(UserVO vo, User entity,
            String propertyName, Object propertyValue, Object[] options)
            throws IllegalAccessException, InvocationTargetException {
        if (propertyName.equals(User.spokenLanguages_)) {
            entity.setSpokenLanguages(new LanguageList());
            entity.getSpokenLanguages().addAll(vo.getSpokenLanguages());
        }
        super.copyVOPropertyToEntityProperty(vo, entity, propertyName,
                propertyValue, options);
    }

    /**
     *
     * @param vo
     * @param entity
     * @param excludeIpList If this is true, the ip list is not copied
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void copyVOToEntity(UserVO vo, User entity,
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

    /* Getters & Setters */
}

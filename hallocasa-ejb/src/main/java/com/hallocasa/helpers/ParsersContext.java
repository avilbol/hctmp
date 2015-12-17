package com.hallocasa.helpers;

import com.hallocasa.helpers.test.UserTestVOParser;

/**
 * This class register all conversion that BeanUtils will use to copy properties
 * between value object and entities and vice-versa
 *
 * @author David Mantilla
 * @since 1.7
 */
public class ParsersContext {

    public static final UserVOParser USER_VO_PARSER = new UserVOParser();
    public static final UserTestVOParser USER_TEST_VO_PARSER = new UserTestVOParser();
    public static final CountryVOParser COUNTRY_VO_PARSER = new CountryVOParser();
    public static final StateVOParser STATE_VO_PARSER = new StateVOParser();
    public static final UserTypeVOParser USER_TYPE_VO_PARSER = new UserTypeVOParser();
    /* instance variables */

    /* constructors */

    /* Methods */

    /* Getters & Setters */
}

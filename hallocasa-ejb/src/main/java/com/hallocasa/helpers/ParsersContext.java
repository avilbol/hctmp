package com.hallocasa.helpers;

import com.hallocasa.commons.vo.CountryTelephonePrefixVO;
import com.hallocasa.commons.vo.TelephoneVO;
import com.hallocasa.dataentities.app.CountryTelephonePrefix;
import com.hallocasa.dataentities.app.Telephone;
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
	public static final PropertyVOParser PROPERTY_VO_PARSER = new PropertyVOParser();
	public static final PropertyTypeVOParser PROPERTY_TYPE_VO_PARSER = new PropertyTypeVOParser();
	public static final PropertyProposalVOParser PROPERTY_PROPOSAL_VO_PARSER = new PropertyProposalVOParser();
	public static final PropertyLocationVOParser PROPERTY_LOCATION_VO_PARSER = new PropertyLocationVOParser();
	public static final UserTestVOParser USER_TEST_VO_PARSER = new UserTestVOParser();
	public static final CountryVOParser COUNTRY_VO_PARSER = new CountryVOParser();
	public static final StandardVOParser<Telephone, TelephoneVO> TELEPHONE_VO_PARSER = new StandardVOParser<>();
	public static final StandardVOParser<CountryTelephonePrefix, CountryTelephonePrefixVO> COUNTRY_TELEPHONE_PREFIX_VO_PARSER = new StandardVOParser<>();
	public static final StateVOParser STATE_VO_PARSER = new StateVOParser();
	public static final CityVOParser CITY_VO_PARSER = new CityVOParser();
	public static final UserTypeVOParser USER_TYPE_VO_PARSER = new UserTypeVOParser();
	public static final CurrencyVOParser CURRENCY_VO_PARSER = new CurrencyVOParser();
	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}

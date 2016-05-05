package com.hallocasa.helpers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.CountryTelephonePrefixVO;
import com.hallocasa.commons.vo.TelephoneVO;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.Telephone;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.UserType;
import com.hallocasa.dataentities.types.LanguageList;

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
		} else if (propertyName.equals(User.userTypes_)) {
			for (UserType userType : entity.getUserTypes()) {
				vo.getUserTypes().add(
						ParsersContext.USER_TYPE_VO_PARSER.toValueObject(
								userType, UserTypeVO.class));
			}
		} else if (propertyName.equals(User.telephone_)) {
			vo.setTelephone(parseEntityToVO(entity.getTelephone()));
		} else {
			super.copyEntityPropertyToValueObjectProperty(vo, entity,
					propertyName, propertyValue, options);
		}
		vo.setProperties(PropertyVOParser.getInstance().toValueObject(
				entity.getProperties()));
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
		} else if (propertyName.equals(User.userTypes_)) {
			for (UserTypeVO userTypeVO : vo.getUserTypes()) {
				entity.getUserTypes().add(
						ParsersContext.USER_TYPE_VO_PARSER.toEntity(userTypeVO,
								UserType.class));
			}
		} else if (propertyName.equals(User.telephone_)) {
			if (vo.getTelephone() != null) {
				entity.setTelephone(new Telephone(vo.getTelephone()));
				entity.getTelephone().setUser(entity);
			}
		} else {
			super.copyVOPropertyToEntityProperty(vo, entity, propertyName,
					propertyValue, options);
		}
	}

	/**
	 * 
	 * @param vo
	 * @param entity
	 * @param excludeIpList
	 *            If this is true, the ip list is not copied
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void copyVOToEntity(UserVO vo, User entity, boolean excludeIpList)
			throws IllegalAccessException, InvocationTargetException {
		copyVOToEntity(vo, entity, buildOptions(excludeIpList));
		entity.setProperties(PropertyVOParser.getInstance().toEntity(
				vo.getProperties()));
	}

	/**
	 * @param excludeIpList
	 * @return
	 */
	private Object[] buildOptions(boolean excludeIpList) {
		return new Object[] { excludeIpList };
	}

	private TelephoneVO parseEntityToVO(Telephone telephone) {
		if (telephone == null || telephone.getNumber() == null) {
			return null;
		}
		TelephoneVO tvo = new TelephoneVO();
		tvo.setNumber(telephone.getNumber());
		CountryTelephonePrefixVO ctp = new CountryTelephonePrefixVO();
		ctp.setId(telephone.getCountryTelephonePrefix().getId().longValue());
		ctp.setName(telephone.getCountryTelephonePrefix().getName());
		ctp.setPrefix(telephone.getCountryTelephonePrefix().getPrefix());
		tvo.setCountryTelephonePrefix(ctp);
		return tvo;
	}

	/* Getters & Setters */
}

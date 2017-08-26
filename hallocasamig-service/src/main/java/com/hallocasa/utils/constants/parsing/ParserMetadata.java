package com.hallocasa.utils.constants.parsing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hallocasa.entities.EntityCity;
import com.hallocasa.entities.EntityComposedExample;
import com.hallocasa.entities.EntityCountry;
import com.hallocasa.entities.EntityCountryTelephonePrefix;
import com.hallocasa.entities.EntityCurrency;
import com.hallocasa.entities.EntityCurrencyExchangeData;
import com.hallocasa.entities.EntityExample;
import com.hallocasa.entities.EntityFilterListingStep;
import com.hallocasa.entities.EntityFilterShowingStep;
import com.hallocasa.entities.EntityHcFilter;
import com.hallocasa.entities.EntityHcFilterCondition;
import com.hallocasa.entities.EntityHcFilterNature;
import com.hallocasa.entities.EntityHcFilterType;
import com.hallocasa.entities.EntityLanguage;
import com.hallocasa.entities.EntityNeighborhood;
import com.hallocasa.entities.EntityPasswordRecoveryToken;
import com.hallocasa.entities.EntityPreferredSetting;
import com.hallocasa.entities.EntityState;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.EntityUserDescription;
import com.hallocasa.entities.EntityUserLanguage;
import com.hallocasa.entities.EntityUserType;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.entities.properties.EntityDropdownOption;
import com.hallocasa.entities.properties.EntityDropdownOptionGroup;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.entities.properties.EntityPropertyField;
import com.hallocasa.entities.properties.EntityPropertyFieldType;
import com.hallocasa.entities.properties.EntityPropertyFieldValueType;
import com.hallocasa.entities.properties.EntityPropertyLocation;
import com.hallocasa.entities.properties.EntityPropertyProposal;
import com.hallocasa.entities.properties.EntityPropertyType;
import com.hallocasa.entities.properties.EntityPropertyTypeGroup;
import com.hallocasa.entities.security.EntityAuthorizationCode;
import com.hallocasa.entities.security.EntitySecurityToken;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.City;
import com.hallocasa.vo.ComposedExample;
import com.hallocasa.vo.Country;
import com.hallocasa.vo.CountryTelephonePrefix;
import com.hallocasa.vo.Currency;
import com.hallocasa.vo.CurrencyExchangeDataSummary;
import com.hallocasa.vo.Example;
import com.hallocasa.vo.Language;
import com.hallocasa.vo.Neighborhood;
import com.hallocasa.vo.PasswordRecoveryToken;
import com.hallocasa.vo.PreferredSetting;
import com.hallocasa.vo.State;
import com.hallocasa.vo.User;
import com.hallocasa.vo.UserDescription;
import com.hallocasa.vo.UserLanguage;
import com.hallocasa.vo.UserType;
import com.hallocasa.vo.hcfilter.FilterListingStep;
import com.hallocasa.vo.hcfilter.FilterShowingStep;
import com.hallocasa.vo.hcfilter.HcFilter;
import com.hallocasa.vo.hcfilter.HcFilterCondition;
import com.hallocasa.vo.hcfilter.HcFilterNature;
import com.hallocasa.vo.hcfilter.HcFilterType;
import com.hallocasa.vo.hcfilter.properties.Property;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldType;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueType;
import com.hallocasa.vo.hcfilter.properties.PropertyLocation;
import com.hallocasa.vo.hcfilter.properties.PropertyProposal;
import com.hallocasa.vo.hcfilter.properties.PropertyType;
import com.hallocasa.vo.hcfilter.properties.PropertyTypeGroup;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.options.DropdownOption;
import com.hallocasa.vo.options.DropdownOptionGroup;
import com.hallocasa.vo.properties.PropertyField;
import com.hallocasa.vo.security.AuthorizationCode;
import com.hallocasa.vo.security.SecurityToken;

public class ParserMetadata {

	public static Map<Class<?>, Class<?>> clazzEquivalenceMap = new HashMap<>();

	public static Map<Class<?>, Parser<?, ?>> parserMap = new HashMap<>();

	/**
	 * Place here equivalences between entities and value objects (required)
	 */
	static {
		clazzEquivalenceMap.put(Example.class, EntityExample.class);
		clazzEquivalenceMap.put(AuthorizationCode.class, EntityAuthorizationCode.class);
		clazzEquivalenceMap.put(SecurityToken.class, EntitySecurityToken.class);
		clazzEquivalenceMap.put(ComposedExample.class, EntityComposedExample.class);
		clazzEquivalenceMap.put(HcFilter.class, EntityHcFilter.class);
		clazzEquivalenceMap.put(HcFilterType.class, EntityHcFilterType.class);
		clazzEquivalenceMap.put(HcFilterCondition.class, EntityHcFilterCondition.class);
		clazzEquivalenceMap.put(HcFilterNature.class, EntityHcFilterNature.class);
		clazzEquivalenceMap.put(FilterListingStep.class, EntityFilterListingStep.class);
		clazzEquivalenceMap.put(FilterShowingStep.class, EntityFilterShowingStep.class);
		clazzEquivalenceMap.put(Property.class, EntityProperty.class);
		clazzEquivalenceMap.put(PropertyType.class, EntityPropertyType.class);
		clazzEquivalenceMap.put(PropertyTypeGroup.class, EntityPropertyTypeGroup.class);
		clazzEquivalenceMap.put(PropertyLocation.class, EntityPropertyLocation.class);
		clazzEquivalenceMap.put(PropertyProposal.class, EntityPropertyProposal.class);
		clazzEquivalenceMap.put(PropertyField.class, EntityPropertyField.class);
		clazzEquivalenceMap.put(PropertyFieldType.class, EntityPropertyFieldType.class);
		clazzEquivalenceMap.put(DropdownOption.class, EntityDropdownOption.class);
		clazzEquivalenceMap.put(PropertyFieldValueType.class, EntityPropertyFieldValueType.class);
		clazzEquivalenceMap.put(PropertyField.class, EntityPropertyField.class);
		clazzEquivalenceMap.put(PreferredSetting.class, EntityPreferredSetting.class);
		clazzEquivalenceMap.put(User.class, EntityUser.class);
		clazzEquivalenceMap.put(Country.class, EntityCountry.class);
		clazzEquivalenceMap.put(State.class, EntityState.class);
		clazzEquivalenceMap.put(City.class, EntityCity.class);
		clazzEquivalenceMap.put(Neighborhood.class, EntityNeighborhood.class);
		clazzEquivalenceMap.put(Currency.class, EntityCurrency.class);
		clazzEquivalenceMap.put(Language.class, EntityLanguage.class);
		clazzEquivalenceMap.put(DropdownOption.class, EntityDropdownOption.class);
		clazzEquivalenceMap.put(DropdownOptionGroup.class, EntityDropdownOptionGroup.class);
		clazzEquivalenceMap.put(CountryTelephonePrefix.class, EntityCountryTelephonePrefix.class);
		clazzEquivalenceMap.put(CurrencyExchangeDataSummary.class, EntityCurrencyExchangeData.class);
		clazzEquivalenceMap.put(UserType.class, EntityUserType.class);
		clazzEquivalenceMap.put(UserLanguage.class, EntityUserLanguage.class);
		clazzEquivalenceMap.put(UserDescription.class, EntityUserDescription.class);
		clazzEquivalenceMap.put(PasswordRecoveryToken.class, EntityPasswordRecoveryToken.class);
	}

	/**
	 * Place here customized parsers (according to needed)
	 */
	static {
		parserMap.put(ComposedExample.class, new ComposedExampleParser());
		parserMap.put(HcFilter.class, new HcFilterParser());
		parserMap.put(HcFilterCondition.class, new HcFilterConditionParser());
		parserMap.put(HcFilterType.class, new HcFilterTypeParser());
		parserMap.put(Property.class, new PropertyParser());
		parserMap.put(User.class, new UserParser());
	}

	public static Set<Class<?>> getWrapperTypes() {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		ret.add(String.class);
		return ret;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <U, V> Parser<U, V> getParser(Class clazz) {
		Parser<U, V> parser = (Parser<U, V>) parserMap.get(clazz);
		return parser != null ? parser : (Parser<U, V>) new DefaultParser();
	}

	public static void main(String[] args) {
		Parser<ValueObject, HallocasaEntity> parser = ParserMetadata.getParser(Example.class);
		Example example = new Example();
		example.setDescription("desc");
		example.setIdentifier(2);
		EntityExample entityExample = (EntityExample) parser.toEntity((ValueObject) example, EntityExample.class);
		System.out.println(entityExample.getIdentifier());
	}
}

package com.hallocasa.helpers;

import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.Coordinate;
import com.hallocasa.dataentities.app.City;
import com.hallocasa.dataentities.app.Country;

import java.lang.reflect.InvocationTargetException;

/**
 * Parser of cityVO to city and vice-versa
 *
 * @author Alexander Villamil
 * @since 1.7
 */
public class CityVOParser extends HallocasaVOParser<City, CityVO> {

	/**
	 * Constructor
	 */
	public CityVOParser() {
		super();
	}

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */
	@Override
	protected void copyEntityPropertyToValueObjectProperty(CityVO vo, City entity, String propertyName,
			Object propertyValue, Object[] options) throws IllegalAccessException, InvocationTargetException {
		if (propertyName.equals(City.defaultLatCoordinate_)) {
			vo.setLatCoordinate(new Coordinate(entity.getDefaultLatCoordinate()));
			return;
		}
		if (propertyName.equals(City.defaultLngCoordinate_)) {
			vo.setLngCoordinate(new Coordinate(entity.getDefaultLngCoordinate()));
			return;
		}
		super.copyEntityPropertyToValueObjectProperty(vo, entity, propertyName, propertyValue, options);
	}

	@Override
	protected void copyVOPropertyToEntityProperty(CityVO vo, City entity, String propertyName, Object propertyValue,
			Object[] options) throws IllegalAccessException, InvocationTargetException {
		if (propertyName.equals(City.defaultLatCoordinate_)) {
			entity.setDefaultLatCoordinate(vo.getLatCoordinate().getDecRepresentation());
			return;
		}
		if (propertyName.equals(City.defaultLngCoordinate_)) {
			entity.setDefaultLngCoordinate(vo.getLngCoordinate().getDecRepresentation());
			return;
		}
		super.copyVOPropertyToEntityProperty(vo, entity, propertyName, propertyValue, options);
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
	public void copyVOToEntity(CityVO vo, City entity, boolean excludeIpList)
			throws IllegalAccessException, InvocationTargetException {
		copyVOToEntity(vo, entity, buildOptions(excludeIpList));
	}

	/**
	 * @param excludeIpList
	 * @return
	 */
	private Object[] buildOptions(boolean excludeIpList) {
		return new Object[] { excludeIpList };
	}

	/* Getters & Setters */
}
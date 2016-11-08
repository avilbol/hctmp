package com.hallocasa.utils.constants.parsing.i;

/**
 * Contract to parse between vo's and entities
 * @author avillamil
 *
 * @param <U>
 * @param <V>
 */
public interface Parser<ValueObject, HallocasaEntity> {

	/**
	 * Takes a value object and converts into an equivalent entity
	 * @param valueObject
	 * 		The vo to transform
	 * @param clazz
	 * 		Instance class to build
	 * @return
	 * 		The entity equivalent
	 */
	HallocasaEntity toEntity(ValueObject valueObject, Class<?> clazz);
	
	/**
	 * Takes a entity and converts into a value object
	 * @param entity
	 * 		The entity to transform
	 * @param clazz
	 * 		Instance class to build
	 * @return
	 * 		The value object equivalent
	 */
	ValueObject toValueObject(HallocasaEntity entity, Class<?> clazz);
	
}

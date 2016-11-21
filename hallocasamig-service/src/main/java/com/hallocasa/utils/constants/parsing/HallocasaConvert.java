package com.hallocasa.utils.constants.parsing;

import static com.hallocasa.utils.constants.parsing.ParserMetadata.clazzEquivalenceMap;
import static com.hallocasa.utils.constants.parsing.ParserMetadata.parserMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.utils.constants.parsing.i.Parser;
import com.hallocasa.vo.i.ValueObject;

public class HallocasaConvert {
	
	@SuppressWarnings("unchecked")
	public static HallocasaEntity toEntity(ValueObject vo){
		Class<?> entityEquivalence = (Class<?>) clazzEquivalenceMap.get(vo.getClass());
		if(entityEquivalence == null){
			throw new FatalException("Imposible determinar la entidad asociada al Value Object " 
					+ vo.getClass().getName());
		}
		Parser<ValueObject, HallocasaEntity> parser = (Parser<ValueObject, HallocasaEntity>) parserMap.get(vo.getClass());
		if(parser == null){
			parser = new DefaultParser();
		}
		return parser.toEntity(vo, entityEquivalence);
	}
	
	public static Optional<?> toValueObject(Optional<?> entity){
		if(!entity.isPresent()){
			return Optional.empty();
		}
		return Optional.of(toValueObject((HallocasaEntity)entity.get()));
	}
	
	@SuppressWarnings("unchecked")
	public static ValueObject toValueObject(HallocasaEntity entity){
		if (entity == null){
			return null;
		}
		Class<?> voEquivalence = null;
		for(Class<?> valueObject : clazzEquivalenceMap.keySet()){
			Class<?> value =  valueObject;
			if(clazzEquivalenceMap.get(value).equals(entity.getClass())){
				voEquivalence = value;
			}
		}
		if(voEquivalence == null){
			throw new FatalException("Imposible determinar el value object asociado a la entidad " 
					+ entity.getClass().getName());
		}
		Parser<ValueObject, HallocasaEntity> parser = (Parser<ValueObject, HallocasaEntity>) parserMap.get(voEquivalence);
		if(parser == null){
			parser = new DefaultParser();
		}
		return parser.toValueObject(entity, voEquivalence);
	}
}

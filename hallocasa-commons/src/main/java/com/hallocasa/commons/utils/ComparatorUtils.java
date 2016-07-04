package com.hallocasa.commons.utils;

import java.math.BigDecimal;
import java.util.List;

public class ComparatorUtils {

	public static boolean inRange(Object attributeToCompare, BigDecimal from,
			BigDecimal to){
		if(attributeToCompare instanceof List){
			throw new RuntimeException("Funcion no soportada por el comparador de rangos");
		}
		BigDecimal sAttr = new BigDecimal((String) attributeToCompare);
		return from.doubleValue() <= sAttr.doubleValue() 
				&& to.doubleValue() >= sAttr.doubleValue();
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> boolean select(Object attributeToCompare, Object valuesReference, boolean inclusive){
		if(valuesReference instanceof List<?>)
			return select(attributeToCompare, valuesReference, inclusive);
		if(attributeToCompare instanceof List<?>)
			return valueIn((T) valuesReference, (List<T>) attributeToCompare);
		return ((T) valuesReference).equals((T) attributeToCompare);
	}
	

	public static<T> boolean select(Object attributeToCompare, List<T> valuesReference, boolean inclusive){
		if(inclusive)
			return selectAnd(attributeToCompare, valuesReference);
		return selectOr(attributeToCompare, valuesReference);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> boolean selectOr(Object attributeToCompare, List<T> valuesReference){
		if(attributeToCompare instanceof List<?>){
			for(T attr : (List<T>) attributeToCompare){
				if(valueIn(attr, valuesReference))
					return true;
			}
			return false;
		}
		return valueIn((T) attributeToCompare, valuesReference);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> boolean selectAnd(Object attributeToCompare, List<T> valuesReference){
		if(attributeToCompare instanceof List<?>){
			List<T> attrList = (List<T>) attributeToCompare;
			if(attrList.isEmpty()){
				return false;
			}
			for(T attr : attrList){
				if(!valueIn(attr, valuesReference))
					return false;
			}
			return true;
		}
		return valueIn((T) attributeToCompare, valuesReference);
	}
	
	public static List<?> coalesce(List<?> listInitial, List<?> listFinal){
		return listInitial == null ? listFinal : listInitial;
	}
	
	public static Object coalesce(Object objInitial, Object objFinal){
		return objInitial == null ? objFinal : objInitial;
	}
	
	private static <T> boolean valueIn(T attr, List<T> valuesReference){
		for(T ref : valuesReference)
			if(attr.equals(ref))
				return true;
		return false;
	}
}

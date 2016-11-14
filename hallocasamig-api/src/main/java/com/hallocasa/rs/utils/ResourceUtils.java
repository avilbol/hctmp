package com.hallocasa.rs.utils;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.UriInfo;

/**
 * Utilities for resource processing
 */
public class ResourceUtils {

	public static List<Integer> intList(UriInfo uriInfo, String value){
		try{
			List<String> strList = uriInfo.getQueryParameters().get("filter_nature_id");
			List<Integer> intList = new LinkedList<Integer>();
			if(strList != null){
				for(String strItem : strList){
					intList.add(Integer.parseInt(strItem));
				}
			}
			return intList;
		} catch(NumberFormatException e){
			throw new BadRequestException("Params in query string cannot admit non integer values", e);
		}
	}
	
}

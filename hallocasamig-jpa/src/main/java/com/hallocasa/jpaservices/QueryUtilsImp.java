package com.hallocasa.jpaservices;

import java.util.List;

import javax.ejb.Stateless;

import com.hallocasa.jpaservices.i.QueryUtils;

@Stateless
public class QueryUtilsImp implements QueryUtils {

	@Override
	public String loadOrderBySnippetQuery(List<String> orderBy, boolean asc) {
		StringBuilder builder = new StringBuilder("");
		for(String orderByAttr : orderBy){
			builder.append(builder.toString().isEmpty() ? "" : ",").append(orderByAttr);
		}
		StringBuilder resultQuery = new StringBuilder("");
		if(!builder.toString().isEmpty()){
			resultQuery.append(" ORDER BY " + builder.toString().toString());
			resultQuery.append(asc ? " ASC" : " DESC");
		}
		return resultQuery.toString();
	}

}

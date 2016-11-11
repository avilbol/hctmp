package com.hallocasa.dao.hcfilter;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.hcfilter.IDAOHcFilter;
import com.hallocasa.entities.EntityHcFilter;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.hcfilter.HcFilter;

/**
 * DAO for class {@link HcFilter}
 */
@Stateless
public class DAOHcFilter implements IDAOHcFilter {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityHcFilter> find(List<Integer> filterNatureIdList) {
		String query = EntityHcFilter.QUERY_FIND_BY_NATURE;
		List<Object> paramList = new LinkedList<Object>();
		for(Integer filterNatureId : filterNatureIdList){
			paramList.add(filterNatureId);
		}
		return appPersistenceServices.executeNamedQuery(query,
				new Object[]{filterNatureIdList}, EntityHcFilter.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityHcFilter> find() {
		return appPersistenceServices.executeNamedQuery(EntityHcFilter.QUERY_FIND_ALL,
				new Object[]{}, EntityHcFilter.class);
	}
	
}

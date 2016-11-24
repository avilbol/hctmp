package com.hallocasa.dao.properties;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyProposal;
import com.hallocasa.entities.properties.EntityPropertyProposal;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

/**
 * DAO for class {@link EntityPropertyProposal}
 */
@Stateless
public class DAOPropertyProposal implements IDAOPropertyProposal {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityPropertyProposal> find() {
		String query = EntityPropertyProposal.QUERY_FIND_ALL;
		return appPersistenceServices.executeNamedQuery(query, new Object[]{}, EntityPropertyProposal.class);
	}
}

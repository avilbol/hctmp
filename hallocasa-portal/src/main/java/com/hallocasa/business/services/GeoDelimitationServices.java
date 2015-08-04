/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.services;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hallocasa.business.dataentities.GeoDelimitation;
import com.hallocasa.business.services.interfaces.GeoDelimitationServicesInterface;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
@Stateless
public class GeoDelimitationServices implements
		GeoDelimitationServicesInterface {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger
			.getLogger(GeoDelimitationServices.class.getName());
	private static final long serialVersionUID = 1L;

	/* dependencies */
	@PersistenceContext(unitName = "RealStateDatabasePU")
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hallocasa.business.services.interfaces.GeoDelimitationServicesInterface
	 * #findByLevel(int)
	 */
	@Override
	public List<GeoDelimitation> findByType(int typeId) {
		// TODO Auto-generated method stub
		final TypedQuery<GeoDelimitation> query = em.createNamedQuery(
				"GeoDelimitation.findByGeoDelimitationTypeId",
				GeoDelimitation.class);
		query.setParameter(1, typeId);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hallocasa.business.services.interfaces.GeoDelimitationServicesInterface
	 * #findByParentId(long)
	 */
	@Override
	public List<GeoDelimitation> findByParentId(long parentId) {
		final TypedQuery<GeoDelimitation> query = em.createNamedQuery(
				"GeoDelimitation.findByGeoDelimitationParentId",
				GeoDelimitation.class);
		query.setParameter(1, parentId);
		return query.getResultList();
	}
}

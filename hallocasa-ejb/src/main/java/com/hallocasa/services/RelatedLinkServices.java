/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hallocasa.dataentities.wcm.Link;
import com.hallocasa.dataentities.wcm.LinkType;
import com.hallocasa.services.interfaces.RelatedLinkServicesInterface;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
@Stateless
public class RelatedLinkServices implements RelatedLinkServicesInterface {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "RealStateDatabasePU")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Link> searchRelatedLinks(int idLinkType) {
		final StringBuilder jpql = new StringBuilder();
		Query query;
		List<Link> listaLinks = null;

		jpql.append("SELECT l FROM ");
		jpql.append(Link.class.getSimpleName());
		jpql.append(" l ");
		jpql.append(" WHERE l.linkType.linkTypeId = :idLinkType ");
		jpql.append("ORDER BY l.linkId");

		query = em.createQuery(jpql.toString());
		query.setParameter("idLinkType", idLinkType);
		listaLinks = query.getResultList();
		return listaLinks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkType> searchLinksType() {
		final StringBuilder jpql = new StringBuilder();
		Query query;
		List<LinkType> listaLinksType = null;

		jpql.append("SELECT lt FROM ");
		jpql.append(LinkType.class.getSimpleName());
		jpql.append(" lt ");
		jpql.append("ORDER BY lt.linkTypeId");

		query = em.createQuery(jpql.toString());
		listaLinksType = query.getResultList();
		return listaLinksType;

	}
}

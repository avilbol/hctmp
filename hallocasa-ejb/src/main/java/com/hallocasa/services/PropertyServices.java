/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hallocasa.dataentities.Property;
import com.hallocasa.dataentities.PropertyTopography;
import com.hallocasa.services.filter.FilterCondition;
import com.hallocasa.services.filter.FilterGroup;
import com.hallocasa.services.interfaces.PropertyServicesInterface;
import com.hallocasa.services.pagination.PageIndexInfo;
import com.hallocasa.services.pagination.PageList;

/**
 *
 * @author David Mantilla
 */
@Stateless
public class PropertyServices implements PropertyServicesInterface {

    private static final Logger LOG = Logger.getLogger(PropertyServices.class
            .getName());
    private static final long serialVersionUID = 1L;

    static {
        LOG.setLevel(Level.FINEST);
    }

    @PersistenceContext(unitName = "RealStateDatabasePU")
    EntityManager em;

    /**
     * Get a list from the database with the properties filtered by a filter list
     *
     * @param filters Filter list
     * @param pageIndexInfo Page info
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PageList<Property> getProperties(List<FilterGroup<?>> filters,
            PageIndexInfo pageIndexInfo)  {

        // Build query base
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        CriteriaQuery queryCriteria = cb.createQuery();
        queryCriteria.distinct(true);
        Map<String, From> fromMap = new HashMap<>();

        Root<Property> root = queryCriteria.from(Property.class);
        From from = root;
        fromMap.put(FilterCondition.ENTITY_NAME, from); // main entity
        queryCriteria.select(from); // select only the main entity
        
        // Join with property collections
        from = fromMap.get(FilterCondition.ENTITY_NAME).join("propertyAccessList",JoinType.LEFT);
        fromMap.put("propertyAccess", from);
        
        // join with environment collections
        from = fromMap.get(FilterCondition.ENTITY_NAME).join("propertyEnvirontmen",JoinType.LEFT);
        fromMap.put("propertyEnvirontmen", from);
        from = fromMap.get("propertyEnvirontmen").join("sportPossibilityList",JoinType.LEFT);
        fromMap.put("sportPossibility", from);

        // dummy expression for starting with
        Path<Long> propertyId = fromMap.get(FilterCondition.ENTITY_NAME).get("propertyId");
        Predicate predicate = cb.greaterThan(propertyId, 0L);

        // adds all filter expression with and
        for (FilterGroup<?> filter : filters) {
            if (!filter.getFilterConditions().isEmpty()) {
                predicate = cb.and(predicate, filter.getAsPredicate(cb, fromMap));
            }
        }
        queryCriteria.where(predicate);

        // create queries
        Query listQuery = em.createQuery(queryCriteria);

        listQuery.setFirstResult((int) pageIndexInfo.getStarItemIndex());
        listQuery.setMaxResults((int) pageIndexInfo.getEndItemIndex()
                - (int) pageIndexInfo.getStarItemIndex());
        List< Property> propertys = listQuery.getResultList();

        // count
        queryCriteria.select(cb.countDistinct(root));
        Query countQuery = em.createQuery(queryCriteria);
        Long count = (Long) countQuery.getSingleResult();

        // build the result object
        PageList<Property> pageList = new PageList<>(propertys, pageIndexInfo,
                count);
        return pageList;
    }

    @Override
    public Property findById(int id) {
        final TypedQuery<Property> query = em.createNamedQuery(
                "Property.findByPropertyId", Property.class);
        query.setParameter("propertyId", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOG.info("NoResultException in the method findById of the class PropertyServices");
            return null;
        }
    }

    @Override
    public List<PropertyTopography> findByPropertyId(int propertyId) {
        final TypedQuery<PropertyTopography> query = em
                .createNamedQuery("PropertyTopography.findByPropertyId",
                        PropertyTopography.class);
        query.setParameter("propertyId", propertyId);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            LOG.info(
                    "NoResultException in the method findByPropertyId of the class PropertyServices");
            return new ArrayList<>();
        }
    }

    @Override
    public List<Property> findAll() {
        final TypedQuery<Property> query = em.createNamedQuery(
                "Property.findAll", Property.class);
        return query.getResultList();
    }

}

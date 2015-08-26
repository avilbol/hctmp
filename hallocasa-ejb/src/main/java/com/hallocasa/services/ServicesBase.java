package com.hallocasa.services;

import com.hallocasa.services.persistence.local.PersistenceServices;
import com.hallocasa.commons.exceptions.services.ValidationException;
import com.hallocasa.commons.validation.BeanValidator;
import com.hallocasa.commons.vo.QueryResult;
import com.hallocasa.commons.vo.criteria.QueryCriteria;
import com.hallocasa.commons.vo.helpers.GenericVOParser;
import com.hallocasa.commons.vo.interfaces.SocialEntity;
import com.hallocasa.commons.vo.interfaces.ValueObject;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.validation.ConstraintViolation;

/**
 * This is the superclass for all EJB services
 *
 * @author David Mantilla
 * @since 1.7
 */
public class ServicesBase {

    /* static fields */
    private static final Logger LOG = Logger.getLogger(ServicesBase.class.getName());

    /* instance variables */

    /* constructors */

    /* Methods */
    /**
     * Validates a bean based on bean validation annotations. If some validation
     * fails then a ValidationException is thrown
     *
     * @param <T>
     * @param bean
     * @param beanClass
     * @param locale
     * @throws ValidationException when the validation fails
     */
    protected <T> void validateBean(T bean, Class<T> beanClass) {
        Set<ConstraintViolation<T>> violations = BeanValidator.validateBean(
                bean, Locale.getDefault());
        for (ConstraintViolation<T> violation : violations) {
            throw new ValidationException(violation.getPropertyPath() + "-"
                    + violation.getMessage());
        }
    }

    /**
     * Validates a bean property based on bean property validation annotations.
     * If some validation fails then a ValidationException is thrown
     *
     * @param <T>
     * @param bean
     * @param propertyName
     * @param propertyLabel If an error occurs, the label instead property-path
     * will be put into the message
     * @param beanClass
     * @param locale
     * @throws ValidationException when the validation fails
     */
    protected <T> void validateProperty(T bean, String propertyName,
            String propertyLabel, Class<T> beanClass) {
        Set<ConstraintViolation<T>> violations = BeanValidator
                .validateProperty(bean, propertyName, Locale.getDefault());
        for (ConstraintViolation<T> violation : violations) {
            throw new ValidationException((propertyLabel == null ? violation
                    .getPropertyPath() : propertyLabel)
                    + "-" + violation.getMessage());
        }
    }

    /**
     * Validates a bean property based on bean property validation annotations.
     * If some validation fails then a ValidationException is thrown
     *
     * @param <T>
     * @param bean
     * @param propertyName
     * @param beanClass
     * @param locale
     * @throws ValidationException when the validation fails
     */
    protected <T> void validateProperty(T bean, String propertyName,
            Class<T> beanClass) {
        validateProperty(bean, propertyName, null, beanClass);
    }

    /**
     * @param queryCriteria
     * @param entityClass
     * @param voClass
     * @param voParser
     * @param persistenceServices
     * @return
     */
    /*protected <T extends SocialEntity, U extends ValueObject> QueryResult<U> findByCriteria(
            QueryCriteria queryCriteria, Class<T> entityClass, Class<U> voClass,
            GenericVOParser<T, U> voParser,
            PersistenceServicesLocal persistenceServices) {

        QueryCriteriaBuilder<T> builder = new QueryCriteriaBuilder<>(
                queryCriteria, entityClass);

        CriteriaQuery<T> dataCriteria = builder
                .buildQueryCriteria(persistenceServices.getEntityManager());

        // builds count query
        CriteriaQuery<Long> countCriteria = builder
                .buildCountQueryCriteria(persistenceServices.getEntityManager());

        return executeCriteria(dataCriteria, countCriteria, queryCriteria,
                entityClass, voClass, voParser, persistenceServices);

    }*/

    /**
     * @param queryCriteria
     * @param entityClass
     * @param voClass
     * @param joinFetchField
     * @param distinct
     * @param voParser
     * @param persistenceServices
     * @return
     */
    /*protected <T extends SocialEntity, U extends ValueObject> QueryResult<U> findByCriteria(
            QueryCriteria queryCriteria, Class<T> entityClass, Class<U> voClass, String joinFetchField,
            Boolean distinct, JoinType joinType, GenericVOParser<T, U> voParser,
            PersistenceServicesLocal persistenceServices) {

        QueryCriteriaBuilder<T> builder = new QueryCriteriaBuilder<>(
                queryCriteria, entityClass, joinFetchField, distinct, joinType);

        CriteriaQuery<T> dataCriteria = builder
                .buildJoinFetchQueryCriteria(persistenceServices.getEntityManager());

        // builds count query
        CriteriaQuery<Long> countCriteria = builder
                .buildCountQueryCriteria(persistenceServices.getEntityManager());

        return executeCriteria(dataCriteria, countCriteria, queryCriteria,
                entityClass, voClass, voParser, persistenceServices);

    }*/

    /**
     * Execute a query given by the criteria bean and convert result into value
     * objects and encapsulate them into a query result object with pagination
     * information
     *
     * @param <T> Class of the entity
     * @param <U> Class of the value object
     * @param queryCriteria
     * @param entityClass
     * @param voClass
     * @param voParser
     * @param persistenceServices
     * @param builder
     * @return The built query result object
     */
    private <T extends SocialEntity, U extends ValueObject> QueryResult<U> executeCriteria(
            CriteriaQuery<T> dataCriteria, CriteriaQuery<Long> countCriteria,
            QueryCriteria queryCriteria, Class<T> entityClass, Class<U> voClass,
            GenericVOParser<T, U> voParser,
            PersistenceServices persistenceServices) {

        // executes data query
        List<T> dataResult = persistenceServices.executeCriteriaQuery(
                dataCriteria, queryCriteria.getStartIndex(), queryCriteria
                .getEndIndex());

        // executes count query
        List<Long> countResult = persistenceServices.executeCriteriaQuery(
                countCriteria, 0, 1);

        // create result object
        List<U> result = voParser.toValueObjectList(dataResult, voClass);

        int startIndex = queryCriteria.getStartIndex() == null ? 0
                : queryCriteria.getStartIndex();
        int endIndex = queryCriteria.getEndIndex() == null ? countResult.get(0)
                .intValue() : queryCriteria.getEndIndex();

        // builds the resuling object
        QueryResult<U> q = new QueryResult<U>(startIndex, endIndex, countResult
                .get(0).intValue(), result);
        return q;
    }

    /**
     * Finds the entity and if it does not exist, a ValidationException is
     * thrown
     *
     * @param entityClass
     * @param id
     * @param <T>
     * @param persistenceServices
     *
     * @return Found entity
     */
    protected <T> T findAndValidateEntity(Class<T> entityClass, Object id,
            PersistenceServices persistenceServices) {
        if (id == null) {
            throw new ValidationException(entityClass.getSimpleName()
                    + " with id null doesn't exist");
        }

        T entity = persistenceServices.findEntity(entityClass, id);
        if (entity == null) {
            throw new ValidationException(entityClass.getSimpleName()
                    + " with id " + id.toString() + " doesn't exist");
        }
        return entity;
    }
    /* Getters & Setters */
}

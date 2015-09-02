/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.exceptions.services.InvalidPasswordLoginException;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.RollbackException;
import javax.transaction.UserTransaction;

import org.eclipse.persistence.exceptions.DatabaseException;

import com.hallocasa.dataentities.wcm.TemporalPublisherUser;
import com.hallocasa.services.base.ServicesBase;
import com.hallocasa.services.constants.ServiceErrorMessage;
import com.hallocasa.services.messaging.local.MailChimpServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.commons.vo.AppAccessInfoVO;
import com.hallocasa.commons.vo.AuthInfoVO;
import com.hallocasa.commons.vo.CredentialVO;
import com.hallocasa.vo.MailChimpMergeVars.TypeEnum;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Mantilla
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServicesImpl extends ServicesBase implements UserServices {

    /* constances */
    private Logger LOG = Logger.getLogger( UserServicesImpl.class.getName() );
    
    /* Dependences */
    @PersistenceContext(unitName = "RealStateDatabasePU")
    private EntityManager em;
    @Resource
    private SessionContext sessionContext;
    @EJB
    private MailChimpServices mailChimpServices;

    /* Methods */
    
    
    
    /**
     *
     * @throws ServiceException
     */
    @Override
    public void savePropertyPublisher(TemporalPublisherUser publisherUser)
            throws ServiceException {
        // TODO: change this transaction management. Let container manages
        // transaction

        // gets the user transaction
        UserTransaction txn = sessionContext.getUserTransaction();

        // begins transaction
        beginTransaction(txn);

        try {
            em.persist(publisherUser);
            mailChimpServices.subscribeNewUser(publisherUser.getEmail(),
                    publisherUser.getFullName(), "",
                    publisherUser.getLanguage(), TypeEnum.PUBLISHER);
            txn.commit();
        } catch (RollbackException re) {
            DatabaseException de = unwrapDatabaseException(re);
            if (de == null) {
                throw new RuntimeException(re.getLocalizedMessage());
            }
            if (de.getErrorCode() == ServiceErrorMessage.MYSQL_DUPLICATED_ENTRY_ERROR_CODE) {
                throw new ServiceException(
                        "Email already exists in our database",
                        ServiceErrorMessage.USER_SERVICES_EMAIL_EXISTS_ERROR,
                        de);
            }
        } catch (Exception e) {
            rollbackTransaction(txn);
            throw new ServiceException("Unexpected Error",
                    ServiceErrorMessage.COMMON_UNEXPECTED_ERROR, e);
        }
    }
    
}

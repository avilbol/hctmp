/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.security.impl;

import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.exceptions.services.InvalidPasswordLoginException;
import com.hallocasa.commons.exceptions.services.ValidationException;
import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.test.AssertUtils;
import com.hallocasa.commons.test.TestUtils;
import com.hallocasa.commons.vo.AuthInfoVO;
import com.hallocasa.commons.vo.CredentialVO;
import com.hallocasa.services.persistence.impl.AppPersistenceServicesImpl;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.security.local.AuthenticationServices;
import com.hallocasa.tests.database.DatabaseCreator;
import com.hallocasa.tests.database.DatabaseUtils;
import com.hallocasa.tests.database.JhonDoeDataFiller;
import java.util.Locale;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author david
 */
public class AuthenticationServicesImplTest {

    /* static fields */
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(AuthenticationServicesImplTest.class
            .getName());
    private static final Locale EN = new Locale("en");

    /* instance variables */
    private AppPersistenceServices persistenceServices;
    private EntityManagerFactory emf;
    private AuthenticationServices authenticationServices;
    private CredentialVO goodCredential;

    /* constructors */
    @Before
    public void initialize() {
        Locale.setDefault(EN);

        // Open persistence unit
        emf = DatabaseUtils.loadTestAppPersistenceUnit();
        EntityManager em = emf.createEntityManager();

        // creates database
        DatabaseCreator databaseBuilder = new DatabaseCreator(emf);
        databaseBuilder.addDatabaseFiller(new JhonDoeDataFiller());
        databaseBuilder.fillDatabase();

        // services
        persistenceServices = new AppPersistenceServicesImpl(em);
        authenticationServices = new AuthenticationServicesImpl(persistenceServices);

        goodCredential = new CredentialVO(
                JhonDoeDataFiller.JHON_DOE_EMAIL,
                JhonDoeDataFiller.JHON_DOE_PASSWORD);
    }

    @Test
    public void testValidEmailPatternOk() {
        // valid IP for account
        CredentialVO credentialVO = new CredentialVO(goodCredential);

        for (String validEmail : TestUtils.getValidEmails()) {
            credentialVO.setEmail(validEmail);
            // not a validation exception
            AssertUtils.failOnException(validEmail, authenticationServices, METHOD_NAME, new Object[]{credentialVO},
                    new Class[]{CredentialVO.class}, ValidationException.class);
            // must be a service exception about the unknown email
            AssertUtils
                    .assertOnException(validEmail, authenticationServices, METHOD_NAME,
                            new Object[]{credentialVO},
                            new Class[]{CredentialVO.class},
                            InvalidEmailException.class);
        }
    }

    @Test
    public void testInvalidPasswordFail() {
        CredentialVO credentialVO = new CredentialVO(goodCredential);
        credentialVO.setPassword("clavenovalida");

        AssertUtils.assertOnException(authenticationServices, METHOD_NAME,
                new Object[]{credentialVO}, new Class[]{CredentialVO.class},
                InvalidPasswordLoginException.class);
    }

    @Test
    public void testEmptyEmailFail() {
        CredentialVO credentialVO = new CredentialVO(goodCredential);
        credentialVO.setEmail(null);
        ValidationException se;

        // null
        se = AssertUtils.assertOnException(authenticationServices, METHOD_NAME,
                new Object[]{credentialVO}, new Class[]{CredentialVO.class},
                ValidationException.class);
        Assert.assertEquals(se.getMessage(), "email-"
                + ValidationMessages.getString(ValidationMessages.NOT_NULL, EN));

        // empty
        credentialVO.setEmail("");
        se = AssertUtils.assertOnException(authenticationServices, METHOD_NAME,
                new Object[]{credentialVO}, new Class[]{CredentialVO.class},
                ValidationException.class);
        credentialVO.setEmail("");
        Assert.assertEquals(se.getMessage(), "email-"
                + ValidationMessages.getString(ValidationMessages.NOT_EMPTY, EN));
    }

    @Test
    public void testEmptyPasswordFail() {
        CredentialVO credentialVO = new CredentialVO(goodCredential);
        credentialVO.setPassword(null);
        // null
        ValidationException se = AssertUtils.assertOnException(authenticationServices, METHOD_NAME, new Object[]{credentialVO},
                new Class[]{CredentialVO.class}, ValidationException.class);
        Assert.assertEquals(se.getMessage(), "password-"
                + ValidationMessages.getString(ValidationMessages.NOT_NULL, EN));
        // empty
        credentialVO.setPassword("");
        se = AssertUtils.assertOnException(authenticationServices, METHOD_NAME,
                new Object[]{credentialVO}, new Class[]{CredentialVO.class},
                ValidationException.class);
        credentialVO.setEmail("");
        Assert.assertEquals(se.getMessage(), "password-"
                + ValidationMessages.getString(ValidationMessages.NOT_EMPTY, EN));
    }

    @Test
    public void testInvalidEmailFail() {
        CredentialVO credentialVO = new CredentialVO(goodCredential);
        credentialVO.setEmail("invento@empresa.com");

        AssertUtils.assertOnException(authenticationServices, METHOD_NAME,
                new Object[]{credentialVO}, new Class[]{CredentialVO.class},
                InvalidEmailException.class);
    }

    @Test
    public void testInvalidEmailPatternFail() {
        CredentialVO credentialVO = new CredentialVO(goodCredential);

        for (String invalidEmail : TestUtils.getInvalidPatternEmails()) {
            credentialVO.setEmail(invalidEmail);
            ValidationException se = AssertUtils.assertOnException(authenticationServices, METHOD_NAME, new Object[]{credentialVO},
                    new Class[]{CredentialVO.class}, ValidationException.class);
            Assert.assertEquals(se.getMessage(), "email-"
                    + ValidationMessages.getString(
                            ValidationMessages.EMAIL_PATTERN, EN));
        }
    }

    @Test
    public void testInvalidSizeFail() {
        CredentialVO credentialVO = new CredentialVO(goodCredential);

        for (String invalidEmail : TestUtils.getInvalidSizeEmails()) {
            credentialVO.setEmail(invalidEmail);
            ValidationException se = AssertUtils.assertOnException(authenticationServices,
                    METHOD_NAME, new Object[]{credentialVO},
                    new Class[]{CredentialVO.class}, ValidationException.class);
            Assert.assertEquals(se.getMessage(), "email-"
                    + ValidationMessages.getString(ValidationMessages.SIZE, EN)
                    .replace("{min}", "0").replace("{max}", "80"));
        }
    }

    @Test
    public void testValidCredentialsOK() {
        AuthInfoVO authInfoVO = (AuthInfoVO) AssertUtils.failOnException(authenticationServices,
                METHOD_NAME, new Object[]{goodCredential},
                new Class<?>[]{CredentialVO.class}, Exception.class);
        Assert.assertEquals(authInfoVO.getUser().getId().longValue(),
                JhonDoeDataFiller.JHON_DOE_ID);
    }
    private static final String METHOD_NAME = "authenticate";

}

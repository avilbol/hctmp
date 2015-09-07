package com.hallocasa.services.user.impl;

import com.hallocasa.commons.codec.CodecUtils;
import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.exceptions.services.ValidationException;
import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.test.AssertUtils;
import com.hallocasa.commons.test.TestUtils;
import com.hallocasa.commons.vo.RegisterUserVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.services.persistence.impl.AppPersistenceServicesImpl;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.user.local.SignUpServices;
import com.hallocasa.tests.database.DatabaseCreator;
import com.hallocasa.tests.database.DatabaseUtils;
import com.hallocasa.tests.database.JhonDoeDataFiller;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

public class RegisterUserTest {

    private static final String METHOD_NAME = "registerUser";
    /* static fields */
    private static final Logger LOG = Logger
            .getLogger(RegisterUserTest.class.getName());
    private static final Locale EN = new Locale("en");
    private static final Class<?>[] PARAM_CLASSES = new Class[]{
        RegisterUserVO.class};

    /* instance variables */
    private AppPersistenceServices persistenceServices;
    private EntityManagerFactory emf;
    private SignUpServices signUpServices;
    private RegisterUserVO goodRegisterUserVO;
    private EntityManager em;

    /* constructors */
    @Before
    public void initialize() {
        Locale.setDefault(EN);

        // Open persistence unit
        try {
            emf = DatabaseUtils.loadTestAppPersistenceUnit();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            throw new RuntimeException(e);
        }
        em = emf.createEntityManager();

        // creates database
        DatabaseCreator databaseBuilder = new DatabaseCreator(emf);
        databaseBuilder.addDatabaseFiller(new JhonDoeDataFiller());
        databaseBuilder.fillDatabase();

        // services
        persistenceServices = new AppPersistenceServicesImpl(em);
        signUpServices = new SignUpServicesImpl(persistenceServices, null);
        goodRegisterUserVO = createGoodParameters();
    }


    /* Methods */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testValidEmailPatternOk() {
        // valid IP for account
        RegisterUserVO testRegisterUserVO = cloneGoodRegisterUserVO();
        for (String validEmail : TestUtils.getValidEmails()) {
            testRegisterUserVO.setEmail(validEmail);
            // not a validation exception
            AssertUtils.failOnException(validEmail, signUpServices, METHOD_NAME,
                    new Object[]{testRegisterUserVO},
                    PARAM_CLASSES, ValidationException.class);
        }
    }

    @Test
    public void testEmptyEmailFail() {
        RegisterUserVO testRegisterUserVO = cloneGoodRegisterUserVO();
        testRegisterUserVO.setEmail(null);
        ValidationException se;

        // null
        se = AssertUtils.assertOnException(signUpServices, METHOD_NAME,
                new Object[]{testRegisterUserVO}, PARAM_CLASSES,
                ValidationException.class);
        Assert.assertEquals("email-"
                + ValidationMessages.getString(ValidationMessages.NOT_NULL, EN),
                se.getMessage());

        // empty
        testRegisterUserVO.setEmail("");
        se = AssertUtils.assertOnException(signUpServices, METHOD_NAME,
                new Object[]{testRegisterUserVO}, PARAM_CLASSES,
                ValidationException.class);
        Assert.assertEquals("email-"
                + ValidationMessages.getString(ValidationMessages.NOT_EMPTY, EN),
                se.getMessage());
    }

    @Test
    public void testPasswordRequiredValidationFail() {
        RegisterUserVO cloned = cloneGoodRegisterUserVO();
        cloned.setPassword(null);
        testValidationFailed(new Object[]{cloned}, "password-"
                + ValidationMessages.getString(ValidationMessages.NOT_NULL, EN));
    }

    @Test
    public void testPasswordPatternFail() {
        RegisterUserVO cloned = cloneGoodRegisterUserVO();
        cloned.setPassword("invalid password");
        testValidationFailed(new Object[]{cloned}, "password-"
                + ValidationMessages.getString(ValidationMessages.PASSWORD_PATTERN,
                        EN));
    }

    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testEmailAlreadyExistsExceptionThrown() {
        RegisterUserVO cloned = cloneGoodRegisterUserVO();
        cloned.setEmail(JhonDoeDataFiller.JHON_DOE_EMAIL);
        assertOnException(new Object[]{cloned}, InvalidEmailException.class);
    }

    @Test
    public void testAllValidationsOk() {
        failOnException(new Object[]{goodRegisterUserVO},
                ValidationException.class);
    }

    @Test
    public void testUserIsWellCreated() {
        em.getTransaction().begin();
        UserVO userVO = failOnException(
                new Object[]{goodRegisterUserVO}, Exception.class);
        em.getTransaction().commit();
        em.clear();

        User user = em.find(User.class, userVO.getId());
        Assert.assertEquals(goodRegisterUserVO.getEmail(), user
                .getEmail());
        Assert.assertEquals(CodecUtils.encryptPassword(
                goodRegisterUserVO.getPassword()), user.getPassword());

    }
    /* private utilities */

    /**
     * Validates an exception in expected failure
     *
     * @param params
     * @param expectedMessage
     */
    private void testValidationFailed(Object[] params, String expectedMessage) {
        ValidationException ve = assertOnException(params,
                ValidationException.class);

        Assert.assertEquals(expectedMessage, ve.getMessage());
    }

    /**
     * Assert on exception
     *
     * @param <T>
     * @param params
     * @param expectedException
     * @return
     */
    private <T extends Exception> T assertOnException(Object[] params,
            Class<T> expectedException) {
        T ve = AssertUtils.assertOnException(signUpServices, METHOD_NAME,
                params, PARAM_CLASSES,
                expectedException);
        return ve;
    }

    private RegisterUserVO cloneGoodRegisterUserVO() {
        RegisterUserVO clone = new RegisterUserVO();
        clone.setEmail(goodRegisterUserVO.getEmail());
        clone.setPassword(goodRegisterUserVO.getPassword());
        return clone;
    }

    /**
     * Fails on exception
     *
     * @param <T>
     * @param params
     * @param exceptionToFail
     */
    private <T extends Exception> UserVO failOnException(Object[] params,
            Class<T> exceptionToFail) {
        return (UserVO) AssertUtils.failOnException(signUpServices,
                METHOD_NAME, params, PARAM_CLASSES,
                exceptionToFail);
    }

    /**
     *
     * @return
     */
    private RegisterUserVO createGoodParameters() {
        RegisterUserVO registerUserVO = new RegisterUserVO();
        registerUserVO.setEmail("newuser@dominio.com");
        registerUserVO.setPassword("Aa1#aaaa");
        return registerUserVO;
    }
}

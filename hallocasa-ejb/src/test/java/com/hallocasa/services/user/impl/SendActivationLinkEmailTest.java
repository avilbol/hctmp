package com.hallocasa.services.user.impl;

import com.hallocasa.commons.exceptions.services.ValidationException;
import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.test.AssertUtils;
import com.hallocasa.commons.test.TestUtils;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailServices;
import com.hallocasa.services.messaging.local.MailServices.BuildInMailType;
import com.hallocasa.services.persistence.impl.AppPersistenceServicesImpl;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.user.local.SignUpServices;
import com.hallocasa.tests.database.DatabaseCreator;
import com.hallocasa.tests.database.DatabaseUtils;
import com.hallocasa.tests.database.JhonDoeDataFiller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SendActivationLinkEmailTest {

    private static final String METHOD_NAME = "sendActivationLinkEmail";
    /* static fields */
    private static final Logger LOG = Logger
            .getLogger(SendActivationLinkEmailTest.class.getName());
    private static final Locale EN = new Locale("en");
    private static final String ACTIVATION_URL = "http://localhost:10080/SocialWeb/#!useractivation/token/1215ce7cb9b776edee61bb58cec6be73";
    private static final String ACTIVATION_KEY = "1215ce7cb9b776edee61bb58cec6be73";
    private static final Class<?>[] PARAM_CLASSES = new Class[]{long.class,
        String.class, String.class};

    /* instance variables */
    private AppPersistenceServices persistenceServices;
    private EntityManagerFactory emf;
    private SignUpServices signUpServices;
    private MailServices mailServices;
    private Object[] goodParameters;

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
        EntityManager em = emf.createEntityManager();

        // creates database
        DatabaseCreator databaseBuilder = new DatabaseCreator(emf);
        databaseBuilder.addDatabaseFiller(new JhonDoeDataFiller());
        databaseBuilder.fillDatabase();

        // services
        mailServices = Mockito.mock(MailServices.class);
        persistenceServices = new AppPersistenceServicesImpl(em);
        signUpServices = new SignUpServicesImpl(persistenceServices,
                mailServices);

        goodParameters = new Object[]{
            JhonDoeDataFiller.JHON_DOE_ID, ACTIVATION_URL,
            ACTIVATION_KEY};
    }

    /* Methods */
    @Test
    public void testUrlPatternValidationFail() {
        Object[] parameters = cloneGoodProperties();

        for (String invalidUrl : TestUtils.getInvalidPatternUrls()) {
            parameters[1] = invalidUrl;
            ValidationException se = AssertUtils.assertOnException(
                    signUpServices, METHOD_NAME, parameters, PARAM_CLASSES,
                    ValidationException.class);

            Assert.assertEquals(se.getMessage(), "url-"
                    + ValidationMessages.getString(ValidationMessages.URL_PATTERN,
                            EN));
        }
    }

    @Test
    public void testUrlEmptyValidationFail() {
        Object[] parameters = cloneGoodProperties();
        parameters[1] = null;

        ValidationException se = AssertUtils.assertOnException(signUpServices,
                METHOD_NAME, parameters, PARAM_CLASSES, ValidationException.class);
        Assert.assertEquals(se.getMessage(), "url-"
                + ValidationMessages.getString(ValidationMessages.NOT_NULL, EN));

        parameters[1] = "";
        se = AssertUtils.assertOnException(signUpServices, METHOD_NAME,
                parameters, PARAM_CLASSES, ValidationException.class);
        Assert.assertEquals(se.getMessage(), "url-"
                + ValidationMessages.getString(ValidationMessages.NOT_EMPTY, EN));
    }

    @Test
    public void testValidationOk() {
        AssertUtils.failOnException(signUpServices, METHOD_NAME,
                goodParameters, PARAM_CLASSES, ValidationException.class);
    }

    @Test
    public void testEmailSentOk() throws MailServicesErrorException {

        signUpServices.sendActivationLinkEmail((long) goodParameters[0],
                (String) goodParameters[1], (String) goodParameters[2]);

        List<String> emails = new ArrayList<>();
        emails.add(JhonDoeDataFiller.JHON_DOE_EMAIL);
        Map<String, String> params = new HashMap<>();
        params.put("USER_ACTIVATION_LINK", ACTIVATION_URL);
        params.put("USER_ACTIVATION_KEY", ACTIVATION_KEY);

        Mockito.verify(mailServices, Mockito.timeout(1)).sendMail(
                BuildInMailType.USER_ACTIVATION, EN, emails, params);
    }

    @Test
    public void testInvalidAccontId() {
        Object[] parameters = cloneGoodProperties();
        parameters[0] = 99999L;

        ValidationException ve = AssertUtils.assertOnException(signUpServices,
                METHOD_NAME, parameters, PARAM_CLASSES, ValidationException.class);
        Assert.assertEquals("User with id 99999 doesn't exist", ve
                .getMessage());
    }

    /* private utilities */
    private Object[] cloneGoodProperties() {
        Object[] parameters = new Object[3];
        parameters[0] = goodParameters[0];
        parameters[1] = goodParameters[1];
        parameters[2] = goodParameters[2];
        return parameters;
    }
}

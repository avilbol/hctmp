package com.hallocasa.services.messagein.impl;

import com.hallocasa.commons.exceptions.services.ValidationException;
import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.test.AssertUtils;
import com.hallocasa.commons.test.TestUtils;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.impl.MailServicesImpl;
import com.hallocasa.services.messaging.local.MailServices;
import com.hallocasa.services.messaging.local.MailServices.MailDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class SendMailTest {

    private static final String GOOD_EMAIL_2 = "good2@email.com";
    private static final String GOOD_EMAIL_1 = "good1@email.com";
    private static final String EMAIL_BODY_TEMPLATE = "$P{PARAMETER1} This is a Test email body template: $P{PARAMETER1}, $P{PARAMETER2}, $P{PARAMETER3}";
    private static final String EMAIL_SUBJECT_TEMPALTE = "$P{PARAMETER2} Test Subject";
    private static final String EMAIL_BODY = "value1 This is a Test email body template: value1, value2, value3";
    private static final String EMAIL_SUBJECT = "value2 Test Subject";
    private static final Locale EN = new Locale("en");
    private static final Class<?>[] PARAM_CLASSES = new Class[]{
        MailDefinition.class, Locale.class, List.class, Map.class};

    /* instance variables */
    private MailServices mailServices;
    private MailDefinition mailDefinition;
    private Object[] goodParameters;

    /* constructors */
    @Before
    public void initialize() {

        Locale.setDefault(EN);

        // initialize dependencies
        mailServices = new MailServicesImpl(null);
        mailDefinition = Mockito.mock(MailDefinition.class);

        // initialize good parameters
        List<String> emails = new ArrayList<>();
        emails.add(GOOD_EMAIL_1);
        emails.add(GOOD_EMAIL_2);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("PARAMETER1", "value1");
        parameters.put("PARAMETER2", "value2");
        parameters.put("PARAMETER3", "value3");

        Mockito.when(mailDefinition.getBodyTemplate(Mockito.any(Locale.class)))
                .thenReturn(EMAIL_BODY_TEMPLATE);
        Mockito.when(
                mailDefinition.getSubjectTemplate(Mockito.any(Locale.class)))
                .thenReturn(EMAIL_SUBJECT_TEMPALTE);

        goodParameters = new Object[]{mailDefinition, new Locale("en"),
            emails, parameters};

    }

    /* Methods */
    @Test
    public void testEmailValidationFail() {
        Object[] params = cloneGoodParameters();
        for (String invalidEmail : TestUtils.getInvalidPatternEmails()) {
            params[2] = createEmailList(invalidEmail);

            ValidationException ve = AssertUtils.assertOnException(
                    invalidEmail, mailServices, "sendMail", params, PARAM_CLASSES,
                    ValidationException.class);

            Assert.assertEquals(invalidEmail, "email-"
                    + ValidationMessages.getString(
                            ValidationMessages.EMAIL_PATTERN, EN), ve.getMessage());
        }
    }

    private List<String> createEmailList(String... emails) {
        List<String> list = new ArrayList<>();
        for (String email : emails) {
            list.add(email);
        }
        return list;
    }

    @Test
    public void testEmailValidationOk() {
        Object[] params = cloneGoodParameters();
        for (String validEmail : TestUtils.getValidEmails()) {
            params[2] = createEmailList(validEmail);
            AssertUtils.failOnException(validEmail, mailServices, "sendMail",
                    params, PARAM_CLASSES, ValidationException.class);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void testParamsFillingOk() throws MailServicesErrorException {
        MailServices mailServicesTest = Mockito.spy(mailServices);

        Mockito.doNothing().when(mailServicesTest).sendFilledMessage(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        mailServicesTest.sendMail((MailDefinition) goodParameters[0],
                (Locale) goodParameters[1], (List) goodParameters[2],
                (Map) goodParameters[3]);

        Mockito.verify(mailServicesTest, Mockito.times(1)).sendFilledMessage(
                EMAIL_BODY, EMAIL_SUBJECT, GOOD_EMAIL_1 + "," + GOOD_EMAIL_2);

    }

    private Object[] cloneGoodParameters() {
        Object[] params = new Object[goodParameters.length];
        System.arraycopy(goodParameters, 0, params, 0, goodParameters.length);
        return params;
    }

    /* Getters & Setters */
}

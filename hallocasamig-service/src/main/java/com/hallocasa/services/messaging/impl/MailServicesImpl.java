package com.hallocasa.services.messaging.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;

import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailServices;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.validators.BeanValidator;
import com.hallocasa.validators.StandardPropertyValidator;

/**
 * @author David Mantilla
 *
 * @since 1.7
 */
@Stateless
public class MailServicesImpl implements MailServices {

    /* static fields */
    private static final Logger LOG = Logger.getLogger(MailServicesImpl.class
            .getName());

    /* dependencies */
    @Resource(name = "mail/HallocasaSession")
    private Session mailSession;

    /* instance variables */

    /* constructors */
    
    /**
     * Default Constructor
     */
    public MailServicesImpl() {
    }

    /**
     * Constructor with dependencies injection
     *
     * @param mailSession
     */
    public MailServicesImpl(Session mailSession) {
        super();
        this.mailSession = mailSession;
    }

    /* Methods */

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hallocasa.services.MailServicesLocal#sendMail(com.mobiera.social
     * .services.MailServices.MailDefinition, java.util.Locale, java.util.List)
     */
    @Override
    public void sendMail(MailDefinition mailDefinition, Locale locale,
            List<String> destEmails, Map<String, String> parameters)
            throws MailServicesErrorException {

        // validates email
        StandardPropertyValidator validator = new StandardPropertyValidator();
        for (String email : destEmails) {
            validator.setEmail(email);
            validateProperty(validator, "email",
                    StandardPropertyValidator.class);
        }

        // replace parameters in body and subject
        String body = mailDefinition.getBodyTemplate(locale);
        String subject = mailDefinition.getSubjectTemplate(locale);

        for (String paramName : parameters.keySet()) {
            body = body.replaceAll("\\$P\\{" + paramName.toUpperCase() + "\\}",
                    parameters.get(paramName));
            subject = subject.replaceAll("\\$P\\{" + paramName.toUpperCase()
                    + "\\}", parameters.get(paramName));
        }

        // create a comma separated list of addresses
        StringBuilder addresses = new StringBuilder();
        boolean needsComma = false;
        for (String destEmail : destEmails) {
            if (needsComma) {
                addresses.append(",");
            }
            addresses.append(destEmail);
            needsComma = true;
        }

        sendFilledMessage(body, subject, addresses.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hallocasa.services.local.MailServicesLocal#sendFilledMessage
     * (java.lang.String, java.lang.String, java.lang.StringBuilder)
     */
    @Override
    public void sendFilledMessage(String body, String subject, String addresses)
            throws MailServicesErrorException {
        // send the message
        try {
            Message message = new MimeMessage(mailSession);
            //message.setHeader("Content-Type", "text/html;charset=UTF-8");
            message.setRecipients(Message.RecipientType.TO, InternetAddress
                    .parse(addresses));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            LOG.log(Level.SEVERE, "Cannot send mail", e);
            throw new MailServicesErrorException(e);
        }
    }

    protected <T> void validateProperty(T bean, String propertyName,
            Class<T> beanClass) {
        validateProperty(bean, propertyName, null, beanClass);
    }
    
    protected <T> void validateProperty(T bean, String propertyName,
            String propertyLabel, Class<T> beanClass) {
        Set<ConstraintViolation<T>> violations = BeanValidator
                .validateProperty(bean, propertyName, Locale.getDefault());
        for (ConstraintViolation<T> violation : violations) {
            throw new FatalException((propertyLabel == null ? violation
                    .getPropertyPath() : propertyLabel)
                    + "-" + violation.getMessage());
        }
    }
}

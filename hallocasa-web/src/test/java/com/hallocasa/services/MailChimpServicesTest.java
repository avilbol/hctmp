package com.hallocasa.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hallocasa.business.exceptions.MailChimpException;
import com.hallocasa.business.services.MailChimpServices;
import com.hallocasa.business.services.interfaces.MailChimpServicesLocal;
import com.hallocasa.business.vo.MailChimpList;
import com.hallocasa.business.vo.MailChimpMergeVars.TypeEnum;
import com.hallocasa.commons.Language;

public class MailChimpServicesTest {

    private MailChimpServicesLocal mailChimpServices;
    private static final Logger LOG = Logger
            .getLogger(MailChimpServicesTest.class.getName());

    public MailChimpServicesTest() {
        // TODO Auto-generated constructor stub
    }

    @Before
    public void initialize() {
        mailChimpServices = new MailChimpServices();
    }

    @Test
    public void testFindListsServices() {
        List<MailChimpList> lists = mailChimpServices.findLists();
        for (MailChimpList list : lists) {
            System.out.println(list.getId() + " " + list.getName());
        }
        Assert.assertFalse(lists.isEmpty());
    }

    @Test
    public void testSubscribeUser() {
        try {
            mailChimpServices.subscribeNewUser("dmantil@gmail.com", "David",
                    "Mantilla", Language.es, TypeEnum.PUBLISHER);
        } catch (MailChimpException e) {
            LOG.log(Level.SEVERE, "", e);
            Assert.fail();
        }
    }
}

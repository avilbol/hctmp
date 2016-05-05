package com.hallocasa.services;

import com.hallocasa.services.messaging.impl.MailChimpServicesImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hallocasa.exceptions.MailChimpException;
import com.hallocasa.services.messaging.local.MailChimpServices;
import com.hallocasa.vo.MailChimpList;
import com.hallocasa.vo.MailChimpMergeVars.TypeEnum;
import com.hallocasa.commons.Language;

public class MailChimpServicesTest {

    private MailChimpServices mailChimpServices;
    private static final Logger LOG = Logger
            .getLogger(MailChimpServicesTest.class.getName());

    public MailChimpServicesTest() {
        // TODO Auto-generated constructor stub
    }

    @Before
    public void initialize() {
        mailChimpServices = new MailChimpServicesImpl();
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

package com.ecwid.mailchimp.method.v1_3.campaign;

import com.ecwid.mailchimp.MailChimpMethod;
import com.ecwid.mailchimp.MailChimpObject;

/**
 * Base class for every campaing related
 */
public abstract class CampaingRelatedMethod<R> extends MailChimpMethod<R>
{
    @MailChimpObject.Field
    public String cid;

}
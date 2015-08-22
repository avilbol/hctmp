package com.hallocasa.vo;

import com.ecwid.mailchimp.MailChimpObject;

/**
 * Value object for mail chimp subscribing variables
 * 
 * @author David Mantilla
 *
 */
public class MailChimpMergeVars extends MailChimpObject {

    /* static */
    private static final long serialVersionUID = 1863303414724659369L;

    public enum TypeEnum {
        NEWSLETTER, PUBLISHER;
    }

    /* instance variables */
    @Field
    public String EMAIL;
    @Field
    public String FNAME;
    @Field
    public String LNAME;
    @Field
    public String UTYPE;
    @Field
    public String mc_language;

    /* constructors */

    /**
     * Default constructor
     */
    public MailChimpMergeVars() {
    }

    /**
     * @param email
     * @param fname
     * @param lname
     */
    public MailChimpMergeVars(String email, String fname, String lname,
            String language, String userType) {
        this.EMAIL = email;
        this.FNAME = fname;
        this.LNAME = lname;
        this.UTYPE = userType;
        this.mc_language = language;
    }

}

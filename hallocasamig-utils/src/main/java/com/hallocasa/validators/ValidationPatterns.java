package com.hallocasa.validators;

/**
 * Constants for validation
 *
 * @author David Mantilla
 * @since 1.7
 */
public interface ValidationPatterns {

    /* static fields */
    /**
     * Regular expression for email validation
     */
    public static final String EMAIL_PATTERN = "^(?:[a-z0-9!#$%&'*+\\/=?^_`{|}~-]"
    		+ "+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)*|(?:[\\x01-\\x08\\x0b\\x0c\\"
    		+ "x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-"
    		+ "\\x7f])*)@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]"
    		+ "*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
    		+ "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
    		+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\"
    		+ "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    /**
     * Regular expression for URL validation
     */
    public static final String URL_PATTERN = "^(((https?|ftp|file):\\/\\/)?[-a-zA-Z0-9+&@#\\/%_|!:,;]+(\\.[-a-zA-Z0-9+?:&@#\\/%=~_|]+)+)?";
    
    /**
     * Regular expression for LinkedIn URL validation
     */
    public static final String LINKED_IN_PATTERN = "^((https:\\/\\/)?([a-z]{1,3}.)?linkedin.com\\/in\\/[-a-zA-Z0-9+&?=@#\\/%_|!:,;]+)?";
    
    /**
     * Regular expression for Password validation
     */
    public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[#%A-Za-z0-9&^$@~!+:*=]{8,15}$";
    /**
     * Regular expression for phone
     */
    public static final String PHONE_PATTERN = "([\\+(]?(\\d){1,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})";
    /**
     * Regular expression for general names
     */
    public static final String GENERAL_NAME = "^[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—‹¸_\\s.√°√©√≠√≥√∫√±√ë√?√â√?√ì√ö√º√ú'\\-\\&0-9,]*$";
    /**
     * Regular expression for street address
     */
    public static final String STREET_ADDRESS = "^([a-zA-Z\\s#.0-9\\-]*)$";

    /**
     * Regular expression for use cases
     */
    public static final String USE_CASE_NAME_PATTERN = "^(\\/[a-zA-Z_\\-\\s0-9\\.]+)*$";
    /* Methods */

}

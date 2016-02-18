package com.hallocasa.commons.validation;

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
    public static final String EMAIL_PATTERN = "(^[\\w.\\+]+@[a-zA-Z_]+?(\\.[a-zA-Z]{2,3}){1,2}$){0,1}";
    /**
     * Regular expression for URL validation
     */
    public static final String URL_PATTERN = "^(https?:\\/\\/([\\da-z\\.-\\:]+)\\.?([a-z\\.]{2,6})?([\\/\\w%!#\\.\\-\\?\\&=])*\\/?){0,1}$";
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
    public static final String GENERAL_NAME = "^[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—‹¸\\s.√°√©√≠√≥√∫√±√ë√?√â√?√ì√ö√º√ú'\\-\\&0-9,]*$";
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

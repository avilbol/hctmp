package com.hallocasa.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom validation for empty validation
 *
 * @author David Mantilla
 * @since 1.7
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

    @Override
    public void initialize(NotEmpty arg0) {
        //
    }

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if (arg0 == null) {
            return true;
        }
        if (arg0.isEmpty()) {
            return false;
        }
        return true;
    }

}

package org.raulzuniga.offers.validators;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *  ValidEmailImpl class.
 *  */
public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {

    /** Minimun. */
    private int min;

    /**
     *  iniitialize method.
     *  @param constraintAnnotation constraintAnnotation
     */
    public void initialize(final ValidEmail constraintAnnotation) {
        min = constraintAnnotation.min();
    }

    /**
     *  isValid method.
     *  @param email email
     *  @param context context
     *  @return true if email is valid; false othereise
     */
    public boolean isValid(final String email,
                           final ConstraintValidatorContext context) {

        if ((email.length() < min)
                && (!EmailValidator.getInstance(false).isValid(email))) {
            return false;
        }

        return true;
    }
}

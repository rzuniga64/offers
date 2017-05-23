package org.raulzuniga.offers.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * ValidEmail interface.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = org.raulzuniga.offers.validators.ValidEmailImpl.class)
public @interface ValidEmail {

    /**
     *  Error message method.
     *  @return an error message
     */
    String message() default "This does not appear to be a valid email address";

    /**
     *  Groups method.
     *  @return an array of type Class.
     */
    Class<?>[] groups() default { };

    /**
     *  Payload method.
     *  @return an array of type Class
     */
    Class<? extends Payload>[] payload() default { };

    /**
     * min method.
     * @return the minimum
     */
    int min() default 5;
}

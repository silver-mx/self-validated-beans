package com.dns.validator;

import jakarta.validation.*;

import java.util.Set;

/**
 * This interface allows a bean to exercise all the jakarta validations defined. All beans need to implement this interface,
 * and call validate() in order to be validated.
 */
public interface SelfValidated {

    /* NOTE: ValidatorFactory should be closed when the VM is shutting down (see App.java) */
    static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    static final Validator validator = validatorFactory.getValidator();


    default void validate() {
        Set<ConstraintViolation<SelfValidated>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}

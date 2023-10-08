package com.dns.validator;

import jakarta.validation.*;

import java.util.Set;

public interface SelfValidation {

    /* NOTE: ValidatorFactory should be closed when the VM is shutting down (see App.java) */
    static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    static final Validator validator = validatorFactory.getValidator();


    default void validate() {
        Set<ConstraintViolation<SelfValidation>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }


}

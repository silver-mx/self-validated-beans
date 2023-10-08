package com.dns.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public interface SelfValidation {

    static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    default void validate() {
        Set<ConstraintViolation<SelfValidation>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }


}

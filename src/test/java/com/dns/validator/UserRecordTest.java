package com.dns.validator;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRecordTest {

    @Test
    void createSuccess() {
        assertDoesNotThrow(() -> new UserRecord("username123", "password123"));
    }

    @Test
    void createFailsForMissingUsername() {
        var violationException = assertThrows(ConstraintViolationException.class, () -> new UserRecord(null, "password123"));
        assertEquals("username: must not be null", violationException.getMessage());
    }

    @Test
    void createFailsForInvalidPassword() {
        var violationException = assertThrows(ConstraintViolationException.class, () -> new UserRecord("username123", "pass"));
        assertEquals("password: size must be between 8 and 30", violationException.getMessage());
    }

}
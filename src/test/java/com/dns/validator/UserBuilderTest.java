package com.dns.validator;

import com.dns.validator.UserBuilder.Builder;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBuilderTest {

    private Builder userBuilder;

    @BeforeEach
    void setup() {
        userBuilder = UserBuilder.builder()
                .username("username123")
                .password("password123");
    }

    @Test
    void builderSuccess() {
        assertDoesNotThrow(() -> userBuilder.build());
    }

    @Test
    void builderFailsForMissingUsername() {
        var violationException = assertThrows(ConstraintViolationException.class, () -> userBuilder.username(null).build());
        assertEquals("username: must not be null", violationException.getMessage());
    }

    @Test
    void builderFailsForInvalidPassword() {
        var violationException = assertThrows(ConstraintViolationException.class, () -> userBuilder.password("short").build());
        assertEquals("password: size must be between 8 and 30", violationException.getMessage());
    }

}
package com.dns.validator;

import com.dns.validator.UserPojo.Builder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPojoTest {

    private Builder userBuilder;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        userBuilder = UserPojo.builder()
                .withUsername("username123")
                .withPassword("password123");
        objectMapper = new ObjectMapper();
    }

    @Test
    void builderSuccess() {
        assertDoesNotThrow(() -> userBuilder.build());
    }

    @Test
    void deserializeSuccess() throws JsonProcessingException {
        var user = objectMapper.readValue("""
                { "username": "username123", "password": "password123" }
                """, UserPojo.class);
        assertEquals(userBuilder.build(), user);
    }

    @Test
    void builderFailsForMissingUsername() {
        var violationException = assertThrows(ConstraintViolationException.class, () -> userBuilder.withUsername(null).build());
        assertEquals("username: must not be null", violationException.getMessage());
    }

    @Test
    void deserializeFailsForMissingUsername() {
        var exception = assertThrows(ValueInstantiationException.class, () ->
                objectMapper.readValue("""
                        { "password": "password123" }
                        """, UserPojo.class));
        assertEquals("username: must not be null", exception.getCause().getMessage());
    }

    @Test
    void builderFailsForInvalidPassword() {
        var violationException = assertThrows(ConstraintViolationException.class, () -> userBuilder.withPassword("short").build());
        assertEquals("password: size must be between 8 and 30", violationException.getMessage());
    }

    @Test
    void deserializeFailsForInvalidPassword() {
        var exception = assertThrows(ValueInstantiationException.class, () ->
                objectMapper.readValue("""
                        { "username": "username123", "password": "short" }
                        """, UserPojo.class));
        assertEquals("password: size must be between 8 and 30", exception.getCause().getMessage());
    }

}
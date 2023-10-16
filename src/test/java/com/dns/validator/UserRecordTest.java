package com.dns.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRecordTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void createSuccess() {
        assertDoesNotThrow(() -> new UserRecord("username123", "password123", "user@email.com"));
    }

    @Test
    void deserializeSuccess() throws JsonProcessingException {
        var expectedUser = new UserRecord("username123", "password123", "user@email.com");
        var user = objectMapper.readValue("""
                { "username": "username123", "password": "password123", "email": "user@email.com" }
                """, UserRecord.class);

        assertEquals(expectedUser, user);
    }

    @Test
    void createFailsForMissingUsername() {
        var violationException = assertThrows(ConstraintViolationException.class, () -> new UserRecord(null, "password123", "user@email.com"));
        assertEquals("username: must not be null", violationException.getMessage());
    }

    @Test
    void createFailsForInvalidPassword() {
        var violationException = assertThrows(ConstraintViolationException.class, () -> new UserRecord("username123", "pass", "user@email.com"));
        assertEquals("password: size must be between 8 and 30", violationException.getMessage());
    }

    @Test
    void createFailsForInvalidEmail() {
        var violationException = assertThrows(ConstraintViolationException.class, () -> new UserRecord("username123", "password123", "useremail"));
        assertEquals("email: must be a well-formed email address", violationException.getMessage());
    }

}
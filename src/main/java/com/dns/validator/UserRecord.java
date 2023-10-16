package com.dns.validator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRecord(
        @NotNull
        @Size(min = 8, max = 20)
        String username,
        @NotNull
        @Size(min = 8, max = 30)
        String password,
        @NotNull
        @Email
        String email) implements SelfValidation {

    public UserRecord(final String username, final String password, final String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        validate();
    }
}

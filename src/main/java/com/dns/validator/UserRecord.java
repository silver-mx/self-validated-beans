package com.dns.validator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRecord(
        @NotNull
        @Size(min = 8, max = 20)
        String username,
        @NotNull
        @Size(min = 8, max = 30)
        String password) implements SelfValidation {

    public UserRecord(String username, String password) {
        this.username = username;
        this.password = password;
        validate();
    }
}

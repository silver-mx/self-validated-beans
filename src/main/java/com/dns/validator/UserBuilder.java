package com.dns.validator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder(builderClassName = "Builder")
public class UserBuilder implements SelfValidation {

    @NotNull
    @Size(min = 8, max = 20)
    private final String username;
    @NotNull
    @Size(min = 8, max = 30)
    private final String password;

    public static class Builder {
        public com.dns.validator.UserBuilder build() {
            var user = new com.dns.validator.UserBuilder(this.username, this.password);
            user.validate();

            return user;
        }
    }
}

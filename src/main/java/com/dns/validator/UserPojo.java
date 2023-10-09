package com.dns.validator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder", setterPrefix = "with")
@JsonDeserialize(builder = UserPojo.Builder.class)
public class UserPojo implements SelfValidation {

    @NotNull
    @Size(min = 8, max = 20)
    String username;
    @NotNull
    @Size(min = 8, max = 30)
    String password;

    public static class Builder {
        public UserPojo build() {
            var user = new UserPojo(this.username, this.password);
            user.validate();

            return user;
        }
    }


}

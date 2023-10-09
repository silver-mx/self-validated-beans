package com.dns.validator.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createUserPojoSuccess() {
        webTestClient.post()
                .uri("/v1/test/user-pojo")
                .contentType(APPLICATION_JSON)
                .bodyValue("{ \"username\": \"username123\", \"password\": \"password123\" }")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void createUserPojoFailsWithValidationError() {
        webTestClient.post()
                .uri("/v1/test/user-pojo")
                .contentType(APPLICATION_JSON)
                .bodyValue("{ \"username\": \"user\", \"password\": \"pass\" }")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .json("{\"status\":400,\"message\":\"username: size must be between 8 and 20, password: size must be between 8 and 30\"}", true);
    }
}
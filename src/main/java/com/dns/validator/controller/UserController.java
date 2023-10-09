package com.dns.validator.controller;

import com.dns.validator.UserPojo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class UserController {

    @PostMapping("user-pojo")
    public ResponseEntity<UserPojo> createUserPojo(@RequestBody @Valid UserPojo userPojo) {
        return ResponseEntity.ok(userPojo);
    }
}

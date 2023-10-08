package com.dns.validator;

public class App {

    public static void main(String[] args) {
        var userBuilder = UserBuilder.builder().username("username123").password("password123").build();
        var userRecord = new UserRecord("username123", "password123");

        System.out.println("validator = " + SelfValidation.validator);
    }
}

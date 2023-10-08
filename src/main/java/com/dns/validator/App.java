package com.dns.validator;

public class App {


    public static void main(String[] args) {
        var userBuilder = UserBuilder.builder().username("username123").password("password123").build();
        var userRecord = new UserRecord("username123", "password123");

        Runtime.getRuntime().addShutdownHook(createShutdownHookThread());
    }


    /**
     * Create a shutdown hook thread that will close the validationFactory.
     */
    private static Thread createShutdownHookThread() {
        return new Thread(() -> {
            System.out.println("Application shutting down, closing validationFactory = " + SelfValidation.validatorFactory);

            SelfValidation.validatorFactory.close();

            System.out.println("validationFactory closed successfully...");
            System.out.println("Hibernate validatorFactory = " + SelfValidation.validatorFactory);
            System.out.println("Hibernate validator = " + SelfValidation.validator);

        });
    }
}

package com.dns.validator;

public class App {

    public static void main(String[] args) {

        try {
            UserBuilder.builder().username("user").password("password123").build();
        } catch (Exception e) {
            System.out.println("Exception 1=" + e.getMessage());
        }
        try {
            new UserRecord("username123", "pass");
        } catch (Exception e) {
            System.out.println("Exception 2=" + e.getMessage());
        }

        Runtime.getRuntime().addShutdownHook(createShutdownHookThread());
    }


    /**
     * Create a shutdown hook thread that will close the validationFactory.
     */
    private static Thread createShutdownHookThread() {
        return new Thread(() -> {

            System.out.println("\n\n********************** CLOSE RESOURCES");
            System.out.println("Application shutting down, closing validationFactory = " + SelfValidation.validatorFactory);

            SelfValidation.validatorFactory.close();

            System.out.println("validationFactory closed successfully...");
            System.out.println("Hibernate validatorFactory = " + SelfValidation.validatorFactory);
            System.out.println("Hibernate validator = " + SelfValidation.validator);

        });
    }
}

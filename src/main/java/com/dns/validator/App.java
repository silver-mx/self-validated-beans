package com.dns.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        Runtime.getRuntime().addShutdownHook(createShutdownHookThread());
    }

    /**
     * Create a shutdown hook thread that will close the validationFactory.
     */
    private static Thread createShutdownHookThread() {
        return new Thread(() -> {

            System.out.println("\n\n********************** CLOSE RESOURCES");
            System.out.println("Application shutting down, closing validationFactory = " + SelfValidated.validatorFactory);

            SelfValidated.validatorFactory.close();

            System.out.println("validationFactory closed successfully...");
            System.out.println("Hibernate validatorFactory = " + SelfValidated.validatorFactory);
            System.out.println("Hibernate validator = " + SelfValidated.validator);

        });
    }
}

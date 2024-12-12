package com.example.ticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * The main application class for the ticket booking simulation.
 *
 * This class serves as the entry point for the Spring Boot application and
 * enables asynchronous method execution for simulating vendor and customer activities.
 *
 * @SpringBootApplication: Marks this class as a Spring Boot application, enabling
 *                         auto-configuration and component scanning.
 * @EnableAsync: Enables asynchronous method execution using Spring's `@Async` annotation.
 */
@SpringBootApplication
@EnableAsync
public class TicketBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketBookingApplication.class, args);
    }
}
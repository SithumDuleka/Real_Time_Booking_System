package com.example.ticketbooking.controller;

import com.example.ticketbooking.dto.Configuration;
import com.example.ticketbooking.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * This class defines REST API endpoints for managing simulations in a ticket booking system.
 * It interacts with the {@link TicketService} to perform simulation operations like saving configurations, starting simulations, and stopping simulations.
 */
@RestController
@RequestMapping("/api/simulation")
@CrossOrigin(origins = "http://localhost:4200")
public class SimulationController {

    /**
     * Injected instance of the {@link TicketService} used to interact with the simulation logic.
     */
    @Autowired
    private TicketService ticketService;

    /**
     * Holds the most recently saved configuration object in memory.
     */
    private Configuration savedConfiguration;

    /**
     * This endpoint saves a provided {@link Configuration} object to a JSON file and stores it in memory for future reference.
     *
     * @param config The configuration object to be saved.
     * @return A ResponseEntity object indicating success or failure with a message.
     * @throws IOException If there's an error writing the configuration to the file.
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveConfiguration(@RequestBody Configuration config) {
        try {
            savedConfiguration = config; // Save the configuration in memory

            // Save the configuration to a JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("savedConfiguration.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, savedConfiguration);

            System.out.println("Configuration saved to file and memory: " + config);
            return ResponseEntity.ok(Collections.singletonMap("message", "Configuration saved successfully."));
        } catch (IOException e) {
            System.err.println("Error saving configuration to file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to save configuration to file."));
        } catch (Exception e) {
            System.err.println("General error saving configuration: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * This endpoint starts a simulation based on the previously saved {@link Configuration}.
     * It checks if a configuration is saved and then calls the `TicketService` methods to initialize and start the simulation.
     *
     * @return A ResponseEntity object indicating success or failure with a message.
     * @throws Exception If there's an error during simulation initialization or start.
     */
    @PostMapping("/start")
    public ResponseEntity<?> startSimulation() {
        try {
            if (savedConfiguration == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "No configuration saved. Please save a configuration first."));
            }
            System.out.println("Starting simulation with configuration: " + savedConfiguration);
            ticketService.initializePool(savedConfiguration);
            ticketService.startSimulation(savedConfiguration);
            return ResponseEntity.ok(Collections.singletonMap("message", "Simulation started."));
        } catch (Exception e) {
            System.err.println("Error starting simulation: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * This endpoint stops the ongoing simulation by calling the `stopSimulation` method of the injected {@link TicketService}.
     *
     * @return A ResponseEntity object indicating success with a message.
     * @throws Exception If there's an error during stopping the simulation.
     */
    @PostMapping("/stop")
    public ResponseEntity<?> stopSimulation() {
        try {
            ticketService.stopSimulation();
            return ResponseEntity.ok(Collections.singletonMap("message", "Simulation stopped."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * This endpoint retrieves the current simulation status from the {@link TicketService}. The specific format of the returned object
     * depends on the implementation of the `getSimulationStatus` method in the service.
     *
     * @return The current simulation status as returned by the `TicketService`.
     */
    @GetMapping("/status")
    public Object getSimulationStatus() {
        return ticketService.getSimulationStatus();
    }
}
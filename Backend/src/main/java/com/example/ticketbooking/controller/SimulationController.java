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

@RestController
@RequestMapping("/api/simulation")
@CrossOrigin(origins = "http://localhost:4200")
public class SimulationController {

    @Autowired
    private TicketService ticketService;

    private Configuration savedConfiguration; // Holds the saved configuration

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
    @GetMapping("/status")
    public Object getSimulationStatus() {
        return ticketService.getSimulationStatus();
    }
}
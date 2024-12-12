package com.example.ticketbooking.service;

import com.example.ticketbooking.dto.Configuration;
import com.example.ticketbooking.model.TicketPool;
import com.example.ticketbooking.simulation.Customer;
import com.example.ticketbooking.simulation.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to manage the ticket booking simulation.
 */
@Service
public class TicketService {

    /**
     * Injected instance of the TicketPool.
     */
    @Autowired
    private TicketPool ticketPool;

    /**
     * List of vendor threads.
     */
    private final List<Thread> vendorThreads = new ArrayList<>();

    /**
     * List of customer threads.
     */
    private final List<Thread> customerThreads = new ArrayList<>();

    /**
     * Initializes the ticket pool with the given configuration.
     *
     * @param config The configuration object.
     */
    public void initializePool(Configuration config) {
        ticketPool.initialize(config.getTotalTickets(), config.getMaxTicketCapacity());
    }

    /**
     * Starts the simulation by creating and starting vendor and customer threads.
     *
     * @param config The configuration object.
     */
    public void startSimulation(Configuration config) {
        for (int i = 0; i < 5; i++) {
            String vendorName = "Vendor-" + (i + 1);
            Vendor vendor = new Vendor(ticketPool, vendorName, config.getReleaseRate());
            Thread vendorThread = new Thread(vendor);
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        for (int i = 0; i < 5; i++) {
            String customerName = "Customer-" + (i + 1);
            Customer customer = new Customer(ticketPool, customerName, config.getCustomerRetrievalRate());
            Thread customerThread = new Thread(customer);
            customerThreads.add(customerThread);
            customerThread.start();
        }
    }

    /**
     * Stops the simulation by interrupting all running threads.
     */
    public void stopSimulation() {
        vendorThreads.forEach(Thread::interrupt);
        customerThreads.forEach(Thread::interrupt);
        vendorThreads.clear();
        customerThreads.clear();
    }

    /**
     * Gets the current simulation status.
     *
     * @return A string representation of the simulation status.
     */
    public Object getSimulationStatus() {
        // Implement logic to provide detailed simulation status
        return "Simulation is currently running. Total tickets sold: " + ticketPool.getTotalSoldTickets();
    }
}
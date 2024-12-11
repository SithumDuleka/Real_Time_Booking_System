package com.example.ticketbooking.service;


import com.example.ticketbooking.dto.Configuration;
import com.example.ticketbooking.model.TicketPool;
import com.example.ticketbooking.simulation.Customer;
import com.example.ticketbooking.simulation.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketPool ticketPool;


    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();


    public void initializePool(Configuration config) {
        ticketPool.initialize(config.getTotalTickets(), config.getMaxTicketCapacity());
    }

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
//        while (!ticketPool.isTerminated()) {
//            try {
//                Thread.sleep(1000); // Check every second
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//
//        System.out.println("Stopping all operations...");
//
//        for (Thread thread : customerThreads) {
//            thread.interrupt();
//        }
//        customerThreads.clear();
//
//        for (Thread thread : vendorThreads) {
//            thread.interrupt();
//        }
//        vendorThreads.clear();
//
//        System.out.println("All threads terminated successfully.");

    }

    public void stopSimulation() {
        vendorThreads.forEach(Thread::interrupt);
        customerThreads.forEach(Thread::interrupt);
        vendorThreads.clear();
        customerThreads.clear();
    }

    public Object getSimulationStatus() {
        // Return mock status for now
        return "Simulation Status Placeholder";
    }
}
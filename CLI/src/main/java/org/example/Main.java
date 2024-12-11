package org.example;
import TicketPool.ticketPool;
import ticketbooking.Configuration.Configuration;
import ticketbooking.Configuration.ConfigurationController;
import ticketbooking.Users.Customer;
import ticketbooking.Users.Vendor;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static ArrayList<Thread> customersThreadlist=new ArrayList<>();
    public static ArrayList<Thread> VendoresThreadlist=new ArrayList<>();
    public static  ticketPool ticketPool1;
    public static boolean stopCommandReceived = false;
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.print("Enter total number of ticekts :");
        int totticket=input.nextInt();
        System.out.print("Enter ticket Release Rate     :");
        int releseRate=input.nextInt();
        System.out.print("Enter customer Retrieval Rate :");
        int customerRetrivalRate=input.nextInt();
        System.out.print("Enter Maximum ticket capacity :");
        int maxTicketCapacity=input.nextInt();

        Configuration config1=new Configuration(totticket,releseRate,customerRetrivalRate,maxTicketCapacity);
        ConfigurationController configurationController=new ConfigurationController();
        configurationController.writeAll(config1);
        ticketPool1=new ticketPool(totticket,maxTicketCapacity);

        System.out.println("Enter Commad(Start or Stop)");
        String command=input.next();
        if(command.equalsIgnoreCase("start")) {
            startVendorOperations(releseRate);
            startCustomerOperations(customerRetrivalRate);
            Thread commandListener = new Thread(() -> {
                Scanner commandInput = new Scanner(System.in);
                System.out.println("Type 'q' to stop the system manually.");
                while (!stopCommandReceived && !ticketPool1.isTerminated()) {
                    try {
                        if (System.in.available() > 0) { // Check if user input is available
                            String userInput = commandInput.nextLine();
                            if (userInput.equalsIgnoreCase("stop")) {
                                System.out.println("Stop command received.");
                                stopCommandReceived = true; // Signal manual termination
                            }
                        }
                        Thread.sleep(100); // Avoid busy-waiting
                    } catch (Exception e) {
                        System.out.println("Command listener interrupted.");
                        break;
                    }
                }
            });
            commandListener.start();

            stopOperations();


            try {
                commandListener.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Program terminated successfully.");
        }
    }

    public static void startVendorOperations(int releseRate){
        for(int i=0;i<5;i++){
            String name="Vendor-"+(i+1);
            Vendor vendor=new Vendor(ticketPool1,releseRate,name);
            Thread vendorthread=new Thread(vendor);
            VendoresThreadlist.add(vendorthread);
            vendorthread.start();


        }
    }
    public static void startCustomerOperations(int customerRetrivalRate){
        for (int i=0;i<5;i++){
            String name="Customer-"+(i+1);
            Customer customer=new Customer(ticketPool1,customerRetrivalRate,name);
            Thread customerThread =new Thread(customer);
            customersThreadlist.add(customerThread);
            customerThread.start();

        }
    }
    public static void stopOperations(){
        while (!ticketPool1.isTerminated() && !stopCommandReceived) {
            try {
                Thread.sleep(1000); // Check every second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Stopping all operations...");

        for (Thread thread : customersThreadlist) {
            thread.interrupt();
        }
        customersThreadlist.clear();

        for (Thread thread : VendoresThreadlist) {
            thread.interrupt();
        }
        VendoresThreadlist.clear();

        System.out.println("All threads terminated successfully.");
    }
}




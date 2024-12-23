package org.example;
import TicketPool.ticketPool;
import ticketbooking.Configuration.Configuration;
import ticketbooking.Configuration.ConfigurationController;
import ticketbooking.Users.Customer;
import ticketbooking.Users.Vendor;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static ArrayList<Thread> customersThreadlist=new ArrayList<>();
    public static ArrayList<Customer> customerslist=new ArrayList<>();
    public static ArrayList<Thread> VendoresThreadlist=new ArrayList<>();
    public static ArrayList<Vendor> Vendoreslist=new ArrayList<>();
    public static  ticketPool ticketPool1;
    public static boolean stopCommandReceived = false;
    public static void main(String[] args) {
        System.out.println("\u001b[31m"+"\r\n  _____                 _     _____ _      _        _   _               ____            _                  \r\n | ____|_   _____ _ __ | |_  |_   _(_) ___| | _____| |_(_)_ __   __ _  / ___| _   _ ___| |_ ___ _ __ ___   \r\n |  _| \\ \\ / / _ \\ \'_ \\| __|   | | | |/ __| |/ / _ \\ __| | \'_ \\ / _` | \\___ \\| | | / __| __/ _ \\ \'_ ` _ \\  \r\n | |___ \\ V /  __/ | | | |_    | | | | (__|   <  __/ |_| | | | | (_| |  ___) | |_| \\__ \\ ||  __/ | | | | | \r\n |_____| \\_/ \\___|_| |_|\\__|   |_| |_|\\___|_|\\_\\___|\\__|_|_| |_|\\__, | |____/ \\__, |___/\\__\\___|_| |_| |_| \r\n                                                                |___/         |___/                        \r\n"+"\u001b[0m");
        //Scanner input=new Scanner(System.in);
        int totticket=getVaildInput("Enter total number of ticekts :");
        int releseRate=getVaildInput("Enter ticket Release Rate  :");
        int customerRetrivalRate=getVaildInput("Enter Customer Retrieval Rate :");
        int maxTicketCapacity=getVaildInput("Enter Maximum ticket capacity :");



        Configuration config1=new Configuration(totticket,releseRate,customerRetrivalRate,maxTicketCapacity);
        ConfigurationController configurationController=new ConfigurationController();
        configurationController.writeAll(config1);
        ticketPool1=new ticketPool(totticket,maxTicketCapacity);

        defaultCustomers(customerRetrivalRate);
        defaultVendors(releseRate);

        Addprocess(customerRetrivalRate,releseRate);
        String command=getValidCommand("Enter Command(Start ) to Run programme :");
        if(command.equalsIgnoreCase("start")) {
            startVendorOperations(releseRate);
            startCustomerOperations(customerRetrivalRate);
            Thread commandListener = new Thread(() -> {
                Scanner commandInput = new Scanner(System.in);
                System.out.println("Type 'stop' to stop the system manually.");
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
        for(int i=0;i<Vendoreslist.size();i++){
            Thread vendorthread=new Thread(Vendoreslist.get(i));
            VendoresThreadlist.add(vendorthread);
            vendorthread.start();


        }
    }
    public static void startCustomerOperations(int customerRetrivalRate){
        for (int i=0;i<customerslist.size();i++){
            Thread customerThread =new Thread(customerslist.get(i));
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
    public static int getVaildInput(String prompt){
        Scanner input=new Scanner(System.in);
        int value=-1;
        while(value<=0){
            try {
                System.out.print(prompt);
                if (input.hasNext()) {
                    value = input.nextInt();
                    if (value <= 0) {
                        System.out.println("Value must be greater than 0.Please try again ");
                    }
                } else {
                    System.out.println("Please Enter valid input ");
                }
            }catch (InputMismatchException e){
                System.out.println("PLease enter valid input");
                input.nextLine();
            }
        }
        return value;
    }
    public static String getValidCommand(String prompt) {
        Scanner input = new Scanner(System.in);
        String command = "";
        while (!(command.equalsIgnoreCase("start") || command.equalsIgnoreCase("stop"))) {
            System.out.print(prompt);
            command = input.next();
            if (!(command.equalsIgnoreCase("start") || command.equalsIgnoreCase("stop"))) {
                System.out.println("Invalid command. Please enter either 'start' or 'stop'.");
            }
        }
        return command;
    }
    public static void Addprocess(int customerRetrivalRate,int releseRate){
        L1:while(true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("\u001b[33m");
                System.out.println("[1]Add Customer ");
                System.out.println("[2]Add Vendor ");
                System.out.println("[3]Close menu");
                System.out.print("Enter an option ");
                System.out.println("\u001b[0m");
                int num = input.nextInt();
                switch (num) {
                    case 1:
                        AddCustomer(customerRetrivalRate);
                        break;
                    case 2:
                        addVendor(releseRate);
                        break;
                    case 3:
                        break L1;
                    default:
                        System.out.println("Please select valid option");
                }
            }catch (InputMismatchException e){
                System.out.println("Please Enter valid input !!!");
            }

        }
    }

    private static void addVendor(int relaseRate)
    {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter Vendor name :");
        String name=input.nextLine();
        Vendor n1=new Vendor(ticketPool1,relaseRate,name);
        Vendoreslist.add(n1);
    }

    public static void AddCustomer(int customerRetrivalRate){
        Scanner input=new Scanner(System.in);
        System.out.println("Enter Customer name :");
        String name=input.nextLine();
        Customer n1=new Customer(ticketPool1,customerRetrivalRate,name);
        customerslist.add(n1);

    }
    public static void defaultCustomers(int customerRetirvarate){
        for(int i=0;i<5;i++){
            String name="Customer-"+(i+1);
            Customer customer=new Customer(ticketPool1,customerRetirvarate,name);
            customerslist.add(customer);
        }
    }
    public static void defaultVendors(int releaserate){
        for(int i=0;i<5;i++){
            String name="Vendor-"+(i+1);
            Vendor vendor=new Vendor(ticketPool1,releaserate,name);
            Vendoreslist.add(vendor);
        }
    }

}




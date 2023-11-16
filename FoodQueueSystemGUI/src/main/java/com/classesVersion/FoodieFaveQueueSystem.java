package com.classesVersion;

import com.example.foodqueuesystemgui.FoodQueueApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FoodieFaveQueueSystem {
//    view console menu
    static String menu = """
                
                *****************************************************
                \u001B[36;1m\u001B[3m\u001B[40m               Foodies Fave Food center              \u001B[0m          \s
                -----------------------------------------------------
                100 or VFQ: View all Queues.
                101 or VEQ: View all Empty Queues.
                102 or ACQ: Add customer to a Queue.
                103 or RCQ: Remove a customer from a Queue. (From a specific location)
                104 or PCQ: Remove a served customer.
                105 or VCS: View Customers Sorted in alphabetical order (Do not use library sort routine)
                106 or SPD: Store Program Data into file.
                107 or LPD: Load Program Data from file.
                108 or STK: View Remaining burgers Stock.
                109 or AFS: Add burgers to Stock.
                110 or IFQ: Income of each queue.
                112 or GUI: View the status of the queues by GUI.
                999 or EXT: Exit the Program.
                -----------------------------------------------------
                """;

//    initializes Scanner objects called input
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
//        The first set of square brackets [] indicates the rows, and the second set indicates the columns
        for (int i = 0; i < FoodQueue.queue1.length; i++) {
//            Initialize the elements of the current row with a new Customer object
            FoodQueue.queue1[i][0] = new Customer("X", "", 0);
            FoodQueue.queue1[i][1] = new Customer("", "", 0);
        }

        for (int i = 0; i < FoodQueue.queue2.length; i++) {
            FoodQueue.queue2[i][0] = new Customer("X", "", 0);
            FoodQueue.queue2[i][1] = new Customer("", "", 0);
        }

        for (int i = 0; i < FoodQueue.queue3.length; i++) {
            FoodQueue.queue3[i][0] = new Customer("X", "", 0);
            FoodQueue.queue3[i][1] = new Customer("", "", 0);
        }

//        a console menu for operator access
        while (true) {
            System.out.println(menu);
            System.out.print("Please select a option: ");
            String option = input.next();

            switch (option) {
                case "100", "VFQ" -> {
//                    view all queues
                    System.out.println();
                    FoodQueue.viewAllQueues();
                }

                case "101", "VEQ" -> {
//                    view all empty queues
                    System.out.println();
                    FoodQueue.viewAllEmptyQueues();
                }

                case "102", "ACQ" -> {
//                    add customer to a queue.
                    System.out.println();
                    try {
                        FoodQueue.addCustomer();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "103", "RCQ" -> {
//                    remove a customer from a queue. (from a specific location)
                    System.out.println();
                    try {
                        FoodQueue.removeCustomer();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "104", "PCQ" -> {
//                    remove a served customer
                    System.out.println();
                    try {
                        FoodQueue.removeServedCustomer();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "105", "VCS" -> {
//                    view customers sorted in alphabetical order
                    System.out.println();
                    try {
                        FoodQueue.viewCustomerSorted();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "106", "SPD" -> {
//                    store program data into file
                    System.out.println();
                    FoodQueue.storeProgramData();
                }

                case "107", "LPD" -> {
//                    load program data from file
                    System.out.println();
                    FoodQueue.loadProgramData();
                }

                case "108", "STK" -> {
//                    view remaining burgers stock
                    System.out.println();
                    FoodQueue.viewRemainingBurgers();
                }

                case "109", "AFS" -> {
//                    add burgers to stock
                    System.out.println();
                    try {
                        FoodQueue.addBurgers();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "110", "IFQ" -> {
//                    print the income of each queue
                    System.out.println();
                    FoodQueue.incomeOfEachQueue();
                }

                case "112", "GUI" -> {
//                    Launch a GUI for the operator to view the status of the queues
                    System.out.println();
                    try {
                        FoodQueueApplication.launch(FoodQueueApplication.class, args);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "999", "EXT" -> {
//                    exit the Program
                    System.out.println("Thank you for using food queue operating system.");
//                    terminate the program if operator enter "999", "EXT"
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. please try again.");
            }

//            using conditions for print warning messages
            if (FoodQueue.burgers == 10){
                System.out.println();
                String warning = "\u001B[33;1mWARNING! \u001B[0m" + "\nthe stock reaches a value of 10 burgers.";
                System.out.println(warning);
            } else if (FoodQueue.burgers < 10) {
                System.out.println();
                String warningHigh = "\u001B[31;5mWARNING! \u001B[0m" + "\nthe stock reaches less than 10 burgers.";
                System.out.println(warningHigh);
                System.out.println("please add burgers to stock.");
            }
        }

    }

}

package com.arraysVersion;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FoodieFaveQueueSystem {
//    Initializes 2D array of strings
//    The first set of square brackets [] indicates the rows, and the second set indicates the columns
    static String[][] queue1 = {{"X", ""}, {"X", ""}};
    static String[][] queue2 = {{"X", ""}, {"X", ""}, {"X", ""}};
    static String[][] queue3 = {{"X", ""}, {"X", ""}, {"X", ""}, {"X", ""}, {"X", ""}};

//    Initializes Scanner objects called input and names
    static Scanner input = new Scanner(System.in);
    static Scanner names = new Scanner(System.in);

//    Initial stock of burgers
    static int burgers = 50;

    static String printCashiers = """
            *****************
            *    Cashiers   *
            *****************
            """;

    public static void main(String[] args) {
        while (true) {
//            a console menu for operator access
            FoodieFaveQueueSystem.displayMenu();
            System.out.print("\nPlease select a option: ");
            String option = input.next();

            switch (option) {

                case "100", "VFQ" -> {
//                    view all queues
                    System.out.println();
                    FoodieFaveQueueSystem.viewAllQueues();
                }

                case "101", "VEQ" -> {
//                    view all empty queues
                    System.out.println();
                    FoodieFaveQueueSystem.viewAllEmptyQueues();
                }

                case "102", "ACQ" -> {
//                    add customer to a queue.
                    System.out.println();
                    try {
                        FoodieFaveQueueSystem.addCustomer();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "103", "RCQ" -> {
//                    remove a customer from a queue. (from a specific location)
                    System.out.println();
                    try {
                        FoodieFaveQueueSystem.removeCustomer();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "104", "PCQ" -> {
//                    remove a served customer
                    System.out.println();
                    try {
                        FoodieFaveQueueSystem.removeServedCustomer();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "105", "VCS" -> {
//                    view customers sorted in alphabetical order
                    System.out.println();
                    try {
                        FoodieFaveQueueSystem.viewCustomerSorted();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        FoodieFaveQueueSystem.input.next();
                    }
                }

                case "106", "SPD" -> {
//                    store program data into file
                    System.out.println();
                    FoodieFaveQueueSystem.storeProgramData();
                }

                case "107", "LPD" -> {
//                    load program data from file
                    System.out.println();
                    FoodieFaveQueueSystem.loadProgramData();
                }

                case "108", "STK" -> {
//                    view remaining burgers stock
                    System.out.println();
                    FoodieFaveQueueSystem.viewRemainingBurgers();
                }

                case "109", "AFS" -> {
//                    add burgers to stock
                    System.out.println();
                    try {
                        FoodieFaveQueueSystem.addBurgers();
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
            if (burgers == 10){
                System.out.println();
                String warning = "\u001B[33;1mWARNING! \u001B[0m" + "\nthe stock reaches a value of 10 burgers.";
                System.out.println(warning);
            } else if (burgers < 10) {
                System.out.println();
                String warningHigh = "\u001B[31;5mWARNING! \u001B[0m" + "\nthe stock reaches less than 10 burgers.";
                System.out.println(warningHigh);
                System.out.println("please add burgers to stock.");
            }
        }

    }

//    view console menu
    private static void displayMenu() {
        String menu = """
                
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
                999 or EXT: Exit the Program.
                -----------------------------------------------------
                """;
        System.out.print(menu);
    }

//    view all queues
    private static void viewAllQueues() {
        System.out.print(printCashiers);

//        calculates the maximum length among three arrays
        int maxLength = Math.max(Math.max(queue1.length, queue2.length), queue3.length);

//        using ternary operator for replace if-else statements
        for (int i = 0; i < maxLength; i++) {
            if (i < queue1.length) {
//                if the condition is true, the value of the ternary expression is "X" and false, "O"
                System.out.print(queue1[i][0].equals("X") ? "X" : "O");
            }
//        this line prints two tabs after printing the value from array
            System.out.print("\t\t");
            if (i < queue2.length) {
                System.out.print(queue2[i][0].equals("X") ? "X" : "O");
            }
            System.out.print("\t\t");
            if (i < queue3.length) {
                System.out.print(queue3[i][0].equals("X") ? "X" : "O");
            }
            System.out.println();
        }
        System.out.println("\nX – Not Occupied      O – Occupied");

    }

//    view all empty queues
    private static void viewAllEmptyQueues() {
        System.out.print(printCashiers);
        int maxLength = Math.max(Math.max(queue1.length, queue2.length), queue3.length);

//        using ternary operator for replace if-else statements
        for (int i = 0; i < maxLength; i++) {
            if (i < queue1.length) {
//                if the condition is true, the value of the ternary expression is "X" and false, "" (null)
                System.out.print(queue1[i][0].equals("X") ? "X" : "");
            }
            System.out.print("\t\t");
            if (i < queue2.length) {
                System.out.print(queue2[i][0].equals("X") ? "X" : "");
            }
            System.out.print("\t\t");
            if (i < queue3.length) {
                System.out.print(queue3[i][0].equals("X") ? "X" : "");
            }
            System.out.println();
        }
    }

//    add customer to a queue
    private static void addCustomer() {
        System.out.print("Enter Queue: ");
        int queue = input.nextInt();
        System.out.print("Enter Name: ");
        String name = names.next();

        switch (queue) {
            case 1 -> {
                for (int i = 0; i < queue1.length; i++) {
//                        if the condition is true, "O" replace "X" and 'name' store in column two
                    if (queue1[i][0].equals("X")) {
                        queue1[i][0] = "O";
                        queue1[i][1] = name;
                        System.out.println(name + " added to the queue 1 successfully");
                        return;
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < queue2.length; i++) {
                    if (queue2[i][0].equals("X")) {
                        queue2[i][0] = "O";
                        queue2[i][1] = name;
                        System.out.println(name + " added to the queue 2 successfully");
                        return;
                    }
                }
            }
            case 3 -> {
                for (int i = 0; i < queue3.length; i++) {
                    if (queue3[i][0].equals("X")) {
                        queue3[i][0] = "O";
                        queue3[i][1] = name;
                        System.out.println(name + " added to the queue 3 successfully");
                        return;
                    }
                }
            }
            default -> {
                System.out.println("Invalid queue number. please try again.");
                return;
            }
        }
//            display “Queue is full”
        System.out.println("Queue" + queue + " is full.");
    }

//    remove a customer from a queue. (from a specific location)
    private static void removeCustomer(){
        System.out.print("Enter Queue: ");
        int queue = input.nextInt();
        System.out.print("Enter Row: ");
        int row = input.nextInt();
        int i = row - 1;

        switch (queue) {
            case 1 -> {
//                    if the condition is true, "X" replace "O", 'name' remove from column two and store "" (null)
                if (queue1[i][0].equals("O")) {
                    queue1[i][0] = "X";
                    queue1[i][1] = "";
                    System.out.println("Row " + row + " of Queue 1 customer removed.");
                } else {
                    System.out.println("Row " + row + " of Queue 1 is empty.");
                }
            }
            case 2 -> {
                if (queue2[i][0].equals("O")) {
                    queue2[i][0] = "X";
                    queue2[i][1] = "";
                    System.out.println("Row " + row + " of Queue 2 customer removed.");
                } else {
                    System.out.println("Row " + row + " of Queue 2 is empty.");
                }
            }
            case 3 -> {
                if (queue3[i][0].equals("O")) {
                    queue3[i][0] = "X";
                    queue2[i][1] = "";
                    System.out.println("Row " + row + " of Queue 3 customer removed.");
                } else {
                    System.out.println("Row " + row + " of Queue 3 is empty.");
                }
            }
            default -> System.out.println("Invalid queue number. please try again.");
        }
    }
//    remove a served customer
    private static void removeServedCustomer(){
        System.out.print("Enter Queue: ");
        int queue = input.nextInt();

        switch (queue) {
            case 1 -> {
                for (int i = 0; i < queue1.length; i++) {
                    if (queue1[i][0].equals("O")) {
                        queue1[i][0] = "X";
                        queue1[i][1] = "";
                        System.out.println("Queue 1 served customer removed.");
//                            assume each customer is served 5 burgers
                        burgers -= 5;
                        return;
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < queue2.length; i++) {
                    if (queue2[i][0].equals("O")) {
                        queue2[i][0] = "X";
                        queue2[i][1] = "";
                        System.out.println("Queue 2 served customer removed.");
                        burgers -= 5;
                        return;
                    }
                }
            }
            case 3 -> {
                for (int i = 0; i < queue3.length; i++) {
                    if (queue3[i][0].equals("O")) {
                        queue3[i][0] = "X";
                        queue3[i][1] = "";
                        System.out.println("Queue 3 served customer removed.");
                        burgers -= 5;
                        return;
                    }
                }
            }
            default -> {
                System.out.println("Invalid queue number. please try again.");
                return;
            }
        }
        System.out.println("Queue " + queue + " all rows are empty.");
    }

//    view customers sorted in alphabetical order
    private static String[][] selectedQueue;
    private static void viewCustomerSorted() {

        System.out.print("Select a queue for view customers sorted: ");

        int queue = input.nextInt();
        switch (queue) {
            case 1 -> {
                System.out.println("Queue 1:");
                selectedQueue = queue1;
            }
            case 2 -> {
                System.out.println("Queue 2:");
                selectedQueue = queue2;
            }
            case 3 -> {
                System.out.println("Queue 3:");
                selectedQueue = queue3;
            }
            default -> {
                System.out.println("Invalid queue number. please try again.");
                return;
            }
        }

//        Reference: https://beginnersbook.com/2019/04/java-program-to-perform-bubble-sort-on-strings/

        int n = selectedQueue.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (selectedQueue[j][1].compareTo(selectedQueue[j + 1][1]) > 0) {
                    // Swap queue1[j] and queue1[j+1]
                    String temp = selectedQueue[j][1];
                    selectedQueue[j][1] = selectedQueue[j + 1][1];
                    selectedQueue[j + 1][1] = temp;
                }
            }
        }
//        print the sorted array
        for (int i = 0; i < n; i++) {
            if (!selectedQueue[i][1].equals("")) {
                System.out.println(selectedQueue[i][1]);
            }
        }
    }

//    store program data into file
    static String filePath = "foodQueue.txt";
    private static void storeProgramData(){
//        Reference: https://www.w3schools.com/java/java_files.asp
        try {
//            creates a new File object named programData and initializes it with the file path "foodQueue.txt"
            File programData = new File(filePath);
            if (programData.createNewFile()){
                System.out.println("File created: " + programData.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e){
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

//        writing program data to the file specified by the filePath variable
        try(BufferedWriter programWrite = new BufferedWriter(new FileWriter(filePath))) {
            if (!queue1[0][0].equals("X") || !queue1[1][0].equals("X")){
                programWrite.write("Queue 1:");
                programWrite.newLine();
                for (String[] strings : queue1) {
                    programWrite.write(strings[1]);
                    programWrite.newLine();
                }

            } else {
                programWrite.write("Queue 1 is empty.");
            }
            programWrite.newLine();

            if (!queue2[0][0].equals("X") || !queue2[1][0].equals("X") || !queue2[2][0].equals("X")){
                programWrite.write("Queue 2:");
                programWrite.newLine();
                for (String[] strings : queue2) {
                    programWrite.write(strings[1]);
                    programWrite.newLine();
                }
            } else {
                programWrite.write("Queue 2 is empty.");
            }
            programWrite.newLine();

            if (!queue3[0][0].equals("X") || !queue3[1][0].equals("X") || !queue3[2][0].equals("X") || !queue3[3][0].equals("X") || !queue3[4][0].equals("X")){
                programWrite.write("Queue 3:");
                programWrite.newLine();
                for (String[] strings : queue3) {
                    programWrite.write(strings[1]);
                    programWrite.newLine();
                }
            } else {
                programWrite.write("Queue 3 is empty.");
            }
            System.out.println("And successfully stored program data into the file.");
        } catch (IOException e){
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }

//    load program data from file
    private static void loadProgramData(){
        try {
            File programData = new File(filePath);
            Scanner programLoad = new Scanner(programData);
            while (programLoad.hasNextLine()){
                String data = programLoad.nextLine();
                System.out.println(data);
            }
            programLoad.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

//    view remaining burgers stock
    private static void viewRemainingBurgers(){

        System.out.println("Remaining burgers in stock: " + burgers);
    }

//    add burgers to stock
    private static void addBurgers(){
        System.out.print("How many burgers to add to stock: ");
        int burgersAdd = input.nextInt();
        burgers += burgersAdd;
    }

}

/*
*           Name: Rikas Ilamdeen            IIT_studentID: 20223275             WestminsterID: W1987544
* */
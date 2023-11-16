package com.classesVersion;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


public class FoodQueue {
//    Initialization of two-dimensional arrays
    public static Customer[][] queue1 = new Customer[2][2];
    public static Customer[][] queue2 = new Customer[3][2];
    public static Customer[][] queue3 = new Customer[5][2];

//    Initialization of an ArrayList named "waitingQueue"
    private static List<Customer> waitingQueue = new ArrayList<>();
//    "waitingQueue" is an ArrayList that will hold objects of the "Customer" type

    public static String printCashiers = """
            *****************
            *    Cashiers   *
            *****************
            """;
//    Initial stock of burgers
    static int burgers = 50;
    static int totNoOfBurgersRequired = 0;
//    Initial the count of burgers purchased from each queue
    static int burgersPurchasedQueue1 = 0;
    static int burgersPurchasedQueue2 = 0;
    static int burgersPurchasedQueue3 = 0;
//    Initial the income of each queue
    static int incomeQueue1 = 0;
    static int incomeQueue2 = 0;
    static int incomeQueue3 = 0;
    static String filePath = "foodQueue.txt";

//    view all queues
    static void viewAllQueues() {
        System.out.print(printCashiers);
//        calculates the maximum length among three arrays
        int maxLength = Math.max(Math.max(queue1.length, queue2.length), queue3.length);

//        using ternary operator for replace if-else statements
        for (int i = 0; i < maxLength; i++) {
            if (i < queue1.length) {
//                if the condition is true, the value of the ternary expression is "X" and false, "O"
                System.out.print(queue1[i][0].getFirstName().equals("X") ? "X" : "O");
            }
//            this line prints two tabs after printing the value from array
            System.out.print("\t\t");
            if (i < queue2.length) {
                System.out.print(queue2[i][0].getFirstName().equals("X") ? "X" : "O");
            }
            System.out.print("\t\t");
            if (i < queue3.length) {
                System.out.print(queue3[i][0].getFirstName().equals("X") ? "X" : "O");
            }
            System.out.println();
        }
        System.out.println("\nX – Not Occupied      O – Occupied");

    }

//    view all empty queues
    static void viewAllEmptyQueues() {
        System.out.print(printCashiers);
        int maxLength = Math.max(Math.max(queue1.length, queue2.length), queue3.length);

//        using ternary operator for replace if-else statements
        for (int i = 0; i < maxLength; i++) {
            if (i < queue1.length) {
//                if the condition is true, the value of the ternary expression is "X" and false, "" (null)
                System.out.print(queue1[i][0].getFirstName().equals("X") ? "X" : "");
            }
            System.out.print("\t\t");
            if (i < queue2.length) {
                System.out.print(queue2[i][0].getFirstName().equals("X") ? "X" : "");
            }
            System.out.print("\t\t");
            if (i < queue3.length) {
                System.out.print(queue3[i][0].getFirstName().equals("X") ? "X" : "");
            }
            System.out.println();
        }
    }

    private static int findMinQueueIndex(Customer[][][] queues) {
        int minQueueIndex = 0;
        int minLength = queues[0].length;
        for (int i = 1; i < queues.length; i++) {
            if (queues[i].length < minLength) {
                minLength = queues[i].length;
                minQueueIndex = i;
            }
        }
        return minQueueIndex;
    }

//    add customer to a queue
    public static void addCustomer() {
        System.out.print("Enter the first name: ");
        String firstName = FoodieFaveQueueSystem.input.next();
        System.out.print("Enter the second name: ");
        String secondName = FoodieFaveQueueSystem.input.next();
        System.out.print("Enter the number of burgers required: ");
        int noOfBurgersRequired = FoodieFaveQueueSystem.input.nextInt();

        Customer[][][] queues = {queue1, queue2, queue3};
//        Start with the queue that has the minimum length
        int queueIndex = findMinQueueIndex(queues);
        int row = 0;

        while (row < queues[2].length) {
            Customer[][] selectedQueue = queues[queueIndex];

            if (row < selectedQueue.length && selectedQueue[row][0].getFirstName().equals("X") && (totNoOfBurgersRequired + noOfBurgersRequired <= burgers) && noOfBurgersRequired <= burgers) {
//                If the condition is true, "O" replace "X"
                selectedQueue[row][0].setFirstName("O");
//                "first name", "second name" and "burgers required" store in column two
                selectedQueue[row][1].setFirstName(firstName);
                selectedQueue[row][1].setSecondName(secondName);
                selectedQueue[row][0].setNoOfBurgersRequired(noOfBurgersRequired);
                selectedQueue[row][1].setNoOfBurgersRequired(noOfBurgersRequired);
                System.out.println(firstName + " " + secondName + " added to queue " + (queueIndex + 1) + " successfully");
                totNoOfBurgersRequired += noOfBurgersRequired;
                return;
            }
//            Move to the next queue
            queueIndex++;

            if (queueIndex == queues.length) {
                queueIndex = 0;
//                Move to the next row
                row++;
            }
        }
        if (noOfBurgersRequired > burgers || (totNoOfBurgersRequired + noOfBurgersRequired > burgers)) {
            System.out.println("no stock!");
        } else {
            System.out.println("All queues are full. Adding to the waiting list...");
//            add customer to waiting list
            addToWaitingList(firstName, secondName, noOfBurgersRequired);
            System.out.println(firstName + " " + secondName + " added to the waiting list.");
        }
    }

//    add customer to waiting list
    private static void addToWaitingList(String firstName, String secondName, int noOfBurgersRequired) {
        waitingQueue.add(new Customer(firstName, secondName, noOfBurgersRequired));
    }

//    remove a customer from a queue. (from a specific location)
    static void removeCustomer() {
        System.out.print("Enter Queue: ");
        int queue = FoodieFaveQueueSystem.input.nextInt();
        System.out.print("Enter Row: ");
        int row = FoodieFaveQueueSystem.input.nextInt();
        int i = row - 1;

        switch (queue) {
            case 1 -> {
                try {
                    //                    if the condition is true, "X" replace "O", 'name' remove from column two and store "" (null)
                    if (queue1[i][0].getFirstName().equals("O")) {
                        queue1[i][0].setFirstName("X");
                        FoodQueue.queue1[i][1] = new Customer("", "", 0);
                        System.out.println("Row " + row + " of Queue 1 customer removed.");
                    } else {
                        System.out.println("Row " + row + " of Queue 1 is empty.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid row number. please try again.");
                }
            }
            case 2 -> {
                try {
                    if (queue2[i][0].getFirstName().equals("O")) {
                        queue2[i][0].setFirstName("X");
                        FoodQueue.queue2[i][1] = new Customer("", "", 0);
                        System.out.println("Row " + row + " of Queue 2 customer removed.");
                    } else {
                        System.out.println("Row " + row + " of Queue 2 is empty.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid row number. please try again.");
                }
            }
            case 3 -> {
                try {
                    if (queue3[i][0].getFirstName().equals("O")) {
                        queue3[i][0].setFirstName("X");
                        FoodQueue.queue3[i][1] = new Customer("", "", 0);
                        System.out.println("Row " + row + " of Queue 3 customer removed.");
                    } else {
                        System.out.println("Row " + row + " of Queue 3 is empty.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid row number. please try again.");
                }
            }
            default -> System.out.println("Invalid queue number. please try again.");
        }

    }

//    remove a served customer
    static void removeServedCustomer() {
        System.out.print("Enter Queue: ");
        int queue = FoodieFaveQueueSystem.input.nextInt();

        switch (queue) {
            case 1 -> {
                for (int i = 0; i < queue1.length; i++) {
                    if (queue1[i][0].getFirstName().equals("O")) {
                        queue1[i][0].setFirstName("X");
                        FoodQueue.queue1[i][1] = new Customer("", "", 0);
                        System.out.println("Queue 1 served customer removed.");
                        burgers -= queue1[i][0].getNoOfBurgersRequired();
                        burgersPurchasedQueue1 += queue1[i][0].getNoOfBurgersRequired();
                        totNoOfBurgersRequired -= queue1[i][0].getNoOfBurgersRequired();

                        if (!waitingQueue.isEmpty()) {
                            Customer nextCustomer = waitingQueue.remove(0);
                            enqueueCustomerToQueue(nextCustomer, queue1);
                            System.out.println("Next customer in the waiting list added to Queue 1");
                        }
                        return;
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < queue2.length; i++) {
                    if (queue2[i][0].getFirstName().equals("O")) {
                        queue2[i][0].setFirstName("X");
                        FoodQueue.queue2[i][1] = new Customer("", "", 0);
                        System.out.println("Queue 2 served customer removed.");
                        burgers -= queue2[i][0].getNoOfBurgersRequired();
                        burgersPurchasedQueue2 += queue2[i][0].getNoOfBurgersRequired();
                        totNoOfBurgersRequired -= queue2[i][0].getNoOfBurgersRequired();

                        if (!waitingQueue.isEmpty()) {
                            Customer nextCustomer = waitingQueue.remove(0);
                            enqueueCustomerToQueue(nextCustomer, queue2);
                            System.out.println("Next customer in the waiting list added to Queue 1");
                        }
                        return;
                    }
                }
            }
            case 3 -> {
                for (int i = 0; i < queue3.length; i++) {
                    if (queue3[i][0].getFirstName().equals("O")) {
                        queue3[i][0].setFirstName("X");
                        FoodQueue.queue3[i][1] = new Customer("", "", 0);
                        System.out.println("Queue 3 served customer removed.");
                        burgers -= queue3[i][0].getNoOfBurgersRequired();
                        burgersPurchasedQueue3 += queue3[i][0].getNoOfBurgersRequired();
                        totNoOfBurgersRequired -= queue3[i][0].getNoOfBurgersRequired();
                        if (!waitingQueue.isEmpty()) {
                            Customer nextCustomer = waitingQueue.remove(0);
                            enqueueCustomerToQueue(nextCustomer, queue3);
                            System.out.println("Next customer in the waiting list added to Queue 1");
                        }
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

    private static void enqueueCustomerToQueue(Customer customer, Customer[][] queue) {
        for (int i = 0; i < queue.length; i++) {
            if (queue[i][0].getFirstName().equals("X")) {
                queue[i][0].setFirstName("O");
                queue[i][1].setFirstName(customer.getFirstName());
                queue[i][1].setSecondName(customer.getSecondName());
                queue[i][0].setNoOfBurgersRequired(customer.getNoOfBurgersRequired());
                return;
            }
        }
    }

//    view customers sorted in alphabetical order
    static void viewCustomerSorted() {
//        Reference: https://riptutorial.com/java/example/10693/sorting-a-list-using-comparable-t--or-a-comparator-t-

//        Create a custom comparator for sorting by first name and last name
        Comparator<Customer[]> customerName = Comparator.comparing((Customer[] c) -> c[1].getFirstName())
                .thenComparing((Customer[] c) -> c[1].getSecondName());

        System.out.print("Select a queue for view customers sorted: ");
        int queue = FoodieFaveQueueSystem.input.nextInt();
        switch (queue) {
            case 1 -> {
//                Sort queue1
                Arrays.sort(queue1, customerName);
                System.out.println("Queue 1:");
                for (Customer[] row : queue1) {
                    if (!row[1].equals("")) {
                        System.out.println(row[1].getFirstName() + " " + row[1].getSecondName());
                    }
                }
            }
            case 2 -> {
//                Sort queue2
                Arrays.sort(queue2, customerName);
                System.out.println("Queue 2:");
                for (Customer[] row : queue2) {
                    if (!row[1].equals("")) {
                        System.out.println(row[1].getFirstName() + " " + row[1].getSecondName());
                    }
                }
            }
            case 3 -> {
//                Sort queue3
                Arrays.sort(queue3, customerName);
                System.out.println("Queue 3:");
                for (Customer[] row : queue3) {
                    if (!row[1].equals("")) {
                        System.out.println(row[1].getFirstName() + " " + row[1].getSecondName());
                    }
                }
            }
            default -> {
                System.out.println("Invalid queue number. please try again.");
                return;
            }
        }
    }

//    store program data into file
    static void storeProgramData() {
//        Reference: https://www.w3schools.com/java/java_files.asp

//        try {
//            creates a new File object named programData and initializes it with the file path "foodQueue.txt"
            File programData = new File(filePath);
//            if (programData.createNewFile()) {
//                System.out.println("File created: " + programData.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred while creating the file.");
//            e.printStackTrace();
//        }

// Writing program data to the file specified by the filePath variable
        try (BufferedWriter programWrite = new BufferedWriter(new FileWriter(filePath))) {
            if (!queue1[0][0].getFirstName().equals("X") || !queue1[1][0].getFirstName().equals("X")) {
                programWrite.write("Queue 1:");
                programWrite.newLine();
                for (Customer[] row : queue1) {
                    if (!row[1].equals("")) {
                        programWrite.write(row[1].getFirstName() + " " + row[1].getSecondName());
                        programWrite.newLine();
                    }
                }
            } else {
                programWrite.write("Queue 1 is empty.");
            }
            programWrite.newLine();

            if (!queue2[0][0].getFirstName().equals("X") || !queue2[1][0].getFirstName().equals("X") || !queue2[2][0].getFirstName().equals("X")) {
                programWrite.write("Queue 2:");
                programWrite.newLine();
                for (Customer[] row : queue2) {
                    if (!row[1].equals("")) {
                        programWrite.write(row[1].getFirstName() + " " + row[1].getSecondName());
                        programWrite.newLine();
                    }
                }
            } else {
                programWrite.write("Queue 2 is empty.");
            }
            programWrite.newLine();

            if (!queue3[0][0].getFirstName().equals("X") || !queue3[1][0].getFirstName().equals("X") || !queue3[2][0].getFirstName().equals("X") || !queue3[3][0].getFirstName().equals("X") || !queue3[4][0].getFirstName().equals("X")) {
                programWrite.write("Queue 3:");
                programWrite.newLine();
                for (Customer[] row : queue3) {
                    if (!row[1].equals("")) {
                        programWrite.write(row[1].getFirstName() + " " + row[1].getSecondName());
                        programWrite.newLine();
                    }
                }
            } else {
                programWrite.write("Queue 3 is empty.");
            }
            System.out.println("Successfully stored program data into the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }

//    load program data from file
    static void loadProgramData() {
        try {
            File programData = new File(filePath);
            Scanner programLoad = new Scanner(programData);
            while (programLoad.hasNextLine()) {
                String data = programLoad.nextLine();
                System.out.println(data);
            }
            programLoad.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

//    view remaining burgers stock
    static void viewRemainingBurgers() {
        System.out.println("Remaining burgers in stock: " + burgers);
        System.out.println("Remaining orders of burgers: " + totNoOfBurgersRequired);
    }

//    add burgers to stock
    static void addBurgers() {
//        add burgers to stock
        System.out.print("How many burgers to add to stock: ");
        int burgersAdd = FoodieFaveQueueSystem.input.nextInt();
        burgers += burgersAdd;
        System.out.println(burgersAdd + " burgers added to stock.");
    }

//    print the income of each queue
    static void incomeOfEachQueue() {
        incomeQueue1 = burgersPurchasedQueue1 * 650;
        System.out.println("Income of queue 1: $" + incomeQueue1);
        System.out.println();
        incomeQueue2 = burgersPurchasedQueue2 * 650;
        System.out.println("Income of queue 2: $" + incomeQueue2);
        System.out.println();
        incomeQueue3 = burgersPurchasedQueue3 * 650;
        System.out.println("Income of queue 3: $" + incomeQueue3);

        int totalIncome = incomeQueue1 + incomeQueue2 + incomeQueue3;
        System.out.println("\nTotal income: $" + totalIncome);

    }

//    GUI for the operator to view the status of the queues
//    Search the details of a customer
    public Customer searchCustomer(String searchTerm) {
//        Iterate through the queues and waitingQueue to search for the customer
        for (Customer[] queue : queue1) {
            for (Customer customer : queue) {
                if (customer != null && (customer.getFirstName().equalsIgnoreCase(searchTerm) || customer.getSecondName().equalsIgnoreCase(searchTerm))) {
                    return customer;
                }
            }
        }
        for (Customer[] queue : queue2) {
            for (Customer customer : queue) {
                if (customer != null && (customer.getFirstName().equalsIgnoreCase(searchTerm) || customer.getSecondName().equalsIgnoreCase(searchTerm))) {
                    return customer;
                }
            }
        }
        for (Customer[] queue : queue3) {
            for (Customer customer : queue) {
                if (customer != null && (customer.getFirstName().equalsIgnoreCase(searchTerm) || customer.getSecondName().equalsIgnoreCase(searchTerm))) {
                    return customer;
                }
            }
        }

//        Search in the waitingQueue list
        for (Customer customer : waitingQueue) {
            if (customer != null && (customer.getFirstName().equalsIgnoreCase(searchTerm) || customer.getSecondName().equalsIgnoreCase(searchTerm))) {
                return customer;
            }
        }
        return null;
    }

//    The customer’s details who are waiting in the food queue along with the customers in the waiting queue
    public List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();

//        Iterate through the food queues
        for (int i = 0; i < queue1.length; i++) {
            if (queue1[i][1] != null && !queue1[i][1].getFirstName().equals("X")) {
                customerList.add(queue1[i][1]);
            }
        }

        for (int i = 0; i < queue2.length; i++) {
            if (queue2[i][1] != null && !queue2[i][1].getFirstName().equals("X")) {
                customerList.add(queue2[i][1]);
            }
        }

        for (int i = 0; i < queue3.length; i++) {
            if (queue3[i][1] != null && !queue3[i][1].getFirstName().equals("X")) {
                customerList.add(queue3[i][1]);
            }
        }
        return customerList;
    }

    public List<Customer> getWaitingQueue() {
        return waitingQueue;
    }

}

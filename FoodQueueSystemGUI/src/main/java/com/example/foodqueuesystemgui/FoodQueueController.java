package com.example.foodqueuesystemgui;

import com.classesVersion.Customer;
import com.classesVersion.FoodQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TableCell;
import java.util.List;

public class FoodQueueController {
//    Declare the JavaFX objects
    public Text heading;
    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Text customerDetailsText;

    private FoodQueue foodQueue;

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<String, String> queues;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> secondNameColumn;

    @FXML
    private TableColumn<Customer, Integer> burgersColumn;

//    Initialize GUI for the operator to view the status of the queues
//    Reference: https://www.youtube.com/watch?v=fnU1AlyuguE&t=361s (Table view)
    @FXML
    public void initialize() {
        System.out.println("Controller initialized");
        foodQueue = new FoodQueue();

//        the customer’s details who are waiting in the food queue along with the customers in the waiting queue
        queues.setCellFactory(column -> {
            return new TableCell<String, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
//                    Heading the queues in the table
                    if (getIndex() == 0) {
                        setText("Queue 1");
                    } else if (getIndex() == 2) {
                        setText("Queue 2");
                    } else if (getIndex() == 5) {
                        setText("Queue 3");
                    } else if (getIndex() == 10) {
                        setText("Waiting Queue");
                    }
                    else {
                        setText(item);
                    }
                }
            };
        });

//        Initialize table columns
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        burgersColumn.setCellValueFactory(new PropertyValueFactory<>("noOfBurgersRequired"));

//        Display customer details
        List<Customer> customerList = foodQueue.getCustomerList();
        List<Customer> waitingQueue = foodQueue.getWaitingQueue();

        ObservableList<Customer> observableCustomerList = FXCollections.observableArrayList(customerList);
        customerTableView.setItems(observableCustomerList);

//        Add waiting queue data to the existing customerTableView
        observableCustomerList.addAll(waitingQueue);

        TableColumn<String, String> queues = new TableColumn<>("Queues");
        queues.setPrefWidth(91.19998550415039);

    }

//    Search the details of a customer
    @FXML
    public void searchCustomer() {
        String searchTerm = searchTextField.getText();
        Customer customer = foodQueue.searchCustomer(searchTerm);
        displaySearchResult(customer);
    }

//    Display the details of a customer when searched
    private void displaySearchResult(Customer customer) {
        if (customer != null && (customer.getFirstName().equals("X") || customer.getFirstName().equals("O"))){
            customerDetailsText.setText("");
        }
        else if (customer != null) {
            String result =
                    "\nFirst Name:    " + customer.getFirstName() + "\n" +
                    "Second Name:    " + customer.getSecondName() + "\n" +
                    "Number of Burgers Required:    " + customer.getNoOfBurgersRequired();
            customerDetailsText.setText(result);
        } else {
            customerDetailsText.setText("\nCustomer not found.");
        }
    }

}

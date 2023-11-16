package com.classesVersion;

public class Customer {
    private String firstName;
    private String secondName;
    private int noOfBurgersRequired;

//    Constructor
    public Customer(String firstName, String secondName, int noOfBurgersRequired) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.noOfBurgersRequired = noOfBurgersRequired;
    }

//    Getter method for first name
    public String getFirstName() {
        return firstName;
    }

//    Getter method for second name
    public String getSecondName() {
        return secondName;
    }

//    Getter method for No. of burgers required.
    public int getNoOfBurgersRequired() { return noOfBurgersRequired;
    }

//    Setter method for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    Setter method for second name
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

//    Setter method for No. of burgers required.
    public void setNoOfBurgersRequired(int noOfBurgersRequired) {
        this.noOfBurgersRequired = noOfBurgersRequired;
    }

}

package com.anattests.test1good;

public class Customer {

    private String name;
    private String email;
    private String type; // REGULAR, GOLD, EMPLOYEE, VIP
    private Address address;

    public Customer() {
    }

    public Customer(String name, String email, String type, Address address) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

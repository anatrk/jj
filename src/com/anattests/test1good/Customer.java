package com.anattests.test1good;

import com.anattests.test1good.customertype.CustomerType;

import java.util.Objects;

public class Customer {

    private String name;
    private String email;
    private CustomerType type;
    private Address address;

    public Customer(String name, String email, CustomerType type, Address address) {
        this.name = name;
        this.email = email;
        this.type = Objects.requireNonNull(type, "type");
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

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = Objects.requireNonNull(type, "type");
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

package com.anattests.test1good;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private long id;
    private List<Item> items;
    private Customer customer;

    public Order() {
    }

    public Order(long id, List<Item> items, Customer customer) {
        this.id = id;
        this.items = items;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // check the order can be processed
    public void validate() {
        if (items == null || items.isEmpty()) {
            throw new InvalidOrderException("no items");
        }
        if (customer == null) {
            throw new InvalidOrderException("no customer");
        }
        if (customer.getEmail().equals("") || !customer.getEmail().contains("@")) {
            throw new InvalidOrderException("bad email");
        }
    }

    // subtotal, minus customer discount, plus country tax
    public double calculateTotal() {
        double t = 0;

        // calc total
        for (Item i : items) {
            t = t + i.getPrice() * i.getQty();
        }

        // discounts
        if (customer.getType().equals("REGULAR")) {
            if (t > 100) t = t * 0.95;
        } else if (customer.getType().equals("GOLD")) {
            t = t * 0.9;
            if (LocalDate.now().getDayOfWeek().getValue() == 5) t = t * 0.97; // friday
        } else if (customer.getType().equals("EMPLOYEE")) {
            t = t * 0.7;
        } else if (customer.getType().equals("VIP")) {
            t = t * 0.85;
            if (t > 1000) t = t - 50;
        }

        // tax
        if (customer.getAddress().getCountry().getCode().equals("IL")) {
            t = t * 1.17;
        } else if (customer.getAddress().getCountry().getCode().equals("US")) {
            t = t * 1.08;
        } else {
            t = t * 1.2;
        }

        return t;
    }
}

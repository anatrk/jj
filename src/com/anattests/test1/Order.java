package com.anattests.test1;

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
}

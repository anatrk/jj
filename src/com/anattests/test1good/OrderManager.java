package com.anattests.test1good;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

// This class processes orders
public class OrderManager {

    private static OrderManager instance;
    public static OrderManager getInstance() {
        if (instance == null) instance = new OrderManager();
        return instance;
    }

    // process the order
    public double process(Order o, boolean sendMail, boolean isTest) {
        if (o == null) {
            throw new InvalidOrderException("null order");
        }
        o.validate();

        double t = 0;

        // calc total
        for (Item i : o.getItems()) {
            t = t + i.getPrice() * i.getQty();
        }

        // discounts
        if (o.getCustomer().getType().equals("REGULAR")) {
            if (t > 100) t = t * 0.95;
        } else if (o.getCustomer().getType().equals("GOLD")) {
            t = t * 0.9;
            if (LocalDate.now().getDayOfWeek().getValue() == 5) t = t * 0.97; // friday
        } else if (o.getCustomer().getType().equals("EMPLOYEE")) {
            t = t * 0.7;
        } else if (o.getCustomer().getType().equals("VIP")) {
            t = t * 0.85;
            if (t > 1000) t = t - 50;
        }

        // tax
        if (o.getCustomer().getAddress().getCountry().getCode().equals("IL")) {
            t = t * 1.17;
        } else if (o.getCustomer().getAddress().getCountry().getCode().equals("US")) {
            t = t * 1.08;
        } else {
            t = t * 1.2;
        }

        // save to db
        if (!isTest) {
            try {
                Connection c = DriverManager.getConnection("jdbc:mysql://prod-db:3306/shop", "admin", "admin123");
                Statement s = c.createStatement();
                s.executeUpdate("INSERT INTO orders VALUES (" + o.getId() + ", " + t + ", '" + LocalDate.now() + "')");
                c.close();
            } catch (Exception e) {
                System.out.println("error " + e);
            }
        }

        // send mail
        if (sendMail) {
            EmailService es = new EmailService("smtp.company.com", 25);
            String body = "Dear " + o.getCustomer().getName() + ", your order " + o.getId() + " total is " + t;
            es.send(o.getCustomer().getEmail(), "Order confirmation", body);
        }

        System.out.println("Order " + o.getId() + " processed. total=" + t);
        return t;
    }
}
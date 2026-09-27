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

        double t = o.calculateTotal();

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
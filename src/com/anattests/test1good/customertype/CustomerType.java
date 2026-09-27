package com.anattests.test1good.customertype;

// A kind of customer, each with its own discount rule
public sealed interface CustomerType
        permits RegularCustomer, GoldCustomer, EmployeeCustomer, VipCustomer, StandardCustomer {

    double applyDiscount(double subtotal);

    // map a stored code such as "GOLD" to its type; unknown codes get no discount
    static CustomerType fromCode(String code) {
        return switch (code) {
            case "REGULAR" -> new RegularCustomer();
            case "GOLD" -> new GoldCustomer();
            case "EMPLOYEE" -> new EmployeeCustomer();
            case "VIP" -> new VipCustomer();
            default -> new StandardCustomer();
        };
    }
}

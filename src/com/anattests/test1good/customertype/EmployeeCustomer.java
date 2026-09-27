package com.anattests.test1good.customertype;

// 30% off
public final class EmployeeCustomer implements CustomerType {

    @Override
    public double applyDiscount(double subtotal) {
        return subtotal * 0.7;
    }
}

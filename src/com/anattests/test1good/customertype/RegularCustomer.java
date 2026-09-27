package com.anattests.test1good.customertype;

// 5% off orders above 100
public final class RegularCustomer implements CustomerType {

    @Override
    public double applyDiscount(double subtotal) {
        return subtotal > 100 ? subtotal * 0.95 : subtotal;
    }
}

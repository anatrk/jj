package com.anattests.test1good.customertype;

// no discount
public final class StandardCustomer implements CustomerType {

    @Override
    public double applyDiscount(double subtotal) {
        return subtotal;
    }
}

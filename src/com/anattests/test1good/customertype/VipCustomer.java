package com.anattests.test1good.customertype;

// 15% off, then another 50 off if the result is above 1000
public final class VipCustomer implements CustomerType {

    @Override
    public double applyDiscount(double subtotal) {
        double t = subtotal * 0.85;
        return t > 1000 ? t - 50 : t;
    }
}

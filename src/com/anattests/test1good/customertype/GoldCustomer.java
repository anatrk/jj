package com.anattests.test1good.customertype;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;

// 10% off, plus an extra 3% on Fridays
public final class GoldCustomer implements CustomerType {

    private final Clock clock;

    public GoldCustomer() {
        this(Clock.systemDefaultZone());
    }

    public GoldCustomer(Clock clock) {
        this.clock = clock;
    }

    @Override
    public double applyDiscount(double subtotal) {
        double t = subtotal * 0.9;
        if (LocalDate.now(clock).getDayOfWeek() == DayOfWeek.FRIDAY) t = t * 0.97;
        return t;
    }
}

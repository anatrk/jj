package com.anattests.test1good;

import com.anattests.test1good.customertype.*;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {

    private static final double DELTA = 1e-9;

    private final OrderManager manager = OrderManager.getInstance();

    // --- helpers ---

    private static Customer customer(CustomerType type, String countryCode) {
        return new Customer("John", "john@example.com", type,
                new Address("Main St 1", "Somewhere", new Country(countryCode, countryCode)));
    }

    private static Order order(Customer customer, Item... items) {
        return new Order(1L, new ArrayList<>(List.of(items)), customer);
    }

    // isTest=true skips the DB write, sendMail=false skips the email
    private double process(Order o) {
        return manager.process(o, false, true);
    }

    // --- singleton ---

    @Test
    void getInstanceReturnsSameInstance() {
        assertSame(OrderManager.getInstance(), OrderManager.getInstance());
    }

    // --- validation ---

    private void assertInvalid(Order o, String expectedMessage) {
        InvalidOrderException e = assertThrows(InvalidOrderException.class, () -> process(o));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void nullOrderThrows() {
        assertInvalid(null, "null order");
    }

    @Test
    void nullItemsThrows() {
        assertInvalid(new Order(1L, null, customer(new RegularCustomer(), "IL")), "no items");
    }

    @Test
    void emptyItemsThrows() {
        assertInvalid(new Order(1L, new ArrayList<>(), customer(new RegularCustomer(), "IL")), "no items");
    }

    @Test
    void nullCustomerThrows() {
        assertInvalid(new Order(1L, List.of(new Item("a", 10, 1)), null), "no customer");
    }

    @Test
    void emptyEmailThrows() {
        Customer c = customer(new RegularCustomer(), "IL");
        c.setEmail("");
        assertInvalid(order(c, new Item("a", 10, 1)), "bad email");
    }

    @Test
    void emailWithoutAtSignThrows() {
        Customer c = customer(new RegularCustomer(), "IL");
        c.setEmail("john.example.com");
        assertInvalid(order(c, new Item("a", 10, 1)), "bad email");
    }

    // --- subtotal ---

    @Test
    void subtotalSumsPriceTimesQuantity() {
        // 10*2 + 5*3 = 35, no discount (REGULAR <= 100), IL tax 17%
        Order o = order(customer(new RegularCustomer(), "IL"), new Item("a", 10, 2), new Item("b", 5, 3));
        assertEquals(35 * 1.17, process(o), DELTA);
    }

    // --- discounts ---

    @Test
    void regularAtOrBelow100HasNoDiscount() {
        Order o = order(customer(new RegularCustomer(), "IL"), new Item("a", 100, 1));
        assertEquals(100 * 1.17, process(o), DELTA);
    }

    @Test
    void regularAbove100Gets5PercentOff() {
        Order o = order(customer(new RegularCustomer(), "IL"), new Item("a", 200, 1));
        assertEquals(200 * 0.95 * 1.17, process(o), DELTA);
    }

    private static Clock clockAt(LocalDate date) {
        return Clock.fixed(date.atStartOfDay(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC);
    }

    @Test
    void goldGets10PercentOffOnWeekdays() {
        GoldCustomer gold = new GoldCustomer(clockAt(LocalDate.of(2024, 1, 4))); // Thursday
        Order o = order(customer(gold, "IL"), new Item("a", 100, 1));
        assertEquals(100 * 0.9 * 1.17, process(o), DELTA);
    }

    @Test
    void goldGetsExtra3PercentOffOnFriday() {
        GoldCustomer gold = new GoldCustomer(clockAt(LocalDate.of(2024, 1, 5))); // Friday
        Order o = order(customer(gold, "IL"), new Item("a", 100, 1));
        assertEquals(100 * 0.9 * 0.97 * 1.17, process(o), DELTA);
    }

    @Test
    void employeeGets30PercentOff() {
        Order o = order(customer(new EmployeeCustomer(), "IL"), new Item("a", 100, 1));
        assertEquals(100 * 0.7 * 1.17, process(o), DELTA);
    }

    @Test
    void vipGets15PercentOff() {
        Order o = order(customer(new VipCustomer(), "IL"), new Item("a", 100, 1));
        assertEquals(100 * 0.85 * 1.17, process(o), DELTA);
    }

    @Test
    void vipOver1000AfterDiscountGetsExtra50Off() {
        // 2000 * 0.85 = 1700 > 1000 -> 1650
        Order o = order(customer(new VipCustomer(), "IL"), new Item("a", 2000, 1));
        assertEquals((2000 * 0.85 - 50) * 1.17, process(o), DELTA);
    }

    @Test
    void standardCustomerHasNoDiscount() {
        Order o = order(customer(new StandardCustomer(), "IL"), new Item("a", 500, 1));
        assertEquals(500 * 1.17, process(o), DELTA);
    }

    // --- tax ---

    @Test
    void usTaxIs8Percent() {
        Order o = order(customer(new RegularCustomer(), "US"), new Item("a", 50, 1));
        assertEquals(50 * 1.08, process(o), DELTA);
    }

    @Test
    void otherCountryTaxIs20Percent() {
        Order o = order(customer(new RegularCustomer(), "DE"), new Item("a", 50, 1));
        assertEquals(50 * 1.2, process(o), DELTA);
    }

    // --- side effects ---

    @Test
    void sendingMailDoesNotAffectTotal() {
        Order o = order(customer(new RegularCustomer(), "US"), new Item("a", 50, 1));
        assertEquals(50 * 1.08, manager.process(o, true, true), DELTA);
    }
}

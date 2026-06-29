package oms;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Validates that Order and Customer preserve the functional
 * requirements F1, F2, F9, F12, F13, F14 (Member B's scope).
 */
public class OrderTest {

    // ---- F2 / F14: discount applied above $100, no leaked field ----

    @Test
    public void discountAppliedWhenAmountAboveThreshold() {
        Customer customer = new Customer("John", "5551234567");
        Order order = new Order("ORD001", customer, 200.0, new CashPayment());
        order.process();
        assertEquals(180.0, order.getAmount(), 0.0001); // 200 - 10%
    }

    @Test
    public void noDiscountWhenAmountAtOrBelowThreshold() {
        Customer customer = new Customer("Mary", "5559876543");
        Order order = new Order("ORD002", customer, 100.0, new CashPayment());
        order.process();
        assertEquals(100.0, order.getAmount(), 0.0001); // 100 is not > 100
    }

    // ---- F3 (via PaymentMethod, exercised through Order) ----

    @Test
    public void cardPaymentAddsFiveDollarFee() {
        Customer customer = new Customer("John", "5551234567");
        Order order = new Order("ORD003", customer, 200.0, new CardPayment());
        order.process();
        assertEquals(185.0, order.getAmount(), 0.0001); // 180 + $5 card fee
    }

    @Test
    public void cashPaymentAddsNoFee() {
        Customer customer = new Customer("John", "5551234567");
        Order order = new Order("ORD004", customer, 200.0, new CashPayment());
        order.process();
        assertEquals(180.0, order.getAmount(), 0.0001);
    }

    // ---- F9 / F12: validation enforced at creation ----

    @Test(expected = IllegalArgumentException.class)
    public void orderRejectsEmptyOrderId() {
        Customer customer = new Customer("John", "5551234567");
        new Order("", customer, 100.0, new CashPayment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void orderRejectsNegativeAmount() {
        Customer customer = new Customer("John", "5551234567");
        new Order("ORD005", customer, -50.0, new CashPayment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void orderRejectsNullPaymentMethod() {
        Customer customer = new Customer("John", "5551234567");
        new Order("ORD006", customer, 100.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void customerRejectsEmptyName() {
        new Customer("", "5551234567");
    }

    @Test(expected = IllegalArgumentException.class)
    public void customerRejectsEmptyPhone() {
        new Customer("John", "");
    }

    // ---- F4 (phone formatting lives on Customer, used via Order) ----

    @Test
    public void phoneNumberStripsNonDigitCharacters() {
        Customer customer = new Customer("Siti", "012-345 6789");
        assertEquals("0123456789", customer.getDigitsOnlyPhone());
    }

    // ---- F11: new payment type works through Order with zero changes ----

    @Test
    public void payPalPaymentWorksThroughOrderWithoutModification() {
        Customer customer = new Customer("Nora", "5550001111");
        Order order = new Order("ORD007", customer, 50.0, new PayPalPayment());
        order.process();
        assertEquals(52.0, order.getAmount(), 0.0001); // no discount, +$2 PayPal fee
        assertEquals("PAYPAL", order.getPaymentName());
    }
}

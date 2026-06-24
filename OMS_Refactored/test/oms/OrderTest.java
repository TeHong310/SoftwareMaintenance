package oms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests validating that the refactored Order Management System preserves
 * every functional requirement (F1-F15). Run with JUnit 5 (JUnit Jupiter).
 */
class OrderTest {

    // F2 + F3: 200 -> -10% discount = 180 -> +$5 card fee = 185
    @Test
    void cardOrderAboveThresholdDiscountThenFee() {
        Order order = new Order("ORD001", new Customer("John", "5551234567"),
                200.0, new CardPayment());
        assertEquals(185.0, order.calculateFinalAmount(), 0.001);
    }

    // F2 + F3: 200 -> -10% = 180 -> cash adds no fee
    @Test
    void cashOrderAboveThresholdDiscountNoFee() {
        Order order = new Order("ORD002", new Customer("Jane", "5551234567"),
                200.0, new CashPayment());
        assertEquals(180.0, order.calculateFinalAmount(), 0.001);
    }

    // F2 boundary: exactly 100 is NOT > 100, so no discount
    @Test
    void amountAtThresholdGetsNoDiscount() {
        Order order = new Order("ORD003", new Customer("Bob", "5551234567"),
                100.0, new CashPayment());
        assertEquals(100.0, order.calculateFinalAmount(), 0.001);
    }

    // F3: card fee applies even when no discount (below threshold)
    @Test
    void cardFeeAppliedBelowThreshold() {
        Order order = new Order("ORD004", new Customer("Bob", "5551234567"),
                50.0, new CardPayment());
        assertEquals(55.0, order.calculateFinalAmount(), 0.001);
    }

    // F4: phone formatting is the Customer's job; dashes/spaces/parens stripped
    @Test
    void phoneDigitsStripFormatting() {
        Customer c = new Customer("John", "(555) 123-4567");
        assertEquals("5551234567", c.getPhoneDigits());
    }

    // F9 + F12: invalid orders rejected by the order object's own validation
    @Test
    void invalidOrdersRejected() {
        Customer c = new Customer("John", "5551234567");
        assertThrows(IllegalArgumentException.class,
                () -> new Order("", c, 200.0, new CardPayment()));
        assertThrows(IllegalArgumentException.class,
                () -> new Order("ORD001", c, -1.0, new CardPayment()));
    }

    // F11: a new payment type is added by implementing the interface only,
    // with no change to Order, CardPayment or CashPayment.
    @Test
    void newPaymentTypeWithoutModifyingExistingCode() {
        PaymentMethod paypal = new PaymentMethod() {
            public double applyFee(double amount) { return amount + 2.0; }
            public String name() { return "PAYPAL"; }
        };
        Order order = new Order("ORD005", new Customer("John", "5551234567"),
                50.0, paypal);
        assertEquals(52.0, order.calculateFinalAmount(), 0.001);
    }

    // F6 + F7: order processing depends on the OrderRepository abstraction,
    // so a fake repository can be injected (Dependency Inversion).
    @Test
    void orderIsSavedThroughRepositoryAbstraction() {
        class FakeRepository implements OrderRepository {
            boolean saved = false;
            public void save(Order order) { saved = true; }
        }
        FakeRepository repo = new FakeRepository();
        OrderService service = new OrderService(repo);
        Order order = new Order("ORD006", new Customer("John", "5551234567"),
                200.0, new CardPayment());
        double total = service.process(order);
        assertTrue(repo.saved);
        assertEquals(185.0, total, 0.001);
    }

    // F8 / LSP: a Bicycle must NOT expose engine behaviour
    @Test
    void bicycleHasNoEngineCapability() {
        Vehicle bicycle = new Bicycle();
        assertFalse(bicycle instanceof Engine);
    }

    // A Truck genuinely has an engine
    @Test
    void truckHasEngineCapability() {
        Vehicle truck = new Truck();
        assertTrue(truck instanceof Engine);
    }
}

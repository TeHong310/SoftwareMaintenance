package oms;

/**
 * Represents a single order as one cohesive, self-validating unit.
 *
 * - F1: built in a single constructor call. There is no start() /
 *   step2() / step3() relay chain re-passing the same five values
 *   through intermediate methods — the caller supplies everything once.
 *
 * - F9: orderId, customer, amount and paymentMethod are stored together
 *   as one object, with validation rules applied at creation time
 *   (see validateOrder()).
 *
 * - F12 / F13: validation is a private method with real logic, called
 *   directly from the constructor. There is no empty validate() stub
 *   and no middle() method delegating to it — both were removed
 *   entirely rather than kept as dead code.
 *
 * - F2 / F14: the 10% discount is computed with a local variable inside
 *   applyDiscount(). No discount field exists on this class (or
 *   anywhere else) before, during, or after the calculation — it only
 *   ever lives on the method's call stack.
 */
public final class Order {

    private static final double DISCOUNT_THRESHOLD = 100.0;
    private static final double DISCOUNT_RATE = 0.10;

    private final String orderId;
    private final Customer customer;
    private double amount;
    private final PaymentMethod paymentMethod;

    public Order(String orderId, Customer customer, double amount, PaymentMethod paymentMethod) {
        this.orderId = orderId;
        this.customer = customer;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        validateOrder(); // F12 / F13: direct call, real logic, no delegation chain
    }

    private void validateOrder() {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be empty");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Order amount cannot be negative");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method is required");
        }
    }

    /**
     * Runs the order's processing rules: discount, then payment fee.
     * Functionally equivalent to the original process() method's
     * calculation logic (F2, F3), but with no static/shared discount
     * field anywhere in the system.
     */
    public void process() {
        applyDiscount();
        amount = paymentMethod.applyFee(amount);
    }

    /**
     * F2 / F14: discount is a local variable that exists only for the
     * duration of this method call — nothing is retained afterwards.
     */
    private void applyDiscount() {
        if (amount > DISCOUNT_THRESHOLD) {
            double discount = amount * DISCOUNT_RATE;
            amount = amount - discount;
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentName() {
        return paymentMethod.name();
    }
}

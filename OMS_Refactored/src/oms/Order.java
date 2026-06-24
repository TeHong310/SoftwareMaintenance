package oms;

/**
 * Order is a single cohesive unit (F9) that is constructed in one operation (F1)
 * and validates itself on creation (F12). The discount is computed using only a
 * local variable and is never retained as a field (F2, F14).
 */
public final class Order {
    private static final double DISCOUNT_THRESHOLD = 100.0;
    private static final double DISCOUNT_RATE = 0.10;

    private final String orderId;
    private final Customer customer;
    private final double amount;
    private final PaymentMethod paymentMethod;

    /** F1: full order accepted in a single constructor call, no intermediate hops. */
    public Order(String orderId, Customer customer, double amount, PaymentMethod paymentMethod) {
        validate(orderId, customer, amount, paymentMethod);
        this.orderId = orderId;
        this.customer = customer;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    /** F12: validation is a private method with real logic inside the order object. */
    private void validate(String orderId, Customer customer, double amount, PaymentMethod paymentMethod) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID is required");
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
     * F2: applies a 10% discount above the threshold; F3: applies the payment fee.
     * F14: the discount lives only in the local variable 'discount' and is discarded.
     */
    public double calculateFinalAmount() {
        double finalAmount = amount;
        if (finalAmount > DISCOUNT_THRESHOLD) {
            double discount = finalAmount * DISCOUNT_RATE;   // local only, not stored
            finalAmount = finalAmount - discount;
        }
        return paymentMethod.applyFee(finalAmount);
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}

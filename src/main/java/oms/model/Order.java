package oms.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import oms.payment.PaymentMethod;

/**
 * A single order, modelled as one cohesive, self-validating unit.
 *
 * <p>This class removes several code smells found in the legacy
 * {@code OMS} class:</p>
 *
 * <ul>
 *   <li><b>F1 &mdash; Middle Man / Message Chain.</b> The order is built in a
 *       single constructor call. The legacy
 *       {@code start() -> step2() -> step3()} relay, which re-passed the same
 *       five values through intermediate methods, is gone.</li>
 *   <li><b>F9 &mdash; Data Clumps / Global Data.</b> Order id, customer, amount
 *       and payment method are stored together as one object with its own
 *       validation rules, instead of as {@code static} fields shared across the
 *       whole program.</li>
 *   <li><b>F12 / F13 &mdash; Dead Code / Middle Man.</b> The empty
 *       {@code validate()} stub and the {@code middle()} delegate are removed.
 *       Validation is a private method containing real logic, invoked directly
 *       by the constructor.</li>
 *   <li><b>F2 / F14 &mdash; Temporary Field.</b> The 10&nbsp;% discount is
 *       computed with a local variable inside {@link #applyDiscount(BigDecimal)}.
 *       No discount field exists on this class or anywhere else, so nothing is
 *       retained in memory once the calculation completes.</li>
 * </ul>
 *
 * <p>Monetary values use {@link BigDecimal} rather than {@code double}, which
 * removes the binary rounding error present in the legacy code and keeps the
 * money type consistent with {@link PaymentMethod}.</p>
<<<<<<< HEAD

=======
 *
 * @author tehong
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
 */
public final class Order {

    /** Orders strictly above this amount qualify for a discount (F2). */
    private static final BigDecimal DISCOUNT_THRESHOLD = new BigDecimal("100.00");

    /** The discount rate applied to qualifying orders: 10 % (F2). */
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10");

    /** Currency values are reported to two decimal places. */
    private static final int MONEY_SCALE = 2;

    private final String orderId;
    private final Customer customer;
    private final BigDecimal amount;
    private final PaymentMethod paymentMethod;

    /**
     * Creates a fully-formed, validated order in a single operation (F1, F9).
     *
     * @param orderId       unique identifier; must not be {@code null} or blank
     * @param customer      the ordering customer; must not be {@code null}
     * @param amount        the amount before discount and fees; must not be {@code null} or negative
     * @param paymentMethod the payment strategy; must not be {@code null}
     * @throws IllegalArgumentException if any argument fails validation
     */
    public Order(final String orderId,
                 final Customer customer,
                 final BigDecimal amount,
                 final PaymentMethod paymentMethod) {
        this.orderId = orderId;
        this.customer = customer;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        validate(); // F12 / F13: real validation, called directly, no delegation chain
    }

    /**
     * Enforces the order's invariants at creation time (F9, F12).
     *
     * @throws IllegalArgumentException if the order is not in a valid state
     */
    private void validate() {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID must not be empty");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Order amount is required");
        }
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("Order amount must not be negative");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method is required");
        }
    }

    /**
     * Calculates the payable total: the discount rule (F2) followed by the
     * payment method's fee rule (F3).
     *
     * <p>The method is a pure function of the order's immutable state, so it
     * may be called any number of times and always yields the same result.
     * Nothing is cached and no field is mutated (F14).</p>
     *
     * @return the final amount payable, to two decimal places
     */
    public BigDecimal finalAmount() {
        final BigDecimal discounted = applyDiscount(amount);
        return paymentMethod.applyFee(discounted).setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * Applies the 10&nbsp;% discount when the amount exceeds the threshold (F2).
     *
     * <p>The discount lives only in a local variable on this method's call
     * stack. It is never promoted to a field, so no discount-related value
     * survives the call (F14).</p>
     *
     * @param base the amount before discount
     * @return the amount after any discount
     */
    private BigDecimal applyDiscount(final BigDecimal base) {
        if (base.compareTo(DISCOUNT_THRESHOLD) > 0) {
            final BigDecimal discount = base.multiply(DISCOUNT_RATE);
            return base.subtract(discount);
        }
        return base;
    }

    /**
     * @return the order identifier
     */
    public String orderId() {
        return orderId;
    }

    /**
     * @return the ordering customer
     */
    public Customer customer() {
        return customer;
    }

    /**
     * @return the original amount, before discount and payment fees
     */
    public BigDecimal amount() {
        return amount;
    }

    /**
     * @return the payment strategy chosen for this order
     */
    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }
}
package oms.payment;

import java.math.BigDecimal;

/**
 * Strategy contract for a payment method.
 *
 * <p>Each concrete payment type encapsulates its own fee rule, so the order
 * processing logic depends only on this abstraction rather than on a chain of
 * {@code if}/{@code else} branches. A new payment type (e.g. PAYPAL, CRYPTO)
 * is introduced by adding a new implementation of this interface, without
 * modifying any existing class &mdash; satisfying the Open/Closed Principle
 * (Requirement F11).</p>

 */
public interface PaymentMethod {

    /**
     * Applies this payment method's fee rule to the supplied amount.
     *
     * @param amount the amount before any payment fee; must not be {@code null}
     * @return the amount after this method's fee has been applied
     */
    BigDecimal applyFee(BigDecimal amount);

    /**
     * Returns the canonical identifier of this payment type.
     *
     * @return the payment type key, e.g. {@code "CARD"}
     */
    String type();
}

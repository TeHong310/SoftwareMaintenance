package oms.payment;

import java.math.BigDecimal;

/**
 * Payment method strategy interface.
 *
 * <p>Each payment type handle its own fee logic, order processing don't
 * need if/else chain anymore. New type like PAYPAL just add new class,
 * no need touch existing code (F11, Open/Closed Principle).</p>
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

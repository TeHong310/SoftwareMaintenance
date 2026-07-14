package oms.payment;

import java.math.BigDecimal;

/**
 * CARD payment strategy.
 *
 * <p>Add fixed $5.00 fee (F3). Use {@link BigDecimal} so no rounding
 * error like double.</p>
 */
public final class CardPayment implements PaymentMethod {

    /** Fixed processing fee applied to every card payment. */
    private static final BigDecimal PROCESSING_FEE = new BigDecimal("5.00");

    /** Canonical type key for this strategy. */
    private static final String TYPE = "CARD";

    @Override
    public BigDecimal applyFee(final BigDecimal amount) {
        return amount.add(PROCESSING_FEE);
    }

    @Override
    public String type() {
        return TYPE;
    }
}

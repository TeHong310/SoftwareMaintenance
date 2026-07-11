package oms.payment;

import java.math.BigDecimal;

public final class CashPayment implements PaymentMethod {

    /** Canonical type key for this strategy. */
    private static final String TYPE = "CASH";

    @Override
    public BigDecimal applyFee(final BigDecimal amount) {
        return amount;
    }

    @Override
    public String type() {
        return TYPE;
    }
}

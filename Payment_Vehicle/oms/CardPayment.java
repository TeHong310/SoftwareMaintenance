package oms;

import java.math.BigDecimal;
import java.util.Objects;


public final class CardPayment implements PaymentMethod {

    /** The fixed card processing fee, expressed exactly to avoid binary rounding. */
    private static final BigDecimal PROCESSING_FEE = new BigDecimal("5.00");

    @Override
    public BigDecimal applyFee(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be null");
        return amount.add(PROCESSING_FEE);
    }

    @Override
    public String displayName() {
        return "Card";
    }
}

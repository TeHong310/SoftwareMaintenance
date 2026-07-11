package oms;

import java.math.BigDecimal;
import java.util.Objects;


public final class CashPayment implements PaymentMethod {

    @Override
    public BigDecimal applyFee(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be null");
        return amount;
    }

    @Override
    public String displayName() {
        return "Cash";
    }
}

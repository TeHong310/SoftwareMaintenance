package oms.payment;

import java.math.BigDecimal;


public interface PaymentMethod {

    BigDecimal applyFee(BigDecimal amount);

    String type();
}

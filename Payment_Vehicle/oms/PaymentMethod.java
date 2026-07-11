package oms;

import java.math.BigDecimal;


public interface PaymentMethod {

    
    BigDecimal applyFee(BigDecimal amount);

 
    String displayName();
}

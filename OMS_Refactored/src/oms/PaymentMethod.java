package oms;

/**
 * Strategy abstraction for payment behaviour.
 * Satisfies F11 (new payment types e.g. PAYPAL/CRYPTO can be added by writing
 * a new implementation, with NO modification to existing processing logic) and
 * applies the Open/Closed Principle.
 */
public interface PaymentMethod {
    /** F3: returns the amount after this payment type's fee has been applied. */
    double applyFee(double amount);

    String name();
}

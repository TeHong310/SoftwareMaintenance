package oms;

public interface PaymentMethod {

    /** F3: returns the amount AFTER this payment type's fee has been applied. */
    double applyFee(double amount);

    /** Identifies the payment type (useful for receipts / logging). */
    String name();
}

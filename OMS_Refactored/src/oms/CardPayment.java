package oms;

/** F3: CARD adds a fixed processing fee. */
public final class CardPayment implements PaymentMethod {
    private static final double PROCESSING_FEE = 5.0;

    @Override
    public double applyFee(double amount) {
        return amount + PROCESSING_FEE;
    }

    @Override
    public String name() {
        return "CARD";
    }
}

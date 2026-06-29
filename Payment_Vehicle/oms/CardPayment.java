package oms;

public final class CardPayment implements PaymentMethod {

    private static final double PROCESSING_FEE = 5.0; // was a magic number in the original

    @Override
    public double applyFee(double amount) {
        return amount + PROCESSING_FEE;
    }

    @Override
    public String name() {
        return "CARD";
    }
}

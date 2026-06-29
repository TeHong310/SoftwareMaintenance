package oms;

public final class PayPalPayment implements PaymentMethod {

    private static final double PROCESSING_FEE = 2.0;

    @Override
    public double applyFee(double amount) {
        return amount + PROCESSING_FEE;
    }

    @Override
    public String name() {
        return "PAYPAL";
    }
}

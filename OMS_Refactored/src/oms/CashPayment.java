package oms;

/** F3: CASH adds no fee. */
public final class CashPayment implements PaymentMethod {
    @Override
    public double applyFee(double amount) {
        return amount;
    }

    @Override
    public String name() {
        return "CASH";
    }
}

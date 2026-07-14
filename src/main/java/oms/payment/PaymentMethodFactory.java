package oms.payment;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class PaymentMethodFactory {

    // keep payment strategy here, thread safe
    private static final Map<String, PaymentMethod> REGISTRY = new ConcurrentHashMap<>();

    static {
        register(new CardPayment());
        register(new CashPayment());
    }

    private PaymentMethodFactory() {
        // no need instantiate this class
    }

    public static void register(final PaymentMethod method) {
        REGISTRY.put(method.type().toUpperCase(), method);
    }

    public static PaymentMethod forType(final String type) {
        return Optional.ofNullable(type)
                .map(String::toUpperCase)
                .map(REGISTRY::get)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No payment method registered for type: " + type));
    }
}

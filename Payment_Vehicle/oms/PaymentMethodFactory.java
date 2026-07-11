package oms.payment;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry-based factory that resolves a {@link PaymentMethod} from its string
 * identifier.
 *
 * <p>Strategies register themselves in an open map keyed by payment type.
 * Supporting a new payment type therefore requires only that a new strategy be
 * registered at start-up; neither this class nor the order processor contains
 * any type-specific branching that must be edited (Requirement F11,
 * Open/Closed Principle).</p>
 *
 * @author Member A
 */
public final class PaymentMethodFactory {

    /** Thread-safe registry of payment strategies keyed by upper-case type. */
    private static final Map<String, PaymentMethod> REGISTRY = new ConcurrentHashMap<>();

    static {
        register(new CardPayment());
        register(new CashPayment());
    }

    private PaymentMethodFactory() {
        // utility class: prevent instantiation
    }

    /**
     * Registers (or replaces) a payment strategy under its own type key.
     *
     * @param method the strategy to register; must not be {@code null}
     */
    public static void register(final PaymentMethod method) {
        REGISTRY.put(method.type().toUpperCase(), method);
    }

    /**
     * Resolves the strategy registered for the supplied payment type.
     *
     * @param type the payment type identifier (case-insensitive)
     * @return the matching {@link PaymentMethod}
     * @throws IllegalArgumentException if no strategy is registered for {@code type}
     */
    public static PaymentMethod forType(final String type) {
        return Optional.ofNullable(type)
                .map(String::toUpperCase)
                .map(REGISTRY::get)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No payment method registered for type: " + type));
    }
}

package oms.model;

/**
 * Represents the customer who placed an order.
 *
 * <p>
 * Formatting of the phone number is the responsibility of this component,
 * not of the order-processing component (Requirement F4). {@code Order} and
 * {@code OrderService} never touch the raw phone string, which removes the
 * <em>Feature Envy</em> smell present in the legacy {@code process()} method,
 * where order processing reached into customer data to strip characters.
 * </p>
 *
 * <p>
 * The object validates itself on construction, so a {@code Customer} can
 * never exist in an invalid state (Requirement F9, Single Responsibility
<<<<<<< HEAD
<<<<<<< HEAD
 * Principle).</p>
 
=======
=======
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
 * Principle).
 * </p>
 *
 * @author Tehong
<<<<<<< HEAD
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
=======
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
 */
public final class Customer {

    /** Customer's display name. */
    private final String name;

    /** Phone number exactly as supplied, in any formatting style. */
    private final String rawPhone;

    /**
     * Creates a validated customer.
     *
     * @param name     the customer's name; must not be {@code null} or blank
     * @param rawPhone the phone number in any format; must not be {@code null} or
     *                 blank
     * @throws IllegalArgumentException if either argument is missing or blank
     */
    public Customer(final String name, final String rawPhone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name must not be empty");
        }
        if (rawPhone == null || rawPhone.isBlank()) {
            throw new IllegalArgumentException("Customer phone must not be empty");
        }
        this.name = name;
        this.rawPhone = rawPhone;
    }

    /**
     * @return the customer's name
     */
    public String name() {
        return name;
    }

    /**
     * Returns the phone number stripped of every non-digit character, so that
     * dashes, spaces and parentheses are removed (Requirement F4).
     *
     * @return the phone number as digits only, e.g. {@code "5551234567"}
     */
    public String phoneDigits() {
        return rawPhone.replaceAll("\\D", "");
    }

    @Override
    public String toString() {
        return name + " (" + phoneDigits() + ")";
    }
}
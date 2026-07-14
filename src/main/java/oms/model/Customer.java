package oms.model;

/**
 * Customer who place the order.
 *
 * <p>
 * Phone formatting is this class job, not Order or OrderService (F4).
 * Old process() method used to reach into customer data to strip the
 * phone number itself, that's Feature Envy smell, now fixed.
 * </p>
 *
 * <p>
 * Validate on construction, so Customer object always valid (F9, SRP).
 * </p>
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
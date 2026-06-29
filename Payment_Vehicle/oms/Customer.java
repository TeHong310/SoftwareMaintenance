package oms;

/**
 * Represents a customer placing an order.
 * Satisfies F4: phone formatting is the responsibility of the customer
 * component, not the order-processing component — Order/OrderProcessor
 * never touch the raw phone string directly.
 * Satisfies part of F9: the customer's own fields are validated when
 * the object is created, not scattered through later processing steps.
 */
public final class Customer {

    private final String name;
    private final String rawPhone;

    public Customer(String name, String rawPhone) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (rawPhone == null || rawPhone.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer phone cannot be empty");
        }
        this.name = name;
        this.rawPhone = rawPhone;
    }

    public String getName() {
        return name;
    }

    /**
     * F4: returns the phone number with all non-digit characters
     * (dashes, spaces, parentheses, etc.) removed.
     */
    public String getDigitsOnlyPhone() {
        return rawPhone.replaceAll("\\D", "");
    }
}

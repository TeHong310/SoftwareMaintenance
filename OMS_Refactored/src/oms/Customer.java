package oms;

/**
 * Encapsulates customer identity and is the single owner of phone formatting.
 * Satisfies F4 (phone formatting is the customer component's responsibility,
 * not the order-processing component) and supports F9 (cohesive data unit).
 */
public final class Customer {
    private final String name;
    private final String phone;

    public Customer(String name, String phone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Customer phone is required");
        }
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    /** F4: strips dashes, spaces and parentheses, returning digits only. */
    public String getPhoneDigits() {
        return phone.replaceAll("\\D", "");
    }
}

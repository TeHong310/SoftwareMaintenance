package oms.service;

import java.math.BigDecimal;
import java.util.Objects;

import oms.model.Order;
import oms.repository.OrderRepository;

/**
 * Coordinates the processing of an order: calculate, report, persist.
 *
 * <p>This class replaces the legacy {@code process()} God Method, which mixed
 * pricing rules, string formatting, console output and persistence in one
 * place. Each of those concerns now lives with the object that owns it, and
 * this service simply orchestrates them (Single Responsibility Principle).</p>
 *
 * <p>The repository is supplied through the constructor rather than created
 * internally, so this class depends on the {@link OrderRepository} abstraction
 * and never on a concrete database (Requirement F7, Dependency Inversion
 * Principle).</p>
<<<<<<< HEAD

=======
 *
 * @author tehong
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
 */
public final class OrderService {

    /** The persistence abstraction this service writes through (F7). */
    private final OrderRepository repository;

    /**
     * @param repository the repository to persist orders with; must not be {@code null}
     */
    public OrderService(final OrderRepository repository) {
        this.repository = Objects.requireNonNull(repository, "repository is required");
    }

    /**
     * Processes one order: applies the pricing rules, prints the order summary
     * (Requirement F5) and saves it (Requirement F6).
     *
     * @param order the order to process; must not be {@code null}
     * @return the final amount payable
     */
    public BigDecimal placeOrder(final Order order) {
        Objects.requireNonNull(order, "order is required");

        final BigDecimal total = order.finalAmount(); // F2, F3, F14
        printSummary(order, total);                   // F5
        repository.save(order);                       // F6, F7

        return total;
    }

    /**
     * Prints the order id, customer name, digits-only phone number and final
     * amount (Requirement F5).
     *
     * <p>The phone number is obtained from the customer, which owns that
     * formatting rule (Requirement F4); this service never inspects the raw
     * phone string.</p>
     *
     * @param order the processed order
     * @param total the final amount payable
     */
    private void printSummary(final Order order, final BigDecimal total) {
        System.out.println("Order: " + order.orderId());
        System.out.println("Customer: " + order.customer().name());
        System.out.println("Phone: " + order.customer().phoneDigits());
        System.out.println("Total: " + total);
    }
}
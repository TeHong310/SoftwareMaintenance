package oms.service;

import java.math.BigDecimal;
import java.util.Objects;

import oms.model.Order;
import oms.repository.OrderRepository;

/**
 * Handle order processing: calculate, print, save.
 *
 * <p>Replace old {@code process()} God Method that mix pricing, string
 * formatting, console output and persistence together. Now each job stay
 * with the class that own it, this class just orchestrate (SRP).</p>
 *
 * <p>Repository pass in through constructor, not create inside. So this
 * class only depend on {@link OrderRepository} interface, not the real
 * database (F7, Dependency Inversion Principle).</p>
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
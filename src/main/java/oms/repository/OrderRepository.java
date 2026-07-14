package oms.repository;

import oms.model.Order;

/**
 * Persistence abstraction for order.
 * (F7).
 *
 * Order processing depend on this interface, not the actual database
 * class. So can swap storage - Postgres, MySQL, or in-memory for test -
 * without touch {@code OrderService} or {@link Order}. This is
 * Dependency Inversion Principle.
 */
public interface OrderRepository {

    /**
     * Persists a processed order (Requirement F6).
     *
     * @param order the order to store; must not be {@code null}
     */
    void save(Order order);
}
package oms.repository;

import oms.model.Order;

/**
 * Persistence abstraction for orders (Requirement F7).
 *
 * <p>Order-processing logic depends on this interface rather than on a concrete
 * database class, so the storage technology can be swapped &mdash; PostgreSQL,
 * MySQL, or an in-memory double for testing &mdash; without a single change to
 * {@code OrderService} or {@link Order}. This is the Dependency Inversion
 * Principle: the high-level policy owns the abstraction, and the low-level
 * detail implements it.</p>
 *
 * @author Member B
 */
public interface OrderRepository {

    /**
     * Persists a processed order (Requirement F6).
     *
     * @param order the order to store; must not be {@code null}
     */
    void save(Order order);
}
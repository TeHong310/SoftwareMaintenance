package oms.repository;

import oms.model.Order;

/**
 * Persistence abstraction for orders 
 * (Requirement F7).
 *
 * Order-processing logic depends on this interface rather than on a concrete
 * database class, so the storage technology can be swapped &mdash; PostgreSQL,
 * MySQL, or an in-memory double for testing &mdash; without a single change to
 * {@code OrderService} or {@link Order}. This is the Dependency Inversion
 * Principle: the high-level policy owns the abstraction, and the low-level
<<<<<<< HEAD
<<<<<<< HEAD
 * detail implements it.</p>

=======
 * detail implements it.
 *
 * @author Stanley
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
=======
 * detail implements it.
 *
 * @author Stanley
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
 */
public interface OrderRepository {

    /**
     * Persists a processed order (Requirement F6).
     *
     * @param order the order to store; must not be {@code null}
     */
    void save(Order order);
}
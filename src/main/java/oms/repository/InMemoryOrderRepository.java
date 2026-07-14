package oms.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import oms.model.Order;

/**
 * In-memory {@link OrderRepository}, used as a test double.
 *
 * Its existence is itself the proof of Requirement F7: the concrete storage
 * implementation is swappable without modifying any order-processing logic,
<<<<<<< HEAD
<<<<<<< HEAD
 * because that logic depends only on the {@link OrderRepository} abstraction.</p>

=======
 * because that logic depends only on the {@link OrderRepository} abstraction.
 *

 */
public final class InMemoryOrderRepository implements OrderRepository {

    /** Orders saved during the lifetime of this repository. */
    private final List<Order> saved = new ArrayList<>();

    @Override
    public void save(final Order order) {
        saved.add(Objects.requireNonNull(order, "order is required"));
    }

    /**
     * @return an unmodifiable view of every order saved so far
     */
    public List<Order> savedOrders() {
        return Collections.unmodifiableList(saved);
    }

    /**
     * @return how many orders have been saved
     */
    public int count() {
        return saved.size();
    }
}
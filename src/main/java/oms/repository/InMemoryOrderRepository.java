package oms.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import oms.model.Order;

/**
 * In-memory {@link OrderRepository}, for testing only.
 *
 * This class prove F7 - storage can swap easily without touch order
 * processing logic, because that logic only depend on the
 * {@link OrderRepository} interface.
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
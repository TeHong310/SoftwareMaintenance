package oms;

/**
 * F7: database abstraction with a save(Order) method. Order processing depends on
 * this interface, not a concrete database, so the implementation is swappable
 * without touching processing logic (Dependency Inversion Principle).
 */
public interface OrderRepository {
    void save(Order order);
}

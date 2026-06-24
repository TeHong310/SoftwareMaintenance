package oms;

/**
 * Concrete repository. F15: the connection configuration exists in exactly one
 * place (this constant), reachable only through the OrderRepository abstraction.
 */
public final class DatabaseOrderRepository implements OrderRepository {
    private static final String CONNECTION_STRING = "jdbc:oms-db://localhost:5432/oms";

    @Override
    public void save(Order order) {
        // Real JDBC/ORM persistence would use CONNECTION_STRING here.
        System.out.println("Saved to DB [" + CONNECTION_STRING + "]: " + order.getOrderId());
    }
}

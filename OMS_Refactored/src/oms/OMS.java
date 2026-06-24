package oms;

import java.util.List;

/**
 * Composition root. Wires concrete implementations to abstractions and runs the
 * same scenario as the original program, preserving the functional output.
 */
public final class OMS {
    public static void main(String[] args) {
        Customer customer = new Customer("John", "5551234567");
        Order order = new Order("ORD001", customer, 200.0, new CardPayment());

        OrderRepository repository = new DatabaseOrderRepository();
        OrderService service = new OrderService(repository);
        service.process(order);

        // Demonstrates F8 and F10 without any per-type handler class.
        new VehicleFleet().operate(List.of(new Bicycle(), new Truck()));
    }
}

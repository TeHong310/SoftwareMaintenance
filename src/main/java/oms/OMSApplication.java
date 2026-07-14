package oms;

import java.math.BigDecimal;
import java.util.List;

import oms.model.Customer;
import oms.model.Order;
import oms.payment.PaymentMethodFactory;
import oms.repository.DatabaseOrderRepository;
import oms.repository.OrderRepository;
import oms.service.OrderService;
import oms.vehicle.Bicycle;
import oms.vehicle.Motorized;
import oms.vehicle.Truck;
import oms.vehicle.Vehicle;

/**
 * Entry point of the refactored Order Management System.
 *
 * <p>The legacy {@code OMS.main()} handed its five parameters to a chain of
 * {@code start() -> step2() -> step3()} methods before anything useful
 * happened. Here the order is assembled in a single operation and handed to
 * {@link OrderService} (Requirement F1).</p>
 */
public final class OMSApplication {

    /** Utility class: never instantiated. */
    private OMSApplication() {
        throw new AssertionError("OMSApplication must not be instantiated");
    }

    /**
     * Runs the demonstration scenario.
     *
     * @param args unused
     */
    public static void main(final String[] args) {
        final OrderRepository repository = new DatabaseOrderRepository(); // F7, F15
        final OrderService service = new OrderService(repository);

        // F1: order id, customer, amount and payment type supplied in one call.
        // The phone is deliberately written with punctuation to show that the
        // Customer strips it (F4).
        final Customer customer = new Customer("John", "(555) 123-4567");
        final Order order = new Order(
                "ORD001",
                customer,
                new BigDecimal("200.00"),
                PaymentMethodFactory.forType("CARD")); // F11

        service.placeOrder(order); // F2, F3, F5, F6

        demonstrateVehicleHierarchy();
    }

    /**
     * Shows that a mixed fleet is driven polymorphically, with no
     * {@code TruckHandler}-style class anywhere (Requirement F10), and that a
     * {@link Bicycle} simply has no engine to start (Requirement F8).
     */
    private static void demonstrateVehicleHierarchy() {
        System.out.println();
        final List<Vehicle> fleet = List.of(new Bicycle(), new Truck());

        for (final Vehicle vehicle : fleet) {
            if (vehicle instanceof Motorized motorized) { // only motorised types have an engine
                motorized.startEngine();
            }
            vehicle.move();
        }
    }
}
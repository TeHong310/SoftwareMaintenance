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
 * Entry point for the OMS.
 *
 * <p>Old code pass the 5 params through start() -> step2() -> step3()
 * before doing anything. Now everything build in one go and send to
 * {@link OrderService} (F1).</p>
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

        // F1: order id, customer, amount, payment type all in one call
        // phone got symbols on purpose, to show Customer will strip it (F4)
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
     * Run mixed vehicles without any TruckHandler class (F10).
     * Bicycle got no engine, so no startEngine here (F8).
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
package oms;

/**
 * Coordinates processing. Depends on the OrderRepository abstraction (DIP) and
 * delegates phone formatting to Customer (F4) and money rules to Order (F2/F3).
 * The old start -> step2 -> step3 -> process -> middle -> validate chain
 * (Middle Man / Message Chain smell) is gone (F1, F13).
 */
public final class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public double process(Order order) {
        double finalAmount = order.calculateFinalAmount();
        printReceipt(order, finalAmount); // F5
        repository.save(order);           // F6
        return finalAmount;
    }

    /** F5: outputs order ID, customer name, phone digits and final amount. */
    private void printReceipt(Order order, double finalAmount) {
        System.out.println("Order: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Phone: " + order.getCustomer().getPhoneDigits());
        System.out.println("Total: " + finalAmount);
    }
}

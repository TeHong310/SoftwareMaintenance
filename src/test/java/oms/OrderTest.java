package oms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import oms.model.Customer;
import oms.model.Order;
import oms.payment.CardPayment;
import oms.payment.CashPayment;
import oms.payment.PaymentMethod;
import oms.payment.PaymentMethodFactory;
import oms.repository.DatabaseConfig;
import oms.repository.DatabaseOrderRepository;
import oms.repository.InMemoryOrderRepository;
import oms.repository.OrderRepository;
import oms.service.OrderService;

/**
 * Unit tests for the order subsystem (Member B).
 *
 * <p>Each test is traced to the functional requirement it protects, so the
 * suite doubles as a regression net proving that the refactoring preserves the
 * behaviour of the legacy {@code OMS} class while removing its code smells.</p>
 *
<<<<<<< HEAD
 *  Member B
=======
 * @author tehong
>>>>>>> 8126633b0cafaccd14caea4afac5f740356ad2c2
 */
@DisplayName("Order, Customer, Repository")
class OrderTest {

    /** Compares money while ignoring scale, so 185.0 equals 185.00. */
    private static void assertMoneyEquals(final String expected, final BigDecimal actual) {
        assertEquals(0, new BigDecimal(expected).compareTo(actual),
                () -> "expected " + expected + " but was " + actual);
    }

    /** Builds a valid customer for tests that do not care about customer data. */
    private static Customer anyCustomer() {
        return new Customer("John", "5551234567");
    }

    // ---------- F1: an order is created in a single operation ----------

    @Test
    @DisplayName("F1: an order is fully built by one constructor call, with no relay chain")
    void orderIsBuiltInASingleOperation() {
        final Customer customer = anyCustomer();
        final PaymentMethod card = new CardPayment();
        final Order order = new Order("ORD001", customer, new BigDecimal("200.00"), card);

        assertEquals("ORD001", order.orderId());
        assertSame(customer, order.customer());
        assertMoneyEquals("200.00", order.amount());
        assertSame(card, order.paymentMethod());
    }

    // ---------- F2: 10% discount above $100 ----------

    @Test
    @DisplayName("F2: an amount above $100 receives a 10% discount")
    void discountAppliedWhenAmountExceedsThreshold() {
        final Order order = new Order("ORD002", anyCustomer(), new BigDecimal("200.00"), new CashPayment());
        assertMoneyEquals("180.00", order.finalAmount());
    }

    @Test
    @DisplayName("F2: an amount of exactly $100 receives no discount")
    void noDiscountWhenAmountEqualsThreshold() {
        final Order order = new Order("ORD003", anyCustomer(), new BigDecimal("100.00"), new CashPayment());
        assertMoneyEquals("100.00", order.finalAmount());
    }

    // ---------- F2 + F3: legacy behaviour is preserved ----------

    @Test
    @DisplayName("F2+F3: $200 by CARD still totals $185, exactly as the legacy system")
    void legacyScenarioProducesTheSameTotal() {
        final Order order = new Order("ORD001", anyCustomer(), new BigDecimal("200.00"), new CardPayment());
        assertMoneyEquals("185.00", order.finalAmount()); // 200 - 10% = 180, + $5 card fee
    }

    // ---------- F14: no discount value is retained ----------

    @Test
    @DisplayName("F14: the calculation is repeatable and leaves no state behind")
    void repeatedCalculationYieldsTheSameResult() {
        final Order order = new Order("ORD004", anyCustomer(), new BigDecimal("200.00"), new CardPayment());

        assertMoneyEquals("185.00", order.finalAmount());
        assertMoneyEquals("185.00", order.finalAmount()); // no accumulating discount field
        assertMoneyEquals("200.00", order.amount());      // the original amount is untouched
    }

    // ---------- F4: phone formatting belongs to the customer ----------

    @Test
    @DisplayName("F4: the customer strips dashes, spaces and parentheses from the phone number")
    void phoneNumberIsReducedToDigitsByTheCustomer() {
        final Customer customer = new Customer("Siti", "(012) 345-6789");
        assertEquals("0123456789", customer.phoneDigits());
    }

    // ---------- F9 / F12: validation happens at creation, with real logic ----------

    @Test
    @DisplayName("F9/F12: a blank order id is rejected at creation")
    void blankOrderIdIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order("  ", anyCustomer(), new BigDecimal("100.00"), new CashPayment()));
    }

    @Test
    @DisplayName("F9/F12: a negative amount is rejected at creation")
    void negativeAmountIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order("ORD005", anyCustomer(), new BigDecimal("-50.00"), new CashPayment()));
    }

    @Test
    @DisplayName("F9/F12: a missing payment method is rejected at creation")
    void missingPaymentMethodIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order("ORD006", anyCustomer(), new BigDecimal("100.00"), null));
    }

    @Test
    @DisplayName("F9: a customer with no name cannot be created")
    void blankCustomerNameIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("", "5551234567"));
    }

    // ---------- F5: the order summary is printed ----------

    @Test
    @DisplayName("F5: processing prints the order id, name, phone digits and final amount")
    void orderSummaryIsPrinted() {
        final PrintStream original = System.out;
        final ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));
        try {
            final Customer customer = new Customer("John", "(555) 123-4567");
            final Order order = new Order("ORD001", customer, new BigDecimal("200.00"), new CardPayment());
            new OrderService(new InMemoryOrderRepository()).placeOrder(order);
        } finally {
            System.setOut(original);
        }

        final String output = captured.toString();
        assertTrue(output.contains("Order: ORD001"), output);
        assertTrue(output.contains("Customer: John"), output);
        assertTrue(output.contains("Phone: 5551234567"), output); // F4 applied end-to-end
        assertTrue(output.contains("Total: 185.00"), output);
    }

    // ---------- F6: every processed order is saved ----------

    @Test
    @DisplayName("F6: a processed order is persisted exactly once")
    void processedOrderIsSaved() {
        final InMemoryOrderRepository repository = new InMemoryOrderRepository();
        final Order order = new Order("ORD007", anyCustomer(), new BigDecimal("120.00"), new CashPayment());

        new OrderService(repository).placeOrder(order);

        assertEquals(1, repository.count());
        assertSame(order, repository.savedOrders().get(0));
    }

    // ---------- F7: the storage implementation is swappable ----------

    @Test
    @DisplayName("F7: order processing works against any OrderRepository implementation")
    void repositoryImplementationIsSwappable() {
        final int[] saveCount = {0};
        final OrderRepository customRepository = order -> saveCount[0]++; // a brand-new implementation

        final Order order = new Order("ORD008", anyCustomer(), new BigDecimal("150.00"), new CashPayment());
        final BigDecimal total = new OrderService(customRepository).placeOrder(order);

        assertEquals(1, saveCount[0]); // OrderService needed no modification at all
        assertMoneyEquals("135.00", total);
    }

    // ---------- F11: a new payment type needs no change to existing logic ----------

    @Test
    @DisplayName("F11: a PAYPAL order is processed without modifying any existing class")
    void newPaymentTypeIsProcessedWithoutModifyingExistingClasses() {
        final PaymentMethod payPal = new PaymentMethod() {
            @Override
            public BigDecimal applyFee(final BigDecimal amount) {
                return amount.add(new BigDecimal("2.00"));
            }

            @Override
            public String type() {
                return "PAYPAL";
            }
        };
        PaymentMethodFactory.register(payPal);

        final Order order = new Order("ORD009", anyCustomer(), new BigDecimal("200.00"),
                PaymentMethodFactory.forType("paypal"));

        assertMoneyEquals("182.00", order.finalAmount()); // 200 - 10% = 180, + $2 PayPal fee
    }

    // ---------- F15: connection configuration lives in exactly one place ----------

    @Test
    @DisplayName("F15: the repository takes its connection settings from DatabaseConfig")
    void connectionConfigurationComesFromASingleLocation() {
        final DatabaseOrderRepository repository = new DatabaseOrderRepository();
        assertEquals(DatabaseConfig.url(), repository.connectionUrl());
    }
}
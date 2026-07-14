package oms;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import oms.payment.CardPayment;
import oms.payment.CashPayment;
import oms.payment.PaymentMethod;
import oms.payment.PaymentMethodFactory;
import oms.vehicle.Bicycle;
import oms.vehicle.Motorized;
import oms.vehicle.Truck;
import oms.vehicle.Vehicle;

/**
 * Unit tests for the Payment Strategy and Vehicle hierarchy subsystem
 * (Member A). Each test is traced to the functional requirement it protects,
 * demonstrating that the refactoring preserves behaviour (F3) while improving
 * the design (F8, F10, F11).

 */
@DisplayName("Payment Strategy and Vehicle Hierarchy")
class PaymentAndVehicleTest {

    /** BigDecimal comparison that ignores scale (185.0 == 185.00). */
    private static void assertMoneyEquals(final String expected, final BigDecimal actual) {
        assertEquals(0, new BigDecimal(expected).compareTo(actual),
                () -> "expected " + expected + " but was " + actual);
    }

    // ---------- F3: processing fee by payment type ----------

    @Test
    @DisplayName("F3: CARD payment adds a $5 processing fee")
    void cardPaymentAddsFiveDollarFee() {
        final PaymentMethod card = new CardPayment();
        assertMoneyEquals("185.00", card.applyFee(new BigDecimal("180.00")));
    }

    @Test
    @DisplayName("F3: CASH payment adds no fee")
    void cashPaymentAddsNoFee() {
        final PaymentMethod cash = new CashPayment();
        assertMoneyEquals("180.00", cash.applyFee(new BigDecimal("180.00")));
    }

    // ---------- F11: open for new payment types ----------

    @Test
    @DisplayName("F11: factory resolves the built-in strategies")
    void factoryResolvesRegisteredTypes() {
        assertEquals("CARD", PaymentMethodFactory.forType("card").type());
        assertEquals("CASH", PaymentMethodFactory.forType("CASH").type());
    }

    @Test
    @DisplayName("F11: a new payment type is added without editing existing classes")
    void newPaymentTypeCanBeAddedWithoutModifyingExistingClasses() {
        final PaymentMethod paypal = new PaymentMethod() {
            @Override
            public BigDecimal applyFee(final BigDecimal amount) {
                return amount.add(new BigDecimal("2.00"));
            }

            @Override
            public String type() {
                return "PAYPAL";
            }
        };
        PaymentMethodFactory.register(paypal);
        assertMoneyEquals("102.00",
                PaymentMethodFactory.forType("PAYPAL").applyFee(new BigDecimal("100.00")));
    }

    @Test
    @DisplayName("F11: an unregistered payment type is rejected")
    void unknownPaymentTypeIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> PaymentMethodFactory.forType("BITCOIN"));
    }

    // ---------- F8: no subclass inherits behaviour it cannot perform ----------

    @Test
    @DisplayName("F8: a Bicycle is not Motorized and cannot start an engine")
    void bicycleIsNotMotorized() {
        final Vehicle bicycle = new Bicycle();
        assertFalse(bicycle instanceof Motorized);
    }

    @Test
    @DisplayName("F8: a Truck is Motorized and can start its engine")
    void truckIsMotorizedAndCanStartEngine() {
        final Truck truck = new Truck();
        assertTrue(truck instanceof Motorized);
        truck.startEngine();
        assertTrue(truck.isEngineRunning());
    }

    // ---------- F10: new vehicle type needs no dedicated handler ----------

    @Test
    @DisplayName("F10: mixed fleet is processed polymorphically with no handler class")
    void vehiclesAreProcessedPolymorphicallyWithoutHandler() {
        final Vehicle[] fleet = { new Bicycle(), new Truck() };
        for (final Vehicle vehicle : fleet) {
            assertDoesNotThrow(vehicle::move);
            assertNotNull(vehicle.describe());
        }
    }
}

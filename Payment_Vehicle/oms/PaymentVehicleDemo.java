package oms;

import java.math.BigDecimal;
import java.util.List;


public final class PaymentVehicleDemo {

    public static void main(String[] args) {
        BigDecimal base = new BigDecimal("180.00");

        System.out.println("== Payment fees (F3, F11) ==");
        List<PaymentMethod> methods = List.of(new CardPayment(), new CashPayment(), new PayPalPayment());
        for (PaymentMethod method : methods) {
            System.out.printf("%-7s %s -> %s%n", method.displayName(), base, method.applyFee(base));
        }

        System.out.println("\n== Vehicles dispatched polymorphically, no handler class (F10) ==");
        List<Vehicle> fleet = List.of(
                new Bicycle("City Cruiser"),
                new Truck("Volvo FH"),
                new Motorcycle("Yamaha MT-07"));
        for (Vehicle vehicle : fleet) {
            String status = vehicle + " is " + vehicle.describeMotion();
            if (vehicle instanceof Motorized motorized) {   // only motorised vehicles start an engine (F8)
                motorized.startEngine();
                status += " (engine running: " + motorized.isEngineRunning() + ")";
            }
            System.out.println(status);
        }
    }
}

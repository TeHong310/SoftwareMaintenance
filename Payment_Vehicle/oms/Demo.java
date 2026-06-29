package oms;

import java.util.List;

public final class MemberADemo {
    public static void main(String[] args) {
        // --- Payment strategy (F3, F11) ---
        double base = 180.0;
        for (PaymentMethod pm : List.of(new CardPayment(), new CashPayment(), new PayPalPayment())) {
            System.out.println(pm.name() + " -> " + pm.applyFee(base));
        }

        // --- Vehicles dispatched polymorphically, NO handler class (F10) ---
        List<Vehicle> fleet = List.of(new Bicycle(), new Truck(), new Motorcycle());
        for (Vehicle v : fleet) {
            v.move();
            if (v instanceof Motorized m) { // only motorised vehicles can start an engine (F8)
                m.startEngine();
            }
        }
    }
}

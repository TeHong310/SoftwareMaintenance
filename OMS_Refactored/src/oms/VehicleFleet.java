package oms;

import java.util.List;

/**
 * F10: new vehicle types need NO separate handler class (the old TruckHandler
 * smell is removed). Any Vehicle is processed polymorphically through move().
 */
public final class VehicleFleet {
    public void operate(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            v.move();
            if (v instanceof Engine engine) {
                engine.startEngine();
            }
        }
    }
}

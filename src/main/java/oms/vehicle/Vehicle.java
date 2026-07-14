package oms.vehicle;

/**
 * Base class for all vehicle.
 *
 * <p>Only put behaviour that every vehicle got. Engine stuff not here,
 * so non-motorised vehicle don't get force to inherit something they
 * cannot do (F8, Liskov Substitution Principle).</p>
 */
public abstract class Vehicle {

    /**
     * Do this vehicle main transport action.
     *
     * <p>Each vehicle write its own version, so add new vehicle type just
     * extend this class, no need any TruckHandler-style class (F10,
     * Open/Closed Principle).</p>
     */
    public abstract void move();

    /**
     * Returns a human-readable label for this vehicle type.
     *
     * @return the vehicle description, e.g. {@code "Truck"}
     */
    public abstract String describe();
}

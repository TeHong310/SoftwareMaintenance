package oms.vehicle;

/**
 * Base type for every vehicle handled by the system.
 *
 * <p>The class declares only behaviour that <em>all</em> vehicles share.
 * Engine-related behaviour is deliberately excluded, so a non-motorised
 * vehicle is never forced to inherit an operation it cannot honour
 * (Requirement F8, Liskov Substitution Principle).</p>

 */
public abstract class Vehicle {

    /**
     * Performs this vehicle's primary transport operation.
     *
     * <p>Each concrete vehicle supplies its own implementation, so a new
     * vehicle type is added simply by extending this class &mdash; no external
     * {@code TruckHandler}-style class is required (Requirement F10,
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

package oms.vehicle;

/**
 * Interface for vehicle that got engine.
 *
 * <p>Only motorised vehicle implement this. Keep startEngine() out of
 * {@link Vehicle} so non-motorised type like {@link Bicycle} don't get
 * a method they cannot do (F8, Interface Segregation and Liskov
 * Substitution Principle).</p>
 */
public interface Motorized {

    /** Starts the vehicle's engine. */
    void startEngine();
}

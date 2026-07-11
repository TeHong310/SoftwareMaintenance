package oms.vehicle;

/**
 * Capability interface for vehicles that own an engine.
 *
 * <p>Only motorised vehicles implement this interface. Keeping the
 * engine-start operation out of {@link Vehicle} prevents non-motorised types
 * such as {@link Bicycle} from exposing a {@code startEngine()} method they
 * cannot perform (Requirement F8, Interface Segregation and Liskov
 * Substitution Principles).</p>
 *
 * @author Member A
 */
public interface Motorized {

    /** Starts the vehicle's engine. */
    void startEngine();
}

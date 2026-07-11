package oms.vehicle;

/**
 * Human-powered vehicle.
 *
 * <p>Intentionally does <em>not</em> implement {@link Motorized}, and therefore
 * exposes no {@code startEngine()} operation, unlike the original design in
 * which {@code Bicycle} inherited an engine it could not start
 * (Requirement F8).</p>
 *
 * @author Member A
 */
public final class Bicycle extends Vehicle {

    @Override
    public void move() {
        System.out.println("Bicycle is pedalling.");
    }

    @Override
    public String describe() {
        return "Bicycle";
    }
}

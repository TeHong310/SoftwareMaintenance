package oms.vehicle;

/**
 * Human-powered vehicle.
 *
 * <p>On purpose don't implement {@link Motorized}, so no startEngine()
 * here. Different from old design where Bicycle inherit engine it
 * cannot even start (F8).</p>
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

package oms.vehicle;

/**
 * Motorised goods vehicle.
 *
 * <p>Implement {@link Motorized} because got engine, and write own
 * {@link #move()} so no need TruckHandler class to run it (F8, F10).</p>
 */
public final class Truck extends Vehicle implements Motorized {

    /** Tracks whether the engine has been started. */
    private boolean engineRunning;

    @Override
    public void startEngine() {
        this.engineRunning = true;
        System.out.println("Truck engine started.");
    }

    @Override
    public void move() {
        if (!engineRunning) {
            startEngine();
        }
        System.out.println("Truck is driving.");
    }

    @Override
    public String describe() {
        return "Truck";
    }

    /**
     * Reports engine state; exposed primarily as a test hook.
     *
     * @return {@code true} if the engine is currently running
     */
    public boolean isEngineRunning() {
        return engineRunning;
    }
}

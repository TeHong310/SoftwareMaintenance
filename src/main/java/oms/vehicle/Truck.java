package oms.vehicle;

/**
 * Motorised goods vehicle.
 *
 * <p>Implements {@link Motorized} because it owns an engine, and defines its
 * own {@link #move()} behaviour so that no dedicated {@code TruckHandler} class
 * is needed to operate it (Requirements F8 and F10).</p>

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

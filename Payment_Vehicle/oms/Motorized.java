package oms;


public abstract class Motorized extends Vehicle {

    private boolean engineRunning;

    protected Motorized(String model) {
        super(model);
    }

 
    public void startEngine() {
        if (engineRunning) {
            throw new IllegalStateException(getModel() + " engine is already running");
        }
        engineRunning = true;
    }

    /** Stops the engine. Stopping an already-stopped engine is a no-op. */
    public void stopEngine() {
        engineRunning = false;
    }

    public boolean isEngineRunning() {
        return engineRunning;
    }
}

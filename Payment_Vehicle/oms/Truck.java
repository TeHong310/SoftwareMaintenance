package oms.vehicle;

public final class Truck extends Vehicle implements Motorized {

    // tracks whether the engine has been started
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

    public boolean isEngineRunning() {
        return engineRunning;
    }
}

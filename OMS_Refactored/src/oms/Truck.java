package oms;

/** Truck genuinely has an engine, so it opts into the Engine capability. */
public final class Truck extends Vehicle implements Engine {
    @Override
    public void move() {
        System.out.println("Truck is driving");
    }

    @Override
    public void startEngine() {
        System.out.println("Truck engine started");
    }
}

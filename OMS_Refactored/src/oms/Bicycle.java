package oms;

/** F8: a Bicycle does NOT implement Engine, so it has no startEngine() to misuse. */
public final class Bicycle extends Vehicle {
    @Override
    public void move() {
        System.out.println("Bicycle is pedalling");
    }
}

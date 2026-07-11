package oms.vehicle;

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

package oms;

public final class Truck extends Motorized {
    @Override
    public void move() {
        System.out.println("Truck is driving");
    }

    @Override
    public void startEngine() {
        System.out.println("Truck engine started");
    }
}

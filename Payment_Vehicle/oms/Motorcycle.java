package oms;

public final class Motorcycle extends Motorized {
    @Override
    public void move() {
        System.out.println("Motorcycle is riding");
    }

    @Override
    public void startEngine() {
        System.out.println("Motorcycle engine started");
    }
}

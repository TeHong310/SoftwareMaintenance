package oms;


public final class Bicycle extends Vehicle {

    public Bicycle(String model) {
        super(model);
    }

    @Override
    public String describeMotion() {
        return "pedalling";
    }
}

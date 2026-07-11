package oms;


public final class Motorcycle extends Motorized {

    public Motorcycle(String model) {
        super(model);
    }

    @Override
    public String describeMotion() {
        return "riding";
    }
}

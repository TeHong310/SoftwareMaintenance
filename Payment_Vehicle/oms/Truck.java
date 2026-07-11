package oms;

 class Truck extends Motorized {

    public Truck(String model) {
        super(model);
    }

    @Override
    public String describeMotion() {
        return "driving";
    }
}

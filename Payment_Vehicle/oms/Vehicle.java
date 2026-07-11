package oms;

import java.util.Objects;


public abstract class Vehicle {

    private final String model;

    /**
     * @param model the vehicle's model name; must not be {@code null} or blank
     */
    protected Vehicle(String model) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("model must not be null or blank");
        }
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    /**
     * @return a description of how this vehicle moves (e.g. "driving", "pedalling")
     */
    public abstract String describeMotion();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + model + "]";
    }
}

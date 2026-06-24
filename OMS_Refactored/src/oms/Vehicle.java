package oms;

/**
 * Base type holds only behaviour every vehicle truly has. Engine capability is
 * NOT here, so no subclass is forced to inherit a behaviour it cannot perform
 * (F8 / Liskov Substitution Principle).
 */
public abstract class Vehicle {
    public abstract void move();
}

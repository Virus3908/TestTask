package model;

public class Resource {

    private int capacity;
    private double speed;

    public Resource(int capacity, double speed) {
        this.capacity = capacity;
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getSpeed() {
        return speed;
    }

    public double getSpeedMetersSecond() {
        return speed * 1000 / 3600;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "capacity=" + capacity +
                ", speed=" + speed +
                '}';
    }

}

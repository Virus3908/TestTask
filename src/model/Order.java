package model;

import java.time.LocalTime;

public class Order implements Comparable {

    private int weight;
    private double latitude;
    private double longitude;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime shippingTime;
    private LocalTime dischargingTime;

    public Order(int weight, double latitude, double longitude, LocalTime startTime, LocalTime endTime, LocalTime shippingTime, LocalTime dischargingTime) {
        this.weight = weight;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shippingTime = shippingTime;
        this.dischargingTime = dischargingTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(LocalTime shippingTime) {
        this.shippingTime = shippingTime;
    }

    public LocalTime getDischargingTime() {
        return dischargingTime;
    }

    public void setDischargingTime(LocalTime dischargingTime) {
        this.dischargingTime = dischargingTime;
    }


    @Override
    public int compareTo(Object o) {
        Order order = (Order) o;
        return this.startTime.compareTo(order.getStartTime());
    }

    @Override
    public String toString() {
        return "Order{" +
                "weight=" + weight +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", shippingTime=" + shippingTime +
                ", dischargingTime=" + dischargingTime +
                '}';
    }


}

package model;

import java.time.LocalTime;

public class DistributionCenter {

    private double latitude;
    private double longitude;
    private int sizeResources;
    private LocalTime startTime;
    private LocalTime endTime;

    public DistributionCenter(double latitude, double longitude, int sizeResources, LocalTime startTime, LocalTime endTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.sizeResources = sizeResources;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public int getSizeResources() {
        return sizeResources;
    }

    public void setSizeResources(int sizeResources) {
        this.sizeResources = sizeResources;
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

    @Override
    public String toString() {
        return "DistributionCenter{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", sizeResources=" + sizeResources +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

}

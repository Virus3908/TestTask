package tasks;

import metrics.Distance;
import model.DistributionCenter;
import model.Order;
import model.Resource;

import java.time.LocalTime;
import java.util.Arrays;

public class Flow {

    private DistributionCenter distributionCenter;
    private Order[] orders;
    private Resource resource;

    private LocalTime start;
    private LocalTime[][] points;
    private LocalTime end;

    public Flow(DistributionCenter distributionCenter, Order[] orders, Resource resource) {
        this.distributionCenter = distributionCenter;
        this.orders = orders;
        this.resource = resource;
        this.start = null;
        this.points = null;
        this.end = null;
        this.calculate();
    }

    public void calculate() {
        points = new LocalTime[orders.length][2];
        LocalTime time = LocalTime.of(0, 0);
        long timeRoute;

        start = distributionCenter.getStartTime();
        for (int i = 0; i < orders.length; i++) {
            time = addTime(time, orders[i].getShippingTime());
        }

        timeRoute = (long) (Distance.calculateCrowFlight(distributionCenter.getLatitude(), distributionCenter.getLongitude(), orders[0].getLatitude(), orders[0].getLongitude()) / resource.getSpeedMetersSecond());

        time = time.plusSeconds(timeRoute);
        start = subTime(orders[0].getStartTime(), time);

        if (start.compareTo(distributionCenter.getStartTime()) == -1) {
            start = distributionCenter.getStartTime();
        }

        points[0][0] = addTime(start, time);
        points[0][1] = addTime(points[0][0], orders[0].getDischargingTime());
        for (int i = 1; i < orders.length; i++) {
            timeRoute = (long) (Distance.calculateCrowFlight(orders[i - 1].getLatitude(), orders[i - 1].getLongitude(), orders[i].getLatitude(), orders[i].getLongitude()) / resource.getSpeedMetersSecond());
            points[i][0] = points[i - 1][1].plusSeconds(timeRoute);
            if (points[i][0].compareTo(orders[i].getStartTime()) == -1) {
                points[i][0] = orders[i].getStartTime();
            }
            points[i][1] = addTime(points[i][0], orders[i].getDischargingTime());
        }
        timeRoute = (long) (Distance.calculateCrowFlight(distributionCenter.getLatitude(), distributionCenter.getLongitude(), orders[orders.length - 1].getLatitude(), orders[orders.length - 1].getLongitude()) / resource.getSpeedMetersSecond());
        end = points[orders.length - 1][1].plusSeconds(timeRoute);
    }


    public void print() {
        System.out.println("Start = " + start.toString());
        for (int i = 0; i < points.length; i++) {
            System.out.println("Point " + i + " : " + points[i][0].toString() + " - " + points[i][1].toString());
        }
        System.out.println("End = " + end.toString());
    }

    private LocalTime addTime(LocalTime timeOne, LocalTime timeTwo) {
        LocalTime time = timeOne.plusSeconds(timeTwo.getSecond());
        time = time.plusMinutes(timeTwo.getMinute());
        time = time.plusHours(timeTwo.getHour());
        return time;
    }

    private LocalTime subTime(LocalTime timeOne, LocalTime timeTwo) {
        LocalTime time = timeOne.minusSeconds(timeTwo.getSecond());
        time = time.minusMinutes(timeTwo.getMinute());
        time = time.minusHours(timeTwo.getHour());
        return time;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime[][] getPoints() {
        return points;
    }

    public LocalTime getEnd() {
        return end;
    }

    public DistributionCenter getDistributionCenter() {
        return distributionCenter;
    }

    public void setDistributionCenter(DistributionCenter distributionCenter) {
        this.distributionCenter = distributionCenter;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Start = " + start.toString() + "\n");
        for (int i = 0; i < points.length; i++) {
            str.append("Point " + i + " : " + points[i][0].toString() + " - " + points[i][1].toString() + "\n");
        }
        str.append("End = " + end.toString());
        return str.toString();
    }
}

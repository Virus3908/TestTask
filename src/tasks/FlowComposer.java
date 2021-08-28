package tasks;

import metrics.Distance;
import model.DistributionCenter;
import model.Order;
import model.Resource;

import java.time.LocalTime;

public class FlowComposer {
    private DistributionCenter distributionCenter;
    private Order[] orders;
    private Resource resource;

    public FlowComposer(DistributionCenter distributionCenter, Order[] orders, Resource resource) {
        this.distributionCenter = distributionCenter;
        this.orders = orders;
        this.resource = resource;
    }

    public Order[] calculate() {
        int lenght = orders.length;
        Order[] ans = new Order[lenght];
        int index = -1;
        long timeRoute;
        double latitude = distributionCenter.getLatitude();
        double longitude = distributionCenter.getLongitude();
        LocalTime time = distributionCenter.getStartTime();
        LocalTime timeStep;//= LocalTime.of(0,0);
        LocalTime minTime = LocalTime.of(23, 59, 59);

        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < lenght; j++) {
                if (orders[j] != null) {
                    timeRoute = (long) (Distance.calculateCrowFlight(latitude, longitude, orders[j].getLatitude(), orders[j].getLongitude()) / resource.getSpeedMetersSecond());
                    timeStep = time;
                    timeStep = timeStep.plusSeconds(timeRoute);
                    if (timeStep.compareTo(orders[j].getStartTime()) == -1) {
                        timeStep = orders[j].getStartTime();
                    }

                    if (minTime.compareTo(timeStep) == 1) {
                        minTime = timeStep;
                        index = j;
                    }
                }
            }
            time = addTime(minTime, time);
            latitude = orders[index].getLatitude();
            longitude = orders[index].getLongitude();
            ans[i] = orders[index];
            orders[index] = null;
            minTime = LocalTime.of(23, 59, 59);
        }

        return ans;
    }

    private LocalTime addTime(LocalTime timeOne, LocalTime timeTwo) {
        LocalTime time = timeOne.plusSeconds(timeTwo.getSecond());
        time = time.plusMinutes(timeTwo.getMinute());
        time = time.plusHours(timeTwo.getHour());
        return time;
    }


}

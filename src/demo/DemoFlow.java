package demo;

import model.DistributionCenter;
import model.Order;
import model.Resource;
import tasks.Flow;

import java.time.LocalTime;

public class DemoFlow {
    public static void main(String[] args) {
        DistributionCenter distributionCenter = new DistributionCenter(2, 2, 1, LocalTime.of(8, 0), LocalTime.of(22, 0));
        Resource resource = new Resource(20, 60);
        Order[] orders = new Order[1];
        orders[0] = new Order(1, 2, 50, LocalTime.of(9, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        Flow flow = new Flow(distributionCenter, orders, resource);
        flow.print();
        System.out.println("________________________________________");

        orders = new Order[3];
        orders[0] = new Order(1, 2.0001, 2.0001, LocalTime.of(8, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        orders[1] = new Order(1, 2.0001, 2.0009, LocalTime.of(8, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        orders[2] = new Order(1, 2.0002, 2.0001, LocalTime.of(8, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        flow.setOrders(orders);
        flow.calculate();
        flow.print();
        System.out.println("________________________________________");

        orders = new Order[3];
        orders[0] = new Order(1, 2.1, 2.1, LocalTime.of(9, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        orders[1] = new Order(1, 2.2, 2.2, LocalTime.of(8, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        orders[2] = new Order(1, 2.3, 2.3, LocalTime.of(10, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        flow.setOrders(orders);
        flow.calculate();
        flow.print();
        System.out.println("________________________________________");
    }
}

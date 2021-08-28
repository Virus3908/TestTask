package demo;

import model.DistributionCenter;
import model.Order;
import model.Resource;
import tasks.Flow;
import tasks.FlowComposer;


import java.time.LocalTime;

public class DemoFlowComposer {
    public static void main(String[] args) {
        DistributionCenter distributionCenter = new DistributionCenter(1, 1, 1, LocalTime.of(8, 0), LocalTime.of(22, 0));
        Resource resource = new Resource(20, 60);
        Order[] orders = new Order[3];
        orders = new Order[3];
        orders[0] = new Order(1, 2, 2, LocalTime.of(15, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        orders[1] = new Order(1, 3, 2.7, LocalTime.of(8, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        orders[2] = new Order(1, 2.5, 2.5, LocalTime.of(8, 0), LocalTime.of(22, 0), LocalTime.of(0, 5), LocalTime.of(0, 5));
        Flow flow = new Flow(distributionCenter, orders, resource);
        flow.calculate();
        flow.print();
        System.out.println("________________________________________");
        FlowComposer flowComposer = new FlowComposer(distributionCenter, orders, resource);
        flow.setOrders(flowComposer.calculate());
        flow.calculate();
        flow.print();
        System.out.println("________________________________________");

    }
}

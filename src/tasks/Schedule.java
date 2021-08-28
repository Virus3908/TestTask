package tasks;

import model.DistributionCenter;
import model.Order;
import model.Resource;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Schedule {

    private DistributionCenter distributionCenter;
    private Order[] orders;
    private Resource resource;
    private ArrayList<Order> canceleds;
    private ArrayList<Order>[] resourceOrders;

    public Schedule(DistributionCenter distributionCenter, Order[] orders, Resource resource) {
        this.distributionCenter = distributionCenter;
        this.orders = orders;
        this.resource = resource;
        canceleds = new ArrayList<>();
        calculate();
    }

    public void calculate() {
        Arrays.sort(orders);
        int sizeResources = distributionCenter.getSizeResources();
        resourceOrders = new ArrayList[sizeResources];
        for (int i = 0; i < resourceOrders.length; i++) {
            resourceOrders[i] = new ArrayList<>();
        }
        for (int i = 0, j = 0; i < orders.length; i++, j++) {
            if (j == sizeResources) j = 0;
            resourceOrders[j].add(orders[i]);
        }
        int sumWeight = 0;
        int sizeOrders;

        for (int i = 0; i < sizeResources; i++) {
            sizeOrders = resourceOrders[i].size();
            for (int j = 0; j < sizeOrders; j++) {
                sumWeight += resourceOrders[i].get(j).getWeight();
                if (sumWeight > resource.getCapacity()) {
                    for (int k = j; k < sizeOrders; k++) {
                        canceleds.add(resourceOrders[i].get(j));
                        resourceOrders[i].remove(j);
                    }
                }
            }
        }

        FlowComposer flowComposer;
        Flow flow;
        LocalTime[][] points;
        boolean flagExit = true;
        for (int i = 0; i < sizeResources; i++) {
            flowComposer = new FlowComposer(distributionCenter, resourceOrders[i].toArray(new Order[resourceOrders.length]), resource);
            flow = new Flow(distributionCenter, flowComposer.calculate(), resource);
            while (true) {
                if (flow.getEnd().compareTo(distributionCenter.getEndTime()) == 1 || flow.getEnd().compareTo(distributionCenter.getStartTime()) == -1) {
                    canceleds.add(resourceOrders[i].get(resourceOrders.length - 1));
                    resourceOrders[i].remove(resourceOrders.length - 1);
                } else {
                    break;
                }
                flowComposer = new FlowComposer(distributionCenter, resourceOrders[i].toArray(new Order[resourceOrders.length]), resource);
                flow = new Flow(distributionCenter, flowComposer.calculate(), resource);
            }

            while (true) {
                points = flow.getPoints();
                for (int j = 0; j < points.length; j++) {
                    if (points[j][0].compareTo(resourceOrders[i].get(j).getStartTime()) == -1 && points[j][1].compareTo(resourceOrders[i].get(j).getEndTime()) == -1) {
                        canceleds.add(resourceOrders[i].get(j));
                        resourceOrders[i].remove(j);
                        flagExit = false;
                        break;
                    }
                }
                if (flagExit) {
                    break;
                }
                flowComposer = new FlowComposer(distributionCenter, resourceOrders[i].toArray(new Order[resourceOrders.length]), resource);
                flow = new Flow(distributionCenter, flowComposer.calculate(), resource);
                flagExit = true;
            }

        }
    }

    public ArrayList<Order> getCanceleds() {
        return canceleds;
    }

    public ArrayList<Order>[] getResourceOrders() {
        return resourceOrders;
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
        StringBuilder str = new StringBuilder("Schedule{" + "\n" + "canceleds" + "\n");
        for (int i = 0; i < canceleds.size(); i++) {
            str.append(canceleds.get(i).toString() + "\n");
        }
        str.append("Flos" + "\n");
        FlowComposer flowComposer;
        Flow flow;
        for (int i = 0; i < resourceOrders.length; i++) {
            str.append("Resource - " + i);
            flowComposer = new FlowComposer(distributionCenter, resourceOrders[i].toArray(new Order[resourceOrders.length]), resource);
            flow = new Flow(distributionCenter, flowComposer.calculate(), resource);
            str.append(flow.toString() + "\n");
        }
        str.append('}' + "\n");
        return str.toString();
    }
}






























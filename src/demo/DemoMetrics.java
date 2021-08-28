package demo;

import metrics.Distance;

public class DemoMetrics {

    public static void main(String[] args) {

        double latOne = 5;
        double longOne = 5;
        double latTwo = 10;
        double longTwo = 10;

        System.out.println("Crow Flight = " + Distance.calculateCrowFlight(latOne, longOne, latTwo, longTwo));
        //782999.6273175813
        System.out.println("OSMR        = " + Distance.calculateOSMRapi(latOne, longOne, latTwo, longTwo));
        //1133846.7

    }

}

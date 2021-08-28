package metrics;


import java.io.IOException;
import java.net.URL;

public class Distance {

    public static double EARTH_RADIUS = 6371009;

    public static double calculateCrowFlight(double latitudePointOne, double longitudePointOne, double latitudePointTwo, double longitudePointTwo) {

        latitudePointOne = Math.toRadians(latitudePointOne);
        longitudePointOne = Math.toRadians(longitudePointOne);
        latitudePointTwo = Math.toRadians(latitudePointTwo);
        longitudePointTwo = Math.toRadians(longitudePointTwo);

        double deltaLongitude = longitudePointOne - longitudePointTwo;
        double cosLatOne = Math.cos(latitudePointOne);
        double cosLatTwo = Math.cos(latitudePointTwo);
        double sinLatOne = Math.sin(latitudePointOne);
        double sinLatTwo = Math.sin(latitudePointTwo);
        double cosDelta = Math.cos(deltaLongitude);
        double sinDelta = Math.sin(deltaLongitude);

        double y = Math.sqrt((cosLatTwo * cosLatTwo * sinDelta * sinDelta) + (cosLatOne * sinLatTwo - sinLatOne * cosLatTwo * cosDelta) * (cosLatOne * sinLatTwo - sinLatOne * cosLatTwo * cosDelta));
        double x = sinLatOne * sinLatTwo + cosLatOne * cosLatTwo * cosDelta;


        return EARTH_RADIUS * Math.atan2(y, x);
    }

    public static double calculateOSMRapi(double latitudePointOne, double longitudePointOne, double latitudePointTwo, double longitudePointTwo) {

        int state = -11;
        char current;
        StringBuffer ans = new StringBuffer();
        char[] response;

        try {
            response = org.apache.commons.io.IOUtils.toString(new URL("http://router.project-osrm.org/route/v1/driving/" + latitudePointOne + "," + longitudePointOne + ";" + latitudePointTwo + "," + longitudePointTwo)).toCharArray();
        } catch (IOException e) {
            return -1;
        }

        for (int i = 0; i < response.length; i++) {
            current = response[i];

            switch (state) {
                case -11:
                    if (current == 'r') state = -10;
                    else state = -11;
                    break;
                case -10:
                    if (current == 'o') state = -9;
                    else state = -11;
                    break;
                case -9:
                    if (current == 'u') state = -8;
                    else state = -11;
                    break;
                case -8:
                    if (current == 't') state = -7;
                    else state = -11;
                    break;
                case -7:
                    if (current == 'a') state = -6;
                    else state = -11;
                    break;
                case -6:
                    if (current == 'b') state = -5;
                    else state = -11;
                    break;
                case -5:
                    if (current == 'i') state = -4;
                    else state = -11;
                    break;
                case -4:
                    if (current == 'l') state = -3;
                    else state = -11;
                    break;
                case -3:
                    if (current == 'i') state = -2;
                    else state = -11;
                    break;
                case -2:
                    if (current == 't') state = -1;
                    else state = -11;
                    break;
                case -1:
                    if (current == 'y') state = 0;
                    else state = -11;
                    break;

                case 0:
                    if (current == 'd') state = 1;
                    break;
                case 1:
                    if (current == 'i') state = 2;
                    else state = 0;
                    break;
                case 2:
                    if (current == 's') state = 3;
                    else state = 0;
                    break;
                case 3:
                    if (current == 't') state = 4;
                    else state = 0;
                    break;
                case 4:
                    if (current == 'a') state = 5;
                    else state = 0;
                    break;
                case 5:
                    if (current == 'n') state = 6;
                    else state = 0;
                    break;
                case 6:
                    if (current == 'c') state = 7;
                    else state = 0;
                    break;
                case 7:
                    if (current == 'e') state = 8;
                    else state = 0;
                    break;
                case 8:
                    if (Character.isDigit(current) || current == '.') ans.append(current);
                    else if (current == '}' || current == ',') state = 9;
                    break;
            }
            if (state == 9) break;
        }

        return Double.parseDouble(ans.toString());
    }
}

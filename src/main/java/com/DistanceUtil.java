package com;

import com.entity.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class DistanceUtil {
    private static double DUBLIN_LON = 53.339428;
    private static double DUBLIN_LAT = -6.257664;
    private static double DUBLIN_LAT_RAD = Math.toRadians(DUBLIN_LAT);
    private static double DUBLIN_LON_RAD = Math.toRadians(DUBLIN_LON);
    private static double R = 6371;
    private static final double DISTANCE = 100;


    public static List<String> getClosest(HashMap<String, TreeSet<Customer>> customersFromEachFile) {

        List<String> result = new ArrayList<>();
        for (String fileName : customersFromEachFile.keySet()) {
            result.add(fileName + ":");
            for (Customer a : customersFromEachFile.get(fileName)) {
                if(calculate(a.getLatitude(), a.getLongitude())) {
                    result.add(a.getId() + " " + a.getName());
                }
            }
        }
        return result;
    }

    private static boolean calculate(double latitude, double longitude) {
        double customerLatRad = Math.toRadians(latitude);
        double customerLonRad = Math.toRadians(longitude);

        double deltaLatRad = Math.abs(DUBLIN_LAT_RAD - customerLatRad);
        double deltaLonRad = Math.abs(DUBLIN_LON_RAD - customerLonRad);

        double val = Math.pow(Math.sin(deltaLatRad/2), 2) +
               Math.cos(customerLatRad) * Math.cos(DUBLIN_LAT_RAD) * Math.pow(Math.sin(deltaLonRad/2), 2);
        double deltaDistance = 2 * Math.asin(Math.sqrt(val));
        double calcDistance = deltaDistance * R;

        return calcDistance > DISTANCE ? false : true;
    }
}

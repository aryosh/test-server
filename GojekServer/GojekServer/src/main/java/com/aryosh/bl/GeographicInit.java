/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aryosh.bl;

import com.aryosh.var.DriverLocation;
import com.aryosh.var.GeoLocation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author AryoSH XLINK
 */
public class GeographicInit
{
    private static HashMap<Integer, GeoLocation> driverLoc = new HashMap<Integer, GeoLocation>();

    public HashMap<Integer, GeoLocation> getDriverLoc()
    {
        return driverLoc;
    }

    public void setDriverLoc(HashMap<Integer, GeoLocation> driverLoc)
    {
        GeographicInit.driverLoc = driverLoc;
    }   
    
    public void generateDriverLoc()
    {
        int maximumPoints = 50000;
        GeographicPoint newLocation;

        GeographicSquare mockCityGeo = new GeographicSquare(
                new GeographicPoint(-6.16, 106.80), new GeographicPoint(-6.18,
                        106.83));
        String cityName = "Google Maps city name";

        Random randomGenerator = new Random();

        double deltaLong = mockCityGeo.getDeltaLongitude();
        double deltaLat = mockCityGeo.getDeltaLatitude();
        driverLoc.clear();
        for (int i = 0; i < maximumPoints; i++)
        {
            newLocation = new GeographicPoint(
                    mockCityGeo.getLowerLeftLatitude() + deltaLat
                    * randomGenerator.nextDouble(),
                    mockCityGeo.getLowerLeftLongitude() + deltaLong
                    * randomGenerator.nextDouble());
            //System.out.println("Put - Latitude : " + newLocation.getLatitude() + ", Longitude : " + newLocation.getLongitude());
            driverLoc.put(i, new GeoLocation(newLocation.getLatitude(), newLocation.getLongitude()));
            // TODO test to see of newLocation is in the city
        }
        
    }
    
    public ArrayList<DriverLocation> compare(double currLatitude, double currLongitude, int currRadius, int currLimit){
        //compare to original longitude
        int limit = 0;
        ArrayList<DriverLocation> ret = new ArrayList<DriverLocation>();
        for (int i = 0; i < driverLoc.size(); i++)
        {
            double lat = driverLoc.get(i).getLatitude();
            double lon = driverLoc.get(i).getLongitude();
            double radius = distFrom(lat, lon, currLatitude, currLongitude) * 1000;
            if (currRadius > radius)
            {
                limit++;
                ret.add(new DriverLocation(i, lat, lon, (int) radius));
                if(limit == currLimit){
                    break;
                }
            }
        }
        return ret;
    }

    public static double distFrom(double lat1, double lng1, double lat2, double lng2)
    {
        double earthRadius = 6371.0; // kilometres (or 3958.75 miles)
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist;
    }
}

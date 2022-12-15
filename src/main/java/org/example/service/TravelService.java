package org.example.service;

import org.example.info.CityInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TravelService {
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if (cities.contains(cityInfo)) {
            throw new IllegalArgumentException("city already exists");
        }

        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     */
    public void remove(String cityName) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equalsIgnoreCase(cityName)) {
                cities.remove(i);
                return;
            }
        }

        System.out.println("city doesn't exist");
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream()
                .map(CityInfo::getName)
                .collect(Collectors.toList());
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     */
    public int getDistance(String srcCityName, String destCityName) {
        final double EARTH_RADIUS = 6372.795;

        double lat1 = -999;
        double lat2 = -999;
        double long1 = -999;
        double long2 = -999;

        for (CityInfo city : cities) {
            if (city.getName().equalsIgnoreCase(srcCityName)) {
                lat1 = city.getPosition().getLatitude();
                long1 = city.getPosition().getLongitude();
            } else if (city.getName().equalsIgnoreCase(destCityName)) {
                lat2 = city.getPosition().getLatitude();
                long2 = city.getPosition().getLongitude();
            }
        }

        if (lat1 == -999 || lat2 == -999) {
            System.out.println("city doesn't exist");
        }

        double cosLat1 = Math.cos(lat1);
        double cosLat2 = Math.cos(lat2);
        double sinLat1 = Math.sin(lat1);
        double sinLat2 = Math.sin(lat2);
        double delta = long2 - long1;
        double cosDelta = Math.cos(delta);
        double sinDelta = Math.sin(delta);

        double y = Math.sqrt(Math.pow(cosLat1 * sinDelta, 2) + Math.pow(cosLat1 * sinLat2 - sinLat1 * cosLat2 * cosDelta, 2));
        double x = sinLat1 * sinLat2 + cosLat1 * cosLat2 * cosDelta;

        double ad = Math.atan2(y, x);
        double dist = Math.ceil(ad * EARTH_RADIUS);

        return (int) dist;
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        List<String> citiesNearNames = new ArrayList<>();

        for (CityInfo city : cities) {
            if (!cityName.equals(city.getName())) {
                if (getDistance(cityName, city.getName()) <= radius) {
                    citiesNearNames.add(city.getName());
                }
            }
        }

        return citiesNearNames;
    }

    @Override
    public String toString() {
        return "TravelService{" +
                "cities=" + cities +
                '}';
    }
}

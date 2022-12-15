package org.example;

import org.example.info.CityInfo;
import org.example.info.GeoPosition;
import org.example.service.TravelService;

public class App {
    public static void main(String[] args) {
        TravelService service = new TravelService();
        CityInfo NSK = new CityInfo("NSK", new GeoPosition("55(01'00'')", "82(55'00'')"));
        service.add(NSK);
        CityInfo OMSK = new CityInfo("OMSK", new GeoPosition("54(58'00'')", "73(23'00'')"));
        service.add(OMSK);
        CityInfo SPB = new CityInfo("SPB", new GeoPosition("59(57'00'')", "30(19'00'')"));
        service.add(SPB);

        System.out.println(service.getCitiesNear("SPB", 1000));
        System.out.println(service.citiesNames());
        service.remove("SPB");
        System.out.println(service.citiesNames());
        System.out.println(service.getDistance("NSK", "OMSK"));
        System.out.println(service.getCitiesNear("NSK", 1000));
    }
}

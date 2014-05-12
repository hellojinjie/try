package com.conger.test.r1;

import java.io.IOException;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class GeoTest {

    public static void main(String[] args) throws IOException {
        LookupService lookupService = new LookupService("GeoIPCity.dat", LookupService.GEOIP_MEMORY_CACHE);
        Location c = lookupService.getLocation("180.168.179.98");
        //Country c = lookupService.getCountry("71.53.65.6");
        System.out.println(c.countryName);
    }
}

package com.conger.test;

import java.io.IOException;

import com.maxmind.geoip.Country;
import com.maxmind.geoip.LookupService;

public class GeoTest {

    public static void main(String[] args) throws IOException {
        LookupService lookupService = new LookupService("GeoIPCity.dat", LookupService.GEOIP_MEMORY_CACHE);
        //Country c = lookupService.getCountry("172.4.161.84");
        Country c = lookupService.getCountry("71.53.65.6");
        System.out.println(c.getCode());
    }
}

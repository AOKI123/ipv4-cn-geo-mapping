package com.github.aoki123.ip.mapping.test;

import com.github.aoki123.ip.mapping.GeoMappingService;
import com.github.aoki123.ip.mapping.support.GeoData;

import java.io.IOException;

/**
 * @author jiahan
 * @date 2019-07-12 17:57
 */
public class Tester {

    public static void main(String[] args) {
        try {
            GeoMappingService service = new GeoMappingService();
            GeoData geo = service.mappingGeo("116.228.188.206");
            System.out.println(geo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

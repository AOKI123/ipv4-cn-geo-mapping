package com.github.aoki123.ip.mapping;

import com.github.aoki123.ip.mapping.support.AddressNotFoundException;
import com.github.aoki123.ip.mapping.support.DatabaseReader;
import com.github.aoki123.ip.mapping.support.GeoData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author jiahan
 * @date 2019-07-12 17:45
 */
public class GeoMappingService {

    private DatabaseReader reader;

    public GeoMappingService() throws IOException {
        init();
    }

    /**
     * 将ipv4映射为地域信息
     *
     * @param ipv4 ipv4 address
     * @return geo
     * @throws AddressNotFoundException when ip mapping geo failed
     */
    public GeoData mappingGeo(String ipv4) throws AddressNotFoundException {
        return reader.city(ipv4);
    }

    private void init() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("geo.data");
        if (url == null) {
            throw new FileNotFoundException("geo.data");
        }
        File file = new File(url.getFile());
        reader = new DatabaseReader.Builder(file).build();
    }
}

package com.github.aoki123.ip.mapping.support;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import org.supercsv.cellprocessor.ConvertNullTo;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.Objects;

/**
 * 读取数据
 */
public class DatabaseReader {

    private ImmutableRangeMap<Long, City> geoData;

    LoadingCache<Long, City> ipCache;

    public DatabaseReader(ImmutableRangeMap<Long, City> geoData) throws IOException {
        this.geoData = geoData;
        ipCache = buildCache();
    }

    /**
     * 获取IP 地域数据
     *
     * @param ip 查询 IP
     * @return 正常情况：<br/>
     * 返回具体city信息 <br/>
     * 异常情况： <br/>
     * 没有找到ip具体的city信息, 抛出 AddressNotFoundException
     */
    public City city(String ip) {

        if (!IPUtils.isValidIPv4Address(ip)) {
            throw new AddressNotFoundException(String.format("IP[%s] is invalid.", ip));
        }

        try {
            long score = IPUtils.ipv4Score(ip);
            return ipCache.get(score);
        } catch (Exception e) {
            throw new AddressNotFoundException(String.format("Address ip [%s] not found.", ip));
        }

    }

    public static final class Builder {

        final File database;
        final InputStream stream;

        /**
         * @param database the GeoIP2 database file to use.
         */
        public Builder(File database) {
            this.database = database;
            this.stream = null;
        }

        /**
         * @return an instance of {@code DatabaseReader} created from the fields
         * set on this builder.
         * @throws IOException if there is an error reading the database
         */
        public DatabaseReader build() throws IOException, GeoException {

            try (Reader reader = new InputStreamReader(new FileInputStream(database), "UTF-8");
                 CsvBeanReader csv = new CsvBeanReader(reader, CsvPreference.STANDARD_PREFERENCE)) {

                ImmutableRangeMap.Builder<Long, City> geoBuilder = ImmutableRangeMap.builder();

                City city = null;
                String[] fields = new String[]{"startIp", "endIp", "countryId", "stateId", "cityId", "description"};
                CellProcessor[] process = new CellProcessor[]{
                        new ConvertNullTo(null, new ParseLong()),
                        new ConvertNullTo(null, new ParseLong()),
                        new ConvertNullTo(""), new ConvertNullTo(""),
                        new ConvertNullTo(""), new ConvertNullTo("")};
                while (Objects.nonNull(
                        city = csv.read(City.class, fields, process))) {
                    geoBuilder.put(Range.closed(city.getStartIp(), city.getEndIp()), city);
                }
                ImmutableRangeMap<Long, City> geoMap = geoBuilder.build();

                return new DatabaseReader(geoMap);
            } catch (IOException e) {
                throw e;
            } catch (Exception e) {
                throw new GeoException("Build ip data failed.", e);
            }

        }
    }

    /**
     * 构建缓存
     *
     * @return
     */
    private LoadingCache<Long, City> buildCache() {
        return CacheBuilder.newBuilder().build(new CacheLoader<Long, City>() {

            @Override
            public City load(Long ip) throws Exception {

                City city = geoData.get(ip);
                if (Objects.nonNull(city)) {
                    city.setIp(ip);
                    return city;
                } else {
                    return null;
                }

            }
        });

    }

}

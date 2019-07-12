# ipv4-cn-geo-mapping
IPV4和中国地域的映射

## 使用非常简单
```java
class Tester {
    
    public static void main(String[] args){
      GeoMappingService srv = new GeoMappingService();
      GeoData geo = srv.mapping("116.228.188.206");
      System.out.println(geo.getDescription());
      // ...console result..
      // 中国 (除港澳台)-上海市-上海市
    }
}

```

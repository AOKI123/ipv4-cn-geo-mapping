package com.github.aoki123.ip.mapping.support;

public class GeoData {

    private Long ip;

    private String countryId;

    private String stateId;

    private String cityId;

    private String description;

    public Long getIp() {
        return ip;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GeoData{" + "ip=" + ip + ", countryId='" + countryId + '\''
                + ", stateId='" + stateId + '\'' + ", cityId='" + cityId + '\''
                + ", description='" + description + '\'' + '}';
    }
}

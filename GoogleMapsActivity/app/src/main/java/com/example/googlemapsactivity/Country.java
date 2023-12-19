package com.example.googlemapsactivity;

public class Country {

    String name, capital, url, region, timezone, currency;
    String population;
    Double logitude, latitude;

    public Country(String name, String capital, String url, String region, String timezone, String currency, String population, Double latitude, Double logitude){
        this.name = name;
        this.capital = capital;
        this.url = url;
        this.region = region;
        this.timezone = timezone;
        this.currency = currency;
        this.population = population;
        this.latitude = latitude;
        this.logitude = logitude;
    }

    public Double getLogitude() {
        return logitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getUrl() {
        return url;
    }

    public String getRegion() {
        return region;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPopulation() {
        return population;
    }


}

package com.example.tisotlib.TLib;

public class Fly {
    String statusCode; //arrival/departure
    String flightCode;
    String provider;
    String estTime;
    String sheduledTime;
    String country;
    String city;

    public Fly(String statusCode, String flightCode, String provider, String estTime, String sheduledTime, String country, String city) {
        this.statusCode = statusCode;
        this.flightCode = flightCode;
        this.provider = provider;
        this.estTime = estTime;
        this.sheduledTime = sheduledTime;
        this.country = country;
        this.city = city;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getEstTime() {
        return estTime;
    }
    public String getFlightCode() {
        return flightCode;
    }
    public String getProvider() {
        return provider;
    }
    public String getSheduledTime() {
        return sheduledTime;
    }

}

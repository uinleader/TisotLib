package com.example.tisotlib.TLib;

public class Flight {
    String id;
    String statusCode; //arrival/departure
    String flightCode;
    String provider;
    String estTime;
    String sheduledTime;
    String country;
    String city;
    //String status;

    public Flight(String id, String statusCode, String flightCode, String provider, String estTime, String sheduledTime, String country, String city) {
        this.id = id;
        this.statusCode = statusCode;
        this.flightCode = flightCode;
        this.provider = provider;
        this.estTime = estTime;
        this.sheduledTime = sheduledTime;
        this.country = country;
        this.city = city;
        //this.status = status;
    }

    //public String getStatus() {
    //    return status;
    //}

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

    public String getId() {
        return id;
    }
}

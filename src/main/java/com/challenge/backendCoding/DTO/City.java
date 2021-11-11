package com.challenge.backendCoding.DTO;

import com.opencsv.bean.CsvBindByName;

/**
 * Clase que modela un objeto City
 */
public class City {
    
    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "admin1")
    private String code;

    @CsvBindByName(column = "country")
    private String country;

    @CsvBindByName(column = "lat")
    private double latitude;

    @CsvBindByName(column = "long")
    private double longitude;
    
    private double score;
    
    public City() {
    }

    public City(String name, double latitude, double longitude, String code, String country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.code = code;
        this.country = country;
        this.score = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitud(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitud(double longitude) {
        this.longitude = longitude;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    

}

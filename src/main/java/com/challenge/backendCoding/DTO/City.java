package com.challenge.backendCoding.DTO;

public class City {
    
    private String name;
    private double latitud;
    private double longitud;
    private double score;
    
    public City() {
    }

    public City(String name, double latitud, double longitud, double score) {
        this.name = name;
        this.latitud = latitud;
        this.longitud = longitud;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

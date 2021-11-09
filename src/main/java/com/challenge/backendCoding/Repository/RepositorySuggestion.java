package com.challenge.backendCoding.Repository;

import java.util.ArrayList;
import java.util.List;

import com.challenge.backendCoding.DTO.City;
import com.challenge.backendCoding.DTO.Suggestion;

import org.springframework.stereotype.Repository;

@Repository
public class RepositorySuggestion {

    public Suggestion getSuggestions(String q, String latitude, String longitude){
        List<City> c = new ArrayList<>();
        List<City> aux = new ArrayList<>();
        

        City cit = new City("Mexico",12.23,21.34,0.4);

        c.add(cit);

        if (longitude != null && latitude != null) {
            double str1 = Double.parseDouble(latitude);
            double str2 = Double.parseDouble(longitude);
            for (City city : c) {
                if (city.getName().contains(q) && (str1 <= city.getLatitud()) && (str2 <= city.getLongitud())) {
                    aux.add(city);
                }
            }
        } else if(longitude == null && latitude != null ){
            double str1 = Double.parseDouble(latitude);
            for (City city : c) {
                if (city.getName().contains(q) && (str1 <= city.getLatitud())) {
                    aux.add(city);
                }
            }
        }else if (latitude == null && longitude != null) {
            double str2 = Double.parseDouble(longitude);
            for (City city : c) {
                if (city.getName().contains(q) && (str2 <= city.getLongitud())) {
                    aux.add(city);
                }
            }
        }else{
            for (City city : c) {
                if (city.getName().contains(q)) {
                    aux.add(city);
                }
            }
        }

        Suggestion sug = new Suggestion(aux);
        return sug;
    }

}

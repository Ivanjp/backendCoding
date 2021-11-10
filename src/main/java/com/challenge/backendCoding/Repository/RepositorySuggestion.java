package com.challenge.backendCoding.Repository;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.challenge.backendCoding.DTO.City;
import com.challenge.backendCoding.DTO.Suggestion;

import org.springframework.stereotype.Repository;

@Repository
public class RepositorySuggestion {

    public Suggestion getSuggestions(String q, String latitude, String longitude, String flag) throws FileNotFoundException, IOException{
        List<City> cities = mappingData();
        Suggestion sug = new Suggestion();

        if (longitude != null && latitude != null) {
            double str1 = Double.parseDouble(latitude);
            double str2 = Double.parseDouble(longitude);
            sug.setSuggestions(searchAll(cities, q, str2, str1, flag));

        } else if(longitude == null && latitude != null ){

            double str1 = Double.parseDouble(latitude);
            sug.setSuggestions(searchL(cities, q, 1, str1));

        }else if (latitude == null && longitude != null) {

            double str2 = Double.parseDouble(longitude);
            sug.setSuggestions(searchL(cities, q, 0, str2));

        }else{
            sug.setSuggestions(searchQ(cities, q));
        }
        return sug;
    }

    private List<City> mappingData() throws IOException, FileNotFoundException{
        List<City> beans = new CsvToBeanBuilder(new FileReader("src/main/resources/data/cities_canada-usa.csv")).withType(City.class).build().parse();

        return beans;
    }

    private List<City> searchL(List<City> cities, String q, int flag, double value){
        List<City> aux = new ArrayList<>();

        switch (flag) {
            case 0:
                for (City city : cities) {

                    if (city.getName().startsWith(q) && (value <= city.getLongitude())) {

                        aux.add(city);

                    } else if (!city.getName().startsWith(q) && city.getName().contains(q) 
                            && (value <= city.getLongitude())) {

                        aux.add(city);
                    }
                }

                aux.sort(Comparator.comparing(City::getLongitude));

                double last = aux.get(aux.size() - 1).getLongitude();
                double diff = (value - (last - -1.00000));

                for (City city2 : aux) {
                    double dif2 = value - (city2.getLongitude());
                    double resto = diff - (dif2);
                    double score = (resto / diff);
                    city2.setScore(score);
                }
                
                break;
            case 1:
                for (City city : cities) {

                    if (city.getName().startsWith(q) && (city.getLatitude() <= value)) {

                        aux.add(city);

                    } else if (!city.getName().startsWith(q) && city.getName().contains(q)
                            && (city.getLatitude() <= value)) {

                        aux.add(city);
                    }
                }

                aux.sort(Comparator.comparing(City::getLatitude).reversed());

                double end = aux.get(aux.size() - 1).getLatitude();
                double dif = value - (end - 1.00000);

                for (City city2 : aux) {
                    double dif2 = value - city2.getLatitude();
                    double resto = dif - dif2;

                    double score = (resto / dif);
                    city2.setScore(score);
                }
                break;
            default:
                break;
        }

        return sortSuggestions(aux);
    }

    private List<City> searchQ(List<City> cities, String q) {
        List<City> aux = new ArrayList<>();
        String form = "";
        int q_lenght = q.length();

        for (City city : cities) {
            
            if (city.getName().startsWith(q)) {

                form = formatString(city.getName());
                
                int city_lenght = form.length();
                double score = ((double)q_lenght / (double)city_lenght);
                city.setScore(score);
                aux.add(city);

            }else if(!city.getName().startsWith(q) && city.getName().contains(q)){

                form = formatString(city.getName());

                int city_lenght = form.length();
                double score = ((double) q_lenght / city_lenght);
                city.setScore(score);
                aux.add(city);
            }
        }

        return sortSuggestions(aux);
    }

    private List<City> searchAll(List<City> cities, String q, double lon, double lat, String flag) {
        List<City> aux = new ArrayList<>();
        String form = "";
        int q_lenght = q.length();

        System.out.println(flag);

        if (flag == null) {
            for (City city : cities) {

                if (city.getName().startsWith(q) && (lon <= city.getLongitude()) && (city.getLatitude() <= lat)) {

                    form = formatString(city.getName());

                    int city_lenght = form.length();
                    double score = ((double) q_lenght / (double) city_lenght);
                    city.setScore(score);
                    aux.add(city);

                } else if (!city.getName().startsWith(q) && city.getName().contains(q) && (lon <= city.getLongitude())
                        && (city.getLatitude() <= lat)) {

                    form = formatString(city.getName());

                    int city_lenght = form.length();
                    double score = ((double) q_lenght / city_lenght);
                    city.setScore(score);
                    aux.add(city);
                }
            }
        } else if(flag.equals("0")){
            for (City city : cities) {

                if (city.getName().startsWith(q) && (lon <= city.getLongitude()) && (city.getLatitude() <= lat)) {

                    aux.add(city);

                } else if (!city.getName().startsWith(q) && city.getName().contains(q) && (lon <= city.getLongitude())
                        && (city.getLatitude() <= lat)) {

                    aux.add(city);
                }
            }

            aux.sort(Comparator.comparing(City::getLatitude).reversed());

            double end = aux.get(aux.size() - 1).getLatitude();
            double dif = lat - (end - 1.00000);

            for (City city2 : aux) {
                double dif2 = lat - city2.getLatitude();
                double resto = dif - dif2;

                double score = (resto / dif);
                city2.setScore(score);
            }
            
        }else if(flag.equals("1")){
            for (City city : cities) {

                if (city.getName().startsWith(q) && (lon <= city.getLongitude()) && (city.getLatitude() <= lat)) {

                    aux.add(city);

                } else if (!city.getName().startsWith(q) && city.getName().contains(q) && (lon <= city.getLongitude())
                        && (city.getLatitude() <= lat)) {

                    aux.add(city);
                }
            }

            aux.sort(Comparator.comparing(City::getLongitude));

            double last = aux.get(aux.size() - 1).getLongitude();
            double diff = (lon - (last - -1.00000));

            for (City city2 : aux) {
                double dif2 = lon - (city2.getLongitude());
                double resto = diff - (dif2);
                double score = (resto / diff);
                city2.setScore(score);
            }
        }

        return sortSuggestions(aux);
    }

    private List<City> sortSuggestions(List<City> sug){
        DecimalFormat formato1 = new DecimalFormat("0.0");
        formato1.setRoundingMode(RoundingMode.DOWN);

        sug.sort(Comparator.comparing(City::getScore).reversed());

        for (City city : sug) {
            double aux = city.getScore();
            city.setScore(Double.parseDouble(formato1.format(aux)));
        }

        return sug;
    }

    private String formatString(String name){

        if (name.contains(" ")) {
            name = name.replace(" ", "");
        } 
        
        if (name.contains("-")) {
            name = name.replace("-", "");
        }
        
        return name;
    }

}

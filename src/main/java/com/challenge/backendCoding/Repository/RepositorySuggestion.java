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
import org.springframework.stereotype.Repository;

/**
 * Repository de la API.
 * Realiza las búsquedas para obtener las sugerencias.
 */
@Repository
public class RepositorySuggestion {

    /**
     * Método que hace la búsqueda de los datos, con la latitud o con la longitud
     * como parámetro extra
     *
     * @param q     Término parcial o completo de la búsqueda
     * @param value Valor Aprox (o completo) de Latitude/Longitude
     * @param flag  Valor (0-1) que indica si el parametro que se pasa es la
     *              latitud(1) o la longitud(0)
     * @return Una List<City> con las ciudades sugeridas.
     */
    public List<City> searchL(String q, int flag, double value) throws FileNotFoundException, IOException{
        List<City> cities = mappingData();//Se realiza el mapeo de todos los datos contenidos en el archivo .csv
        List<City> aux = new ArrayList<>();//Arreglo auxiliar donde se guardan las ciudades que hagan match.

        switch (flag) {
            case 0:
                for (City city : cities) {

                    //Obtención de las sugerencias

                    if (city.getName().startsWith(q) && (value <= city.getLongitude())) {

                        aux.add(city);

                    } else if (!city.getName().startsWith(q) && city.getName().contains(q) 
                            && (value <= city.getLongitude())) {

                        aux.add(city);
                    }
                }

                aux.sort(Comparator.comparing(City::getLongitude));//Se ordena la lista con las ciudades seleccionadas.

                //El score se calcula sacando un rango proporcional entre los valores de las ciudades sugeridas y el valor pasado como parámetro.

                double last = aux.get(aux.size() - 1).getLongitude();
                double diff = (value - (last - -1.00000));

                //Se recorre la lista con las ciudades previamente seleccionadas para calcularles el score
                for (City city2 : aux) {
                    double dif2 = value - (city2.getLongitude());//Se calcula la diferencia entre la longitud que se
                                                                 // pasa y la longitud de la ciudad actual que se está iterando, 
                                                                 // para ajustarlo al rango calculado.

                    double resto = diff - (dif2);//Se calcula la diferencia ya dentro del rango establecido.
                    double score = (resto / diff);//Se calcula el score final
                    city2.setScore(score);//Se modifica el score de la ciudad
                }
                
                break;
            case 1:
                for (City city : cities) {

                    // Obtención de las sugerencias
                    if (city.getName().startsWith(q) && (city.getLatitude() <= value)) {

                        aux.add(city);

                    } else if (!city.getName().startsWith(q) && city.getName().contains(q)
                            && (city.getLatitude() <= value)) {

                        aux.add(city);
                    }
                }

                //Se realiza el mismo proceso que arriba

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

    /**
     * Método que hace la búsqueda de los datos, solo con el término pasado.
     *
     * @param q Término parcial o completo de la búsqueda
     * @return Una List<City> con las ciudades sugeridas.
     */
    public List<City> searchQ(String q) {
        List<City> cities = mappingData();// Se realiza el mapeo de todos los datos contenidos en el archivo .csv
        List<City> aux = new ArrayList<>();// Arreglo auxiliar donde se guardan las ciudades que hagan match
        String form = "";//Variable que guarda el nombre de la ciudad sin espacios ni -
        int q_lenght = q.length();//Longitud del termino

        for (City city : cities) {
            
            // Obtención de las sugerencias
            if (city.getName().startsWith(q)) {

                form = formatString(city.getName());//Se quitan los espacios y - del nombre de la ciudad que se está iterando
                
                int city_lenght = form.length();
                double score = ((double)q_lenght / (double)city_lenght);//Se calcula el score
                city.setScore(score);// Se modifica el score de la ciudad
                aux.add(city);

            }else if(!city.getName().startsWith(q) && city.getName().contains(q)){

                //Se realiza el mismo procedimiento

                form = formatString(city.getName());

                int city_lenght = form.length();
                double score = ((double) q_lenght / city_lenght);
                city.setScore(score);
                aux.add(city);
            }
        }

        return sortSuggestions(aux);
    }

    /**
     * Método que hace la búsqueda de los datos, con todos los términos.
     *
     * @param q    Término parcial o completo de la búsqueda
     * @param lon  Valor Aprox (o completo) de Longitude
     * @param lat  Valor Aprox (o completo) de Latitude
     * @param flag Valor (0-1) que indica si el score se calcula en base a la
     *             longitud(1) o latitud(0). Por defecto se calcula en base al
     *             término.
     * @return Una List<City> con las ciudades sugeridas.
     */
    public List<City> searchAll(String q, double lon, double lat, String flag) {

        //Mismos parámetros que en searchQ()
        List<City> cities = mappingData();
        List<City> aux = new ArrayList<>();
        String form = "";
        int q_lenght = q.length();

        if (flag == null) {//Calcula los scores en base al término
            for (City city : cities) {

                // Obtención de las sugerencias
                if (city.getName().startsWith(q) && (lon <= city.getLongitude()) && (city.getLatitude() <= lat)) {

                    // Calcula los scores en base al término igual que en searchQ()

                    form = formatString(city.getName());

                    int city_lenght = form.length();
                    double score = ((double) q_lenght / (double) city_lenght);
                    city.setScore(score);
                    aux.add(city);

                } else if (!city.getName().startsWith(q) && city.getName().contains(q) && (lon <= city.getLongitude())
                        && (city.getLatitude() <= lat)) {

                    // Calcula los scores en base al término igual que en searchQ()

                    form = formatString(city.getName());

                    int city_lenght = form.length();
                    double score = ((double) q_lenght / city_lenght);
                    city.setScore(score);
                    aux.add(city);
                }
            }
        } else if(flag.equals("0")){// Calcula los scores en base al la latitud
            for (City city : cities) {

                if (city.getName().startsWith(q) && (lon <= city.getLongitude()) && (city.getLatitude() <= lat)) {

                    aux.add(city);

                } else if (!city.getName().startsWith(q) && city.getName().contains(q) && (lon <= city.getLongitude())
                        && (city.getLatitude() <= lat)) {

                    aux.add(city);
                }
            }

            // Calcula los scores en base al término igual que en searchL() -> Para Latitude

            aux.sort(Comparator.comparing(City::getLatitude).reversed());

            double end = aux.get(aux.size() - 1).getLatitude();
            double dif = lat - (end - 1.00000);

            for (City city2 : aux) {
                double dif2 = lat - city2.getLatitude();
                double resto = dif - dif2;

                double score = (resto / dif);
                city2.setScore(score);
            }
            
        }else if(flag.equals("1")){// Calcula los scores en base a la longitude
            for (City city : cities) {

                if (city.getName().startsWith(q) && (lon <= city.getLongitude()) && (city.getLatitude() <= lat)) {

                    aux.add(city);

                } else if (!city.getName().startsWith(q) && city.getName().contains(q) && (lon <= city.getLongitude())
                        && (city.getLatitude() <= lat)) {

                    aux.add(city);
                }
            }

            // Calcula los scores en base al término igual que en searchL() -> Para Longitude

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

    /**
     * Método que hace el mapeo de los datos del archivo .csv
     * @return List<City> con las ciudades mapeadas
     */
    private List<City> mappingData() {
        List<City> beans = new ArrayList<>();
        try {
            beans = new CsvToBeanBuilder(new FileReader("src/main/resources/data/cities_canada-usa.csv"))
                    .withType(City.class).build().parse();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return beans;
    }

    /**
     * Método que ordena las ciudades por score
     * @param sug List<City> con las ciudades sugeridas
     * @return List<City> con las ciudades ordenadas por score
     */
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

    /**
     * Método que quita espacios y - de una cadena
     * 
     * @param name Nombre de la ciudad
     * @return Nombre de la ciudad sin espacios ni -
     */
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

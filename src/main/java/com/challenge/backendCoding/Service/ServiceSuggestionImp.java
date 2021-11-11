package com.challenge.backendCoding.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.challenge.backendCoding.DTO.Suggestion;
import com.challenge.backendCoding.Repository.RepositorySuggestion;
import com.challenge.exceptionHandler.ApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Service de la API 
 * ServiceSuggestionImp implementa los métodos de la
 * interfaz ServiceSuggestion
 */
@Service
public class ServiceSuggestionImp implements ServiceSuggestion {

    @Autowired
    RepositorySuggestion repositorySuggestion;

    /**
     * Método que se comunica con el Repository para la obtención de los datos
     * dependiendo del tipo de búsqueda que se requiera.
     *
     * @param q         Término parcial o completo de la búsqueda
     * @param latitude  Valor Aprox (o completo) de Latitude (Optional)
     * @param longitude Valor Aprox (o completo) de Longitude (Optional)
     * @param flag      Valor opcional para dar prioridad a la latitud o longitud en
     *                  la búsqueda con todos los términos
     * @return Objeto Suggestion con la lista de las ciudades sugeridas.
     */
    @Override
    public Suggestion getSuggestions(String q, String latitude, String longitude,String flag) throws FileNotFoundException, IOException {
        try {
            Suggestion sug = new Suggestion();//Objeto Suggestion que se regresará

            //Búsqueda que contiene los parámetros longitude y latitude
            if (longitude != null && latitude != null) {
                //Parseo de parámetros (latitude y longitude) para poder realizar las operaciones para sacar el score.
                double str1 = Double.parseDouble(latitude);
                double str2 = Double.parseDouble(longitude);

                sug.setSuggestions(repositorySuggestion.searchAll(q, str2, str1, flag));

            } else if (longitude == null && latitude != null) {//Búsqueda que contiene el parámetro latitude

                double str1 = Double.parseDouble(latitude);
                sug.setSuggestions(repositorySuggestion.searchL(q, 1, str1));

            } else if (latitude == null && longitude != null) {// Busqueda que contiene el parámetro longitude

                double str2 = Double.parseDouble(longitude);
                sug.setSuggestions(repositorySuggestion.searchL(q, 0, str2));

            } else {
                // Busqueda con solo el término
                sug.setSuggestions(repositorySuggestion.searchQ(q)); 
            }
            return sug;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Values for latitude and longitude must not be characters");
        }
        
    }

}

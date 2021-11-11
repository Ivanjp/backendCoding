package com.challenge.backendCoding.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.challenge.backendCoding.DTO.Suggestion;

/**
 * La interfaz ServiceSuggestion declara los métodos que ocupará el Service de la API
 */
public interface ServiceSuggestion {
    
    /**
     * Firma del método para las llamadas al Repository.
     *
     * @param q         Término parcial o completo de la búsqueda
     * @param latitude  Valor Aprox (o completo) de Latitude (Optional)
     * @param longitude Valor Aprox (o completo) de Longitude (Optional)
     * @param flag      Valor opcional para dar prioridad a la latitud o longitud en
     *                  la búsqueda con todos los términos
     * @return Objeto Suggestion con la lista de las ciudades sugeridas.
     */
    public Suggestion getSuggestions(String q, String latitude, String longitude,String flag) 
            throws FileNotFoundException, IOException;
}

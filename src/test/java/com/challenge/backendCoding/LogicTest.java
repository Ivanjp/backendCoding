package com.challenge.backendCoding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.challenge.backendCoding.Controller.ControllerSuggestion;
import com.challenge.backendCoding.DTO.City;
import com.challenge.backendCoding.Repository.RepositorySuggestion;
import com.challenge.backendCoding.Service.ServiceSuggestionImp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LogicTest {

    @Mock
    private RepositorySuggestion repositorySuggestion;

    @Mock
    private ControllerSuggestion controllerSuggestion;

    @InjectMocks
    private ServiceSuggestionImp serviceSuggestionImp;

    private List<City> exam = new ArrayList<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Verifica que regresa un lista sin sugerencias cuando el query "q" no contiene un nombre (parcial o completo)
     */
    @Test
    void listNullWithQ() throws FileNotFoundException, IOException{

        assertEquals(exam, serviceSuggestionImp.getSuggestions("6t4", null, null, null).getSuggestions());
    }

    /**
     * Verifica que regresa un lista sin sugerencias cuando el query "q" no contiene
     * un nombre (parcial o completo), aunque se le pase una latitud
     */
    @Test
    void listNullWithQAndLat() throws FileNotFoundException, IOException {

        assertEquals(exam, serviceSuggestionImp.getSuggestions("", "43.41672", null, null).getSuggestions());
    }

    /**
     * Verifica que regresa un lista sin sugerencias cuando el query "q" no contiene
     * un nombre (parcial o completo), aunque se le pase una longitud
     */
    @Test
    void listNullWithQAndLong() throws FileNotFoundException, IOException {

        assertEquals(exam, serviceSuggestionImp.getSuggestions("6t4", null, "-73.41672", null).getSuggestions());
    }

    /**
     * Verifica que regresa un lista sin sugerencias cuando el query "q" no contiene
     * un nombre (parcial o completo), aunque se le pase una latitud y una longitud
     */
    @Test
    void listNullWithAll() throws FileNotFoundException, IOException {

        assertEquals(exam, serviceSuggestionImp.getSuggestions("6t4", "43.41672", "-73.41672", null).getSuggestions());
    }

    /**
     * Verifica que regresa un lista sin sugerencias cuando el query "q" no contiene
     * un nombre (parcial o completo), aunque se le pase una latitud, una longitud y la bandera para
     * priorizar el score con base en la latitud
     */
    @Test
    void listNullWithAllandFlag0() throws FileNotFoundException, IOException {

        assertEquals(exam, serviceSuggestionImp.getSuggestions("6t4", "43.41672", "-73.41672", "0").getSuggestions());
    }

    /**
     * Verifica que regresa un lista sin sugerencias cuando el query "q" no contiene
     * un nombre (parcial o completo), aunque se le pase una latitud, una longitud y
     * la bandera para priorizar el score con base en la longitud
     */
    @Test
    void listNullWithAllandFlag1() throws FileNotFoundException, IOException {

        assertEquals(exam, serviceSuggestionImp.getSuggestions("6t4", "43.41672", "-73.41672", "1").getSuggestions());
    }

    /**
     * Verifica que regresa un lista con sugerencias cuando el query "q" contiene
     * un nombre (parcial o completo)
     */
    @Test
    void suggestionWithQ() throws FileNotFoundException, IOException {

        assertNotNull(serviceSuggestionImp.getSuggestions("For", null, null, null).getSuggestions());
    }

    /**
     * Verifica que regresa un lista con sugerencias cuando el query "q" contiene
     * un nombre (parcial o completo), y se le pase una latitud
     */
    @Test
    void suggestionWithQandLat() throws FileNotFoundException, IOException {

        assertNotNull(serviceSuggestionImp.getSuggestions("For", "42.89543", null, null).getSuggestions());
    }

    /**
     * Verifica que regresa un lista con sugerencias cuando el query "q" contiene un
     * nombre (parcial o completo), y se le pase una longitud
     */
    @Test
    void suggestionWithQandLong() throws FileNotFoundException, IOException {

        assertNotNull(serviceSuggestionImp.getSuggestions("For", null, "-65.73518", null).getSuggestions());
    }

    /**
     * Verifica que regresa un lista cin sugerencias cuando el query "q" contiene un
     * nombre (parcial o completo), y se le pase una latitud y longitud
     */
    @Test
    void suggestionWithQandAll() throws FileNotFoundException, IOException {

        assertNotNull(serviceSuggestionImp.getSuggestions("For", "42.89543", "-65.73518", null).getSuggestions());
    }

    /**
     * Verifica que regresa un lista sin sugerencias cuando el query "q" no contiene
     * un nombre (parcial o completo), aunque se le pase una latitud, una longitud y
     * la bandera para priorizar el score con base en la latitud
     */
    @Test
    void suggestionWithQandAllwithF0() throws FileNotFoundException, IOException {

        assertNotNull(serviceSuggestionImp.getSuggestions("For", "42.89543", "-65.73518", "0").getSuggestions());
    }
    
    /**
     * Verifica que regresa un lista sin sugerencias cuando el query "q" no contiene
     * un nombre (parcial o completo), aunque se le pase una latitud, una longitud y
     * la bandera para priorizar el score con base en la longitud
     */
    @Test
    void suggestionWithQandAllwithF1() throws FileNotFoundException, IOException {

        assertNotNull(serviceSuggestionImp.getSuggestions("For", "42.89543", "-65.73518", "1").getSuggestions());
    }


}
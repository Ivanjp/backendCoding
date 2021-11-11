package com.challenge.backendCoding.Controller;

import java.util.ArrayList;
import java.util.List;

import com.challenge.backendCoding.DTO.City;
import com.challenge.backendCoding.DTO.Suggestion;
import com.challenge.backendCoding.Repository.RepositorySuggestion;
import com.challenge.backendCoding.Service.ServiceSuggestion;
import com.challenge.backendCoding.Service.ServiceSuggestionImp;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringBootConfiguration.class })
@WebMvcTest(ControllerSuggestion.class)
public class ControllerSuggestionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ServiceSuggestion serviceSuggestion;

    @MockBean
    private ServiceSuggestionImp serviceSuggestionImp;

    @MockBean
    private RepositorySuggestion repositorySuggestion;

    // @Test
    // public void testgetSuggestionsRandom() throws Exception{

    //     String q = "4rdf4";
    //     String latitude = null;
    //     String longitude = null;
    //     String flag = null;

    //     List<City> suggestions = new ArrayList<>();

    //     Suggestion value = new Suggestion(suggestions);

    //     Mockito.when(repositorySuggestion.getSuggestions(q, latitude, longitude, flag)).thenReturn(value);

    //     String url = "/suggestions?q=4rdf4";

    //     MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

    //     String actualJsonResponse = mvcResult.getResponse().getContentAsString();

    //     String expectedJsonResponse = objectMapper.writeValueAsString(value);

    //     assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    // }
    
}

package com.challenge.backendCoding.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.challenge.backendCoding.DTO.Suggestion;
import com.challenge.backendCoding.Service.ServiceSuggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suggestions")
public class ControllerSuggestion {
    
    @Autowired
    ServiceSuggestion serviceSuggestion;

    @GetMapping
    public ResponseEntity<Suggestion> getSuggestions(@RequestParam(required = true) String q, 
            @RequestParam(required = false) String latitude,
            @RequestParam(required = false) String longitude) throws FileNotFoundException, IOException {
        return new ResponseEntity<>(serviceSuggestion.getSuggestions(q,latitude,longitude), HttpStatus.OK);
    }
}

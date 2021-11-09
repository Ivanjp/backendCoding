package com.challenge.backendCoding.Controller;

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
    public ResponseEntity<Object> getSuggestions(@RequestParam("q") String q) {
        return new ResponseEntity<>(serviceSuggestion.getSuggestions(q), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getSuggestions(@RequestParam("q") String q, @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude) {
        return new ResponseEntity<>(serviceSuggestion.getSuggestions(q, latitude, longitude), HttpStatus.OK);
    }
}

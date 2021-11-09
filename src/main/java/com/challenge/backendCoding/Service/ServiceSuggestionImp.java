package com.challenge.backendCoding.Service;

import com.challenge.backendCoding.DTO.Suggestion;
import com.challenge.backendCoding.Repository.RepositorySuggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceSuggestionImp implements ServiceSuggestion {

    @Autowired
    RepositorySuggestion repositorySuggestion;

    @Override
    public Suggestion getSuggestions(String q) {
        return repositorySuggestion.getSuggestions(q);
    }
    
    @Override
    public Suggestion getSuggestions(String q, String latitude, String longitude) {
        return repositorySuggestion.getSuggestions(q, latitude, longitude);
    }

}

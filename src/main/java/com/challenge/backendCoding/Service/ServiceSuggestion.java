package com.challenge.backendCoding.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.challenge.backendCoding.DTO.Suggestion;

public interface ServiceSuggestion {
    
    public Suggestion getSuggestions(String q, String latitude, String longitude,String flag) 
            throws FileNotFoundException, IOException;
}

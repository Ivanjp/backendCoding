package com.challenge.backendCoding.Service;

import com.challenge.backendCoding.DTO.Suggestion;

public interface ServiceSuggestion {
    
    public Suggestion getSuggestions(String q, String latitude, String longitude);
}

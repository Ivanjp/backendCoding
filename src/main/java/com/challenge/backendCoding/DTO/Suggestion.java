package com.challenge.backendCoding.DTO;

import java.util.List;

public class Suggestion {
    
    private List<City> suggestions;
    
    public Suggestion() {
    }

    public Suggestion(List<City> suggestions) {
        this.suggestions = suggestions;
    }

    public List<City> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<City> suggestions) {
        this.suggestions = suggestions;
    }
    
}

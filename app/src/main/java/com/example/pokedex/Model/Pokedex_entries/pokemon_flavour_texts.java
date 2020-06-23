package com.example.pokedex.Model.Pokedex_entries;

public class pokemon_flavour_texts {
    String flavor_text;
    Language language;

    public Language getLanguage() {
        return language;
    }

    public pokemon_flavour_texts(Language language) {
        this.language = language;
    }

    public pokemon_flavour_texts(String flavor_text) {
        this.flavor_text = flavor_text;
    }

    public String getFlavor_text() {
        return flavor_text;
    }

}

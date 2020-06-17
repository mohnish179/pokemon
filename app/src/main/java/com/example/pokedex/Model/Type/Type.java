package com.example.pokedex.Model.Type;

public class Type {

    String name;
    String url;

    public Type(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

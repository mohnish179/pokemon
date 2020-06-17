package com.example.pokedex.Model;

public class Pokemon {

    private int number;
    private String name;
    private String url;

    public int getNumber() {
        String[] urlparts=url.split("/");
        return Integer.parseInt(urlparts[urlparts.length-1]);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

package com.example.pokedex.Model.evolution;

import java.util.ArrayList;

public class Evolves_to {

Species species;
ArrayList<Evolves_to> evolves_to;

    public Species getSpecies() {
        return species;
    }

    public ArrayList<Evolves_to> getEvolves_to() {
        return evolves_to;
    }
}

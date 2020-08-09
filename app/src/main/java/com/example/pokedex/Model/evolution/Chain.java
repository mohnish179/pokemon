package com.example.pokedex.Model.evolution;

import java.util.ArrayList;

public class Chain {

    ArrayList<Evolves_to> evolves_to;
    Species species;

    public ArrayList<Evolves_to> getEvolves_to() {
        return evolves_to;
    }

    public Species getSpecies() {
        return species;
    }
}

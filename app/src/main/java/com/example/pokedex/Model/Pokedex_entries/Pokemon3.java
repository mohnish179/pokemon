package com.example.pokedex.Model.Pokedex_entries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pokemon3 {

    @SerializedName("flavor_text_entries")
        @Expose
    ArrayList<pokemon_flavour_texts> flavor_text_entries;

Generation generation;
Evolution_chain evolution_chain;
int id;

    public int getId() {
        return id;
    }

    public Evolution_chain getEvolution_chain() {
        return evolution_chain;
    }

    public Generation getGeneration() {
        return generation;
    }

    @SerializedName("base_happiness")
        @Expose
    int base_happiness;

    public ArrayList<pokemon_flavour_texts> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public int getBase_happiness() {
        return base_happiness;
    }
}

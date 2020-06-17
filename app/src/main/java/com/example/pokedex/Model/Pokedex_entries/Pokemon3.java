package com.example.pokedex.Model.Pokedex_entries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pokemon3 {

    @SerializedName("flavor_text_entries")
        @Expose
    ArrayList<pokemon_flavour_texts> flavor_text_entries;
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

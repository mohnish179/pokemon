package com.example.pokedex.PokeApiService;

import com.example.pokedex.Model.Pokedex_entries.Pokemon3;
import com.example.pokedex.Model.Pokemon2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface pokeapiservice_get_pokedex_entries {

    @GET(".")
    Call<Pokemon3> getJsonArrayData();
}

package com.example.pokedex.PokeApiService;

import com.example.pokedex.Model.Pokedex_entries.Pokemon3;
import com.example.pokedex.Model.evolution.evolutionchain;

import retrofit2.Call;
import retrofit2.http.GET;

public interface pokeapiservice_get_evolution {


    @GET(".")
    Call<evolutionchain> getJsonArrayData();
}

package com.example.pokedex.PokeApiService;

import com.example.pokedex.Model.Pokemon2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface pokeapiservice_get_info_pokemon {


    @GET(".")
    Call<Pokemon2> getJsonArrayData();
}

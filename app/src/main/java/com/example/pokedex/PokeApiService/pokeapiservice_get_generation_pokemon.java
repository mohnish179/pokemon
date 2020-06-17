package com.example.pokedex.PokeApiService;

import com.example.pokedex.Model.Ans.PokemonAns;
import com.example.pokedex.Model.Ans.Pokemon_get_pokemon_for_generation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface pokeapiservice_get_generation_pokemon {

    @GET("{id}")
    Call<Pokemon_get_pokemon_for_generation> obtain_list_Pokemon(@Path("id")int id, @Query("limit")int limit, @Query("offset") int offset);
}

package com.example.pokedex.PokeApiService;

import com.example.pokedex.Model.Ans.PokemonAns;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface pokeapiservice {


   @GET("{id}")
   Call<PokemonAns>obtain_list_Pokemon( @Path("id")String id,@Query("limit")int limit, @Query("offset") int offset);

}

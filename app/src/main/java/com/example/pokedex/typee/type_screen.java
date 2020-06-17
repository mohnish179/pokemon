package com.example.pokedex.typee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.Adapter.PokemonAdapter;
import com.example.pokedex.Location;
import com.example.pokedex.MainActivity;
import com.example.pokedex.Model.Ans.PokemonAns;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.PokeApiService.pokeapiservice;
import com.example.pokedex.R;
import com.example.pokedex.Regions.Region_screen;
import com.example.pokedex.items;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class type_screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    Retrofit retrofit;
    private static final String TAG = "items";
    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    private int offset;
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;


    private boolean a1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdrawerlayout=findViewById(R.id.drawer_layout);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        recyclerView = findViewById(R.id.recyclerView);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (a1) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {

                            a1 = false;
                            offset += 20;
                            get_info(offset);
                        }
                    }
                }
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        a1 = true;
        offset = 0;
        get_info(offset);
    }

    private void get_info(int offset) {
        pokeapiservice service = retrofit.create(pokeapiservice.class);

        Call<PokemonAns> pokemonAnsCall = service.obtain_list_Pokemon("type",20,offset);

        pokemonAnsCall.enqueue(new Callback<PokemonAns>() {
            @Override
            public void onResponse(Call<PokemonAns> call, Response<PokemonAns> response) {
                a1 = true;
                if (response.isSuccessful()) {

                    PokemonAns pokemonAns = response.body();
                    ArrayList<Pokemon> list_Pokemon = pokemonAns.getResults();



                    pokemonAdapter.addPokemon(list_Pokemon);
                    Log.d(TAG, "onResponse:");

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonAns> call, Throwable t) {
                a1 = true;
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mtoggle.onOptionsItemSelected(item))
        {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.nav_items:
                Intent intent=new Intent(this, items.class);
                startActivity(intent);
                break;
            case R.id.nav_types:
                Intent intent2=new Intent(this, type_screen.class);
                startActivity(intent2);
                break;
            case R.id.nav_regions:
                Intent intent3= new Intent(this, Region_screen.class);
                startActivity(intent3);
                break;
            case R.id.nav_location:
                Intent intent4=new Intent(this, Location.class);
                startActivity(intent4);
                break;
            case R.id.nav_pokedex:
                Intent intent5=new Intent(this,MainActivity.class);
                startActivity(intent5);
                break;

        }


        mdrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

}

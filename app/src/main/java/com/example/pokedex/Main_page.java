package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pokedex.Adapter.PokemonAdapter;
import com.example.pokedex.Regions.Region_screen;
import com.example.pokedex.typee.type_screen;
import com.google.android.material.navigation.NavigationView;

public class Main_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;

    private PokemonAdapter pokemonAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        mdrawerlayout=findViewById(R.id.drawer_layout);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem searchitem= menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) searchitem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                pokemonAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return true;
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
                Intent intent4=new Intent(this,Location.class);
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

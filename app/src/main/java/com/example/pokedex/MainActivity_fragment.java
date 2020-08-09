package com.example.pokedex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.Adapter.PokemonAdapter;

import retrofit2.Retrofit;

public class MainActivity_fragment extends Fragment {

    private static final String TAG = "POKEDEX";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    private int offset;
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;

    private ProgressBar p;

    int load;
    private boolean a1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main,container,false);


        return view;


    }
}

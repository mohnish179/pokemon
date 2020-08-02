package com.example.pokedex.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.R;
import com.example.pokedex.Regions.specific_region_screen;
import com.example.pokedex.screen2;
import com.example.pokedex.typee.specific_type_screen;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> implements Filterable {

    private ArrayList<Pokemon> dataset;
    private ArrayList<Pokemon> datasetfull;
    private Context context;
    String replace;

    public static String posn="com.example.pokedexsample2.adapter.PokemonAdapter.dataset";


    public PokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //getting to know what pokemon  it is
        Pokemon pokemon1 = dataset.get(position);
//storing the Data_set in shared preferences
        Gson gson=new Gson();
        String json=gson.toJson(dataset);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("dataset",json);
        editor.apply();
        holder.TextViewTitle.setText(pokemon1.getName());


        //getting image

        if(dataset.get(0).getName().equals("bulbasaur")) {
            Glide.with(context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon1.getNumber() + ".png")
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);
            holder.parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, screen2.class);
                    intent.putExtra("posn",position);
                    context.startActivity(intent);

                }
            });
        }
       else if(dataset.get(0).getName().equals("normal"))
        {
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.TextViewTitle.setTextSize(30);
            holder.parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, specific_type_screen.class);
                    intent.putExtra("type_sent",position);
                    context.startActivity(intent);

                }
            });
        }
       else if(dataset.get(0).getName().equals("kanto"))
        {
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.TextViewTitle.setTextSize(30);
            holder.parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, specific_region_screen.class);
                    intent.putExtra("region_sent",position);
                    context.startActivity(intent);



                }
            });
        }
       else if(dataset.get(0).getName().equals("canalave-city"))
        { holder.imageView.setVisibility(View.INVISIBLE);
            holder.TextViewTitle.setTextSize(20);

            //has nothing
        }
        else
        {
            replace=pokemon1.getName();
            replace=replace.replace("-","");

            Glide.with(context)
                    .load("https://www.serebii.net/itemdex/sprites/pgl/" + replace + ".png")
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);
        }


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        datasetfull=new ArrayList<>(dataset);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView TextViewTitle;
        LinearLayout parentlayout;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            TextViewTitle = (TextView) itemView.findViewById(R.id.TextViewTitle);
            parentlayout=itemView.findViewById(R.id.parentlayout);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Pokemon> filteredlist=new ArrayList<>();

            if(constraint==null||constraint.length()==0)
            {
                filteredlist.addAll(datasetfull);

            }
            else
            {
                String filterpattern=constraint.toString().toLowerCase().trim();
                for(Pokemon item:datasetfull)
                {
                    if(item.getName().toLowerCase().contains(filterpattern))
                    {
                        filteredlist.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredlist;

            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataset.clear();
            dataset.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };
}


package com.example.pokedex.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.R;
import com.example.pokedex.screen2;
import com.google.gson.Gson;

import java.util.ArrayList;

public class specific_type_adapter extends RecyclerView.Adapter<specific_type_adapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;
    int i=0;
    private static final String TAG = "specific_type_adapter";
    public specific_type_adapter(Context context) {
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
        final Pokemon pokemon1 = dataset.get(position);
       /* Gson gson=new Gson();
        String json=gson.toJson(dataset);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("dataset",json);
        editor.apply();*/

        holder.TextViewTitle.setText(pokemon1.getName());


        //getting image
            Glide.with(context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +pokemon1.getNumber()+ ".png")
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);


            holder.parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gson gson=new Gson();
                    String json=gson.toJson(dataset);
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putString("dataset",json);
                    editor.apply();
                    Intent intent = new Intent(context, screen2.class);

                    intent.putExtra("posn",position);
                    context.startActivity(intent);

                }
            });





    }//onbind viewholder

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
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

}


package com.example.pokedex;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedex.Model.Pokedex_entries.Pokemon3;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.Model.Pokemon2;
import com.example.pokedex.PokeApiService.pokeapiservice_get_info_pokemon;
import com.example.pokedex.PokeApiService.pokeapiservice_get_pokedex_entries;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class screen2 extends AppCompatActivity {
    private ArrayList<Pokemon> dataset2;
    int position;

    RelativeLayout r;


    int ht, wt,id;
    int temp;

    List<String> positivelist = new ArrayList<>();
    String about=new String();

    String x;

    private Retrofit retrofit,retrofit2;
    TextView tv;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    CardView cardView;
    TextView tv7;
    TextView tv8;
    TextView tv9;
    pokeapiservice_get_info_pokemon service;
    pokeapiservice_get_pokedex_entries service2;

    private static final String TAG = "screen2";
    Gson gson2;
    Pokemon p;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_pokemon);


        position = getIntent().getIntExtra("posn", 0);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        gson2=new Gson();
        String json = sharedPreferences.getString("dataset", null);
        final Type type = new TypeToken<ArrayList<Pokemon>>() {
        }.getType();
        dataset2 = gson.fromJson(json, type);
        if (dataset2 == null) {
            dataset2 = new ArrayList<>();
        }
        p = dataset2.get(position);
     tv = (TextView) findViewById(R.id.info_tv);
        ImageView iv = (ImageView) findViewById(R.id.info_image);
         tv2 = (TextView) findViewById(R.id.tv2);
        //tv5=findViewById(R.id.tv5);
        //tv6=findViewById(R.id.tv6);
        cardView=findViewById(R.id.cardview);
        r=findViewById(R.id.relative_layout);

        tv8=findViewById(R.id.tv8);
        tv9=findViewById(R.id.tv9);

        //tv2.setText("ID:" + (position + 1));
        temp=position+1;

        tv.setText(p.getName());
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv7=findViewById(R.id.tv7);
        Glide.with(this)
                .load("https://pokeres.bastionbot.org/images/pokemon/" +p.getNumber()+ ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv);
        Log.d(TAG, "onCreate: "+p.getNumber()+"  "+position);



        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon/"+p.getName()+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(pokeapiservice_get_info_pokemon.class);

        Call<Pokemon2> call = service.getJsonArrayData();

        call.enqueue(new Callback<Pokemon2>() {
            @Override
            public void onResponse(Call<Pokemon2> call, Response<Pokemon2> response) {
                if (response.isSuccessful()) {
                    Pokemon2 pokemonAns = response.body();
                    ht=pokemonAns.getHeight();
                    wt=pokemonAns.getWeight();
                    id=pokemonAns.getId();
                    tv3.setText("Height:" + ht);
                    tv4.setText("Weight:" + wt);
                    tv2.setText("ID:" + id);

                    positivelist.add(pokemonAns.getTypes().get(0).getType().getName());
                    x=positivelist.get(0);
                    tv7.setText(x);
                    getcolor(x,1);
                    tv8.setVisibility(View.INVISIBLE);
                    if (pokemonAns.getTypes().size()==2)
                    {
                        positivelist.add(pokemonAns.getTypes().get(1).getType().getName());
                        String y=positivelist.get(1);
                        tv8.setText(positivelist.get(1));
                        tv8.setVisibility(View.VISIBLE);
                        getcolor(y,2);

                    }


                }
            }

            @Override
            public void onFailure(Call<Pokemon2> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

        retrofit2 = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon-species/"+p.getName()+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service2 = retrofit2.create(pokeapiservice_get_pokedex_entries.class);

        Call<Pokemon3> call2 = service2.getJsonArrayData();

        call2.enqueue(new Callback<Pokemon3>() {
            @Override
            public void onResponse(Call<Pokemon3> call, Response<Pokemon3> response) {
                if (response.isSuccessful()) {
                    Pokemon3 pokemonAns=response.body();
                    assert pokemonAns != null;
                    about=pokemonAns.getFlavor_text_entries().get(0).getFlavor_text();
                    tv9.setText(about);


                }
            }

            @Override
            public void onFailure(Call<Pokemon3> call, Throwable t) {

            }
        });






    }

    public void getcolor(String T,int s)
    {
        int c,c2 = 0;
        switch(T)
        {

            case "normal":
                c=Color.parseColor("#A4A27A");c2=Color.parseColor("#D2B4BC");
                break;


            case "dragon":
                c= Color.parseColor("#743BFB");
                c2=Color.parseColor("#9966FF");
                break;



            case "psychic":
                c= Color.parseColor("#F15B85");
                c2=Color.parseColor("#FF99FF");
                break;


            case "electric":
                c= Color.parseColor("#E9CA3C");
                c2=Color.parseColor("#FFFF9E");
                break;


            case "ground":
                c= Color.parseColor("#D9BF6C");
                c2=Color.parseColor("#D2BE96");
                break;


            case "grass":
                c= Color.parseColor("#81C85B"); c2=Color.parseColor("#98FB98");break;

            case "poison":
                c= Color.parseColor("#A441A3");
                c2=Color.parseColor("#f68af4");
                break;

            case "steel":
                c= Color.parseColor("#BAB7D2");c2=Color.parseColor("#e1e1e1");
                break;


            case "fairy":
                c= Color.parseColor("#DDA2DF");
                c2=Color.parseColor("#ffa9ef");
                break;


            case "fire":
                c= Color.parseColor("#F48130");c2=Color.parseColor("#ffd45b");break;


            case "fighting":
                c= Color.parseColor("#BE3027");c2=Color.parseColor("#ff9b9b");break;


            case "bug":
                c= Color.parseColor("#A8B822");c2=Color.parseColor("#ddf511");break;


            case "ghost":
                c= Color.parseColor("#705693");c2=Color.parseColor("#be8ffc");break;


            case "dark":
                c= Color.parseColor("#745945");c2=Color.parseColor("#cbc0ae");break;


            case "ice":
                c= Color.parseColor("#9BD8D8");c2=Color.parseColor("#aef7f6");break;


            case "water":
                c= Color.parseColor("#658FF1");c2=Color.parseColor("#8dc8ff");break;
            default:
                c= Color.parseColor("#bdb76b");c2=Color.parseColor("#d5d1a0");break;
        }

        if(s==1) {
            tv7.setBackgroundColor(c);
            r.setBackgroundColor(c2);
            tv.setTextColor(c2);
        }
        else
            tv8.setBackgroundColor(c);



    }





}



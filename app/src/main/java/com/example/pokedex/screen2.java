
package com.example.pokedex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.EventLogTags;
import android.util.EventLogTags.Description;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedex.Model.Pokedex_entries.Pokemon3;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.Model.Pokemon2;
import com.example.pokedex.Model.evolution.evolutionchain;
import com.example.pokedex.PokeApiService.pokeapiservice_get_evolution;
import com.example.pokedex.PokeApiService.pokeapiservice_get_info_pokemon;
import com.example.pokedex.PokeApiService.pokeapiservice_get_pokedex_entries;
import com.example.pokedex.Regions.specific_region_screen;
import com.example.pokedex.typee.specific_type_screen;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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
    int[] ranges=new int[6];

     int which_pokemon=0;
    int position;
    String url_ec="https://pokeapi.co/api/v2/evolution-chain/1/";

    int c3;
    String evolution_1,evolution_2,evolution_3,evolution_4;

    RelativeLayout r;


    int ht, wt,id;
    int temp;
    TextView hp,attack,defense,spattack,spdef,speed,et1,et2,et3;
CardView ecv1,ecv2,ecv3,rcv,tcv;

    List<String> positivelist = new ArrayList<>();
    String about=new String();

    String x;

    private Retrofit retrofit,retrofit2,retrofit3;
    TextView tv;
    TextView tv2;
    TextView tv3;
    TextView tv4;;
    ImageView ei1,ei2,ei3;
    CardView cardView;
    HorizontalBarChart chart;
    TextView tv7;
    TextView tv8;
    String y;
    TextView tv9,tv11;
    pokeapiservice_get_info_pokemon service;
    pokeapiservice_get_pokedex_entries service2;
    pokeapiservice_get_evolution service3;


    private static final String TAG = "screen2";
    Gson gson2;
    Pokemon p;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_pokemon);

        position = getIntent().getIntExtra("posn", 0);
        which_pokemon=0;


        chart=findViewById(R.id.chart);
        hp=findViewById(R.id.hp);
        attack=findViewById(R.id.attack);
        defense=findViewById(R.id.defense);
        spattack=findViewById(R.id.spattack);
        spdef=findViewById(R.id.spdef);
        speed=findViewById(R.id.speed);
        et1=findViewById(R.id.et1);
        ei1=findViewById(R.id.ei1);
        et2=findViewById(R.id.et2);
        ei2=findViewById(R.id.ei2);
        ecv1=findViewById(R.id.ecv1);
        ecv2=findViewById(R.id.ecv2);
        ecv3=findViewById(R.id.ecv3);
        et3=findViewById(R.id.et3);
        ei3=findViewById(R.id.ei3);
        rcv=findViewById(R.id.rcv);
        tcv=findViewById(R.id.tcv);


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

        cardView=findViewById(R.id.cardview);
        r=findViewById(R.id.relative_layout);

        tv8=findViewById(R.id.tv8);
        tv9=findViewById(R.id.tv9);
        tv11=findViewById(R.id.tv11);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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


                    for(int i=0;i<6;i++)
                    {
                        ranges[i]=pokemonAns.getStats().get(i).getBase_stat();
                        Log.d(TAG, "onResponse: "+pokemonAns.getStats().get(i).getBase_stat());
                    }

                    hp.setText("Hp: "+ranges[0]);
                    attack.setText("Attack: "+ranges[1]);
                    defense.setText("Defense: "+ranges[2]);
                    spattack.setText("Sp. Atk: "+ranges[3]);
                    spdef.setText("Sp. Def: "+ranges[4]);
                    speed.setText("speed :"+ranges[5]);



                    positivelist.add(pokemonAns.getTypes().get(0).getType().getName());
                    x=positivelist.get(0);
                    tv7.setText(x);
                    getcolor(x,1);
                    tv8.setVisibility(View.INVISIBLE);
                    if (pokemonAns.getTypes().size()==2)
                    {
                        positivelist.add(pokemonAns.getTypes().get(1).getType().getName());
                         y=positivelist.get(1);
                        tv8.setText(positivelist.get(1));
                        tv8.setVisibility(View.VISIBLE);
                        getcolor(y,2);

                    }
                    setData(6,ranges);

                    tv7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getBaseContext(), specific_type_screen.class);

                            int position2= type_to_no_converter(x);
                            intent.putExtra("type_sent",position2);
                           startActivity(intent);

                        }
                    });
                    tv8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getBaseContext(), specific_type_screen.class);

                            int position2= type_to_no_converter(y);
                            intent.putExtra("type_sent",position2);
                            startActivity(intent);

                        }
                    });



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

                    String reg;
                    int position2 = 0;
                    final int id2;
                    id2=pokemonAns.getId();


                    switch(pokemonAns.getGeneration().getName())
                    {
                        case "generation-i":tv11.setText("Kanto");position2=0; break;
                        case "generation-ii":tv11.setText("Johto");position2=1;break;
                        case "generation-iii":tv11.setText("Hoenn");position2=2;break;
                        case "generation-iv":tv11.setText("Sinnoh");position2=3;break;
                        case "generation-v" :tv11.setText("Unova");position2=4;break;
                        case "generation-vi":tv11.setText("Kalos");position2=5;break;
                        case "generation-vii":tv11.setText("Alola");position2=6;break;
                        default:tv11.setText("error can't be displayed");
                    }

                    final int finalPosition = position2;
                    tv11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getBaseContext(), specific_region_screen.class);
                            
                            intent.putExtra("region_sent", finalPosition);
                            startActivity(intent);


                        }
                    });

                   url_ec=pokemonAns.getEvolution_chain().getUrl();
                    Log.d(TAG, "onResponse: url_ec " +pokemonAns.getEvolution_chain().getUrl());




                    if(pokemonAns.getFlavor_text_entries().get(5).getLanguage().getName().equals("en")) {
                        about = pokemonAns.getFlavor_text_entries().get(5).getFlavor_text();
                        Log.d(TAG, "onResponse: accessing  1st");

                        tv9.setText(about);
                    }
                    else if(pokemonAns.getFlavor_text_entries().get(6).getLanguage().getName().equals("en"))
                    {
                        about = pokemonAns.getFlavor_text_entries().get(6).getFlavor_text();
                        tv9.setText(about);
                    }
                    else if(pokemonAns.getFlavor_text_entries().get(7).getLanguage().getName().equals("en"))
                    {
                        about = pokemonAns.getFlavor_text_entries().get(7).getFlavor_text();
                        tv9.setText(about);
                    }

                    retrofit3 = new Retrofit.Builder()
                            .baseUrl(url_ec)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    service3=retrofit3.create(pokeapiservice_get_evolution.class);

                    Call<evolutionchain> call3=service3.getJsonArrayData();

                    call3.enqueue(new Callback<evolutionchain>() {
                        @Override
                        public void onResponse(Call<evolutionchain> call, Response<evolutionchain> response) {
                            if(response.isSuccessful())
                            { final evolutionchain pokemonAns = response.body();


                                evolution_1=pokemonAns.getChain().getSpecies().getName();

                                if(pokemonAns.getChain().getSpecies().getName().equals(p.getName()))
                                {
                                    which_pokemon=1;
                                }

                                et1.setText(evolution_1);

                                Glide.with(getApplicationContext())
                                        .load("https://img.pokemondb.net/sprites/sword-shield/icon/" +evolution_1+ ".png")
                                        .centerCrop()
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .dontAnimate()
                                        .into(ei1);
                                Log.d(TAG, "onResponse: what is p.get name "+p.getName()+"  "+p.getNumber());

                                ecv1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(p.getName().equals(pokemonAns.getChain().getSpecies().getName()))
                                        { Toast.makeText(getApplicationContext(),"Same page ...",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            transition(1);
                                        }



                                    }
                                });


                                if(pokemonAns.getChain().getEvolves_to().size()==2) {


                                        evolution_2 = pokemonAns.getChain().getEvolves_to().get(1).getSpecies().getName();

                                        et2.setText(evolution_2);
                                    if(pokemonAns.getChain().getEvolves_to().get(1).getSpecies().getName().equals(p.getName()))
                                    {
                                        which_pokemon=2;
                                    }

                                        Glide.with(getApplicationContext())
                                                .load("https://img.pokemondb.net/sprites/sword-shield/icon/" + evolution_2 + ".png")
                                                .centerCrop()
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .dontAnimate()
                                                .into(ei2);

                                    ecv2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if(p.getName().equals(pokemonAns.getChain().getEvolves_to().get(1).getSpecies().getName()))
                                            { Toast.makeText(getApplicationContext(),"Same page ...",Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                transition(2);
                                            }



                                        }
                                    });



                                    evolution_3 = pokemonAns.getChain().getEvolves_to().get(0).getSpecies().getName();

                                    if(pokemonAns.getChain().getEvolves_to().get(0).getSpecies().getName().equals(p.getName()))
                                    {
                                        which_pokemon=3;
                                    }

                                        et3.setText(evolution_3);
                                        Glide.with(getApplicationContext())
                                                .load("https://img.pokemondb.net/sprites/sword-shield/icon/" + evolution_3 + ".png")
                                                .centerCrop()
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .dontAnimate()
                                                .into(ei3);

                                    Log.d(TAG, "onResponse: checking wether this part  of if is accessed");
                                    ecv3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if(pokemonAns.getChain().getEvolves_to().get(0).getSpecies().getName().equals(p.getName()))
                                            { Toast.makeText(getApplicationContext(),"Same page ...",Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                transition(3);
                                            }



                                        }
                                    });





                                }
                                else
                                {
                                    if(pokemonAns.getChain().getEvolves_to().size()!=0) {
                                        evolution_2 = pokemonAns.getChain().getEvolves_to().get(0).getSpecies().getName();
                                        et2.setText(evolution_2);
                                        if(pokemonAns.getChain().getEvolves_to().get(0).getSpecies().getName().equals(p.getName()))
                                        {
                                            which_pokemon=2;
                                        }

                                        Glide.with(getApplicationContext())
                                                .load("https://img.pokemondb.net/sprites/sword-shield/icon/" + evolution_2 + ".png")
                                                .centerCrop()
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .dontAnimate()
                                                .into(ei2);

                                        ecv2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if(pokemonAns.getChain().getEvolves_to().get(0).getSpecies().getName().equals(p.getName()))
                                                { Toast.makeText(getApplicationContext(),"Same page ...",Toast.LENGTH_SHORT).show();
                                                }
                                                else
                                                {
                                                    transition(2);
                                                }



                                            }
                                        });




                                        if( pokemonAns.getChain().getEvolves_to().get(0).getEvolves_to().size()!=0)
                                        {
                                            evolution_3=pokemonAns.getChain().getEvolves_to().get(0).getEvolves_to().get(0).getSpecies().getName();
                                            et3.setText(evolution_3);
                                            if(pokemonAns.getChain().getEvolves_to().get(0).getEvolves_to().get(0).getSpecies().getName().equals(p.getName()))
                                            {
                                                which_pokemon=3;
                                            }

                                            Glide.with(getApplicationContext())
                                                    .load("https://img.pokemondb.net/sprites/sword-shield/icon/" + evolution_3 + ".png")
                                                    .centerCrop()
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .dontAnimate()
                                                    .into(ei3);

                                            ecv3.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    if(pokemonAns.getChain().getEvolves_to().get(0).getEvolves_to().get(0).getSpecies().getName().equals(p.getName()))
                                                    { Toast.makeText(getApplicationContext(),"Same page ...",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else
                                                    {
                                                        transition(3);
                                                    }



                                                }
                                            });


                                        }
                                        else
                                        {
                                            ecv3.setVisibility(View.GONE);
                                        }

                                    }
                                    else
                                    {
                                        ecv2.setVisibility(View.GONE);
                                        ecv3.setVisibility(View.GONE);
                                    }
                                }




                            }

                        }

                        @Override
                        public void onFailure(Call<evolutionchain> call, Throwable t) {

                        }
                    });




                }
            }

            @Override
            public void onFailure(Call<Pokemon3> call, Throwable t) {

            }
        });
        Log.d(TAG, "onCreate: evolution_1 "+evolution_1);





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
            c3=c2;
            tv.setTextColor(c2);
        }
        else
            tv8.setBackgroundColor(c);



    }

    private  void setData(int count, int[] ranges)
    {
        ArrayList<BarEntry> yvals=new ArrayList<>();
        float barwidth=3f;
        float spaceforbar=5f;
        for(int i=0;i<6;i++)
        {
     int val= (ranges[i]);
     yvals.add(new BarEntry((5-i)*spaceforbar, (int) val));

        }

        YAxis y = chart.getAxisLeft();
        y.setAxisMaxValue(150);
        y.setAxisMinValue(0);
        y.setLabelCount(12);

        BarDataSet set1;
        set1=new BarDataSet(yvals,"stats");
        set1.setDrawValues(false);
        BarData data =new BarData(set1);


     set1.setColor(c3);
     rcv.setCardBackgroundColor(c3);
     ecv1.setCardBackgroundColor(c3);
        ecv2.setCardBackgroundColor(c3);
        tcv.setCardBackgroundColor(c3);
        ecv3.setCardBackgroundColor(c3);




        chart.setData(data);


        chart.setTouchEnabled(true);
        chart.setClickable(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setDoubleTapToZoomEnabled(false);

        chart.setDrawBorders(false);
        chart.setDrawGridBackground(false);

        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);

        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisLeft().setDrawAxisLine(false);

        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setDrawAxisLine(false);

        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisRight().setDrawAxisLine(false);

        chart.invalidate();



    }


    private int type_to_no_converter(String x)
    {
        int position;

        switch(x)
        {
            case "normal":position=0;break;
            case "fighting":position=1;break;
            case "flying":position=2;break;
            case "poison":position=3;break;
            case "ground":position=4;break;
            case "rock":position=5; break;
            case "bug":position=6;break;
            case "ghost":position=7;break;
            case "steel":position=8;break;
            case "fire":position=9;break;
            case "water":position=10;break;
            case "grass":position=11;break;
            case "electric":position=12;break;
            case "psychic":position=13;break;
            case "ice":position=14;break;
            case "dragon":position=15;break;
            case "dark":position=16;break;
            case "fairy":position=17;break;
            case "unknown":position=19;break;
            case"shadow":position=20;break;
            default:position=0;
        }


        return position;
    }

    public void transition(int what)
    {
        Intent intent=getIntent();
        if(which_pokemon==1)
        {
            if(what==2)
            {intent.putExtra("posn",p.getNumber());}
            else if(what==3)
            {
                intent.putExtra("posn",p.getNumber()+1);
            }
        }
        else if(which_pokemon==2)
        {
            if(what==1)
            {intent.putExtra("posn",p.getNumber()-2);}
            else if(what==3)
            {
                intent.putExtra("posn",p.getNumber());
            }
        }
        else if(which_pokemon==3)
        {
            if(what==1)
            {
                intent.putExtra("posn",p.getNumber()-3);
            }
            else if(what==2)
            {
                intent.putExtra("posn",p.getNumber()-2);
            }
        }

        startActivity(intent);
finish();
    }
    
    
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}



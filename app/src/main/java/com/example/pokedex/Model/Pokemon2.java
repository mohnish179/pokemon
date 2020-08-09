package com.example.pokedex.Model;

import com.example.pokedex.Model.Type.Types;

import java.util.ArrayList;

public class Pokemon2 {

    int height;
    int weight;
    ArrayList<Types> types;
    int id;
    ArrayList<Stats> stats;


    public Pokemon2(int height, int weight, ArrayList<Types> types) {
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.id=id;
    }

    public ArrayList<Stats> getStats() {
        return stats;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Types> getTypes() {
        return types;
    }
}

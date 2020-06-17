package com.example.pokedex.Model.Type;

public class Types {

    int slot;
    Type type;


    public Types(int slot, Type type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public Type getType() {
        return type;
    }
}

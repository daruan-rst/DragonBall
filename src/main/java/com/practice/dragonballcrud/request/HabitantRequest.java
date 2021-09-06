package com.practice.dragonballcrud.request;

import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;

public class HabitantRequest {

    String name;
    Races race;
    int powerLevel;
    boolean alive;

    public Habitant convert(HabitantRequest habitantRequest){
        return new Habitant(this.name, this.race, this.powerLevel, this.alive);
    }
}

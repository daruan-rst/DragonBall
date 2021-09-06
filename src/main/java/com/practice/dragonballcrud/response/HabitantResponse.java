package com.practice.dragonballcrud.response;

import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;

import java.util.List;
import java.util.stream.Collectors;

public class HabitantResponse {

    String name;
    Races race;
    int powerLevel;
    boolean alive;

    public HabitantResponse(Habitant habitant){
        this.name = habitant.getName();
        this.race = habitant.getRace();
        this.powerLevel = habitant.getPowerLevel();
        this.alive = habitant.isAlive();
    }

    public static List<HabitantResponse> convert(List<Habitant> habitants){
        return habitants.stream().map(HabitantResponse::new).collect(Collectors.toList());
    }


}

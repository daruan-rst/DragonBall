package com.practice.dragonballcrud.response;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HabitantResponse {

    private final int habitantId;
    private final String name;
    private final Races race;
    private final int powerLevel;
    private final boolean alive;
    private final City cityId;

    public HabitantResponse(Habitant habitant){
        this.habitantId = habitant.getId();
        this.name = habitant.getName();
        this.race = habitant.getRace();
        this.powerLevel = habitant.getPowerLevel();
        this.alive = habitant.isAlive();
        this.cityId = habitant.getCityId();
    }

    public static List<HabitantResponse> convert(List<Habitant> habitants){
        return habitants.stream().map(HabitantResponse::new).collect(Collectors.toList());
    }


}

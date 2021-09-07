package com.practice.dragonballcrud.request;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.repository.CityRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
@Getter
@Setter
public class HabitantRequest {

    int id;
    String name;
    Races race;
    int powerLevel;
    boolean alive;
    int cityId;

    public Habitant convert(CityRepository cityRepository){
        Optional<City> cityOptional = cityRepository.findById(cityId);
        City city = cityOptional.get();
        return new Habitant(getId(),this.name, this.race, this.powerLevel, this.alive, city);
    }

    public Habitant updateConvert(CityRepository cityRepository, int habitantId){
        Habitant updatedHabitant = convert(cityRepository);
        return new Habitant(habitantId,
                updatedHabitant.getName(),
                updatedHabitant.getRace(),
                updatedHabitant.getPowerLevel(),
                updatedHabitant.isAlive(),
                updatedHabitant.getCityId());
    }


}

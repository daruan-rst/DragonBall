package com.practice.dragonballcrud.request;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.DestroyedCity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestroyedCityRequest {


    public DestroyedCity convert(City city){
        return new DestroyedCity(city.getCityId(),
                city,city.getPlanetId());
    }

}

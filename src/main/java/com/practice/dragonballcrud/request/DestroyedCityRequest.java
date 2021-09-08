package com.practice.dragonballcrud.request;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.DestroyedCity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestroyedCityRequest {

    private String cityName;
    private long population;
    private float longitude;
    private float latitude;


    public DestroyedCity convert(City city){
        this.cityName = city.getCityName();
        this.population = city.getPopulation();
        this.longitude = city.getLongitude();
        this.latitude = city.getLatitude();

        return new DestroyedCity(city.getCityId(),
                this.cityName,
                this.population,
                this.longitude,
                this.latitude,
                city.getPlanetId());
    }

}

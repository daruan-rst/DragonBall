package com.practice.dragonballcrud.response;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.DestroyedCity;
import com.practice.dragonballcrud.entities.Planet;
import lombok.Getter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DestroyedCityResponse {

    private int destroyedCityId;
    private String cityName;
    private long population;
    private float longitude;
    private float latitude;
    private Planet planet;

    public DestroyedCityResponse(DestroyedCity cityThatWillBeDestroyed){
        this.destroyedCityId = cityThatWillBeDestroyed.getDestroyedCityId();
        this.cityName = cityThatWillBeDestroyed.getCityName();
        this.population = cityThatWillBeDestroyed.getPopulation();
        this.longitude = cityThatWillBeDestroyed.getLongitude();
        this.latitude = cityThatWillBeDestroyed.getLatitude();
        this.planet = cityThatWillBeDestroyed.getPlanet();
    }

    public static List<DestroyedCityResponse> convert(List<DestroyedCity> destroyedCities){
        return destroyedCities.stream().map(DestroyedCityResponse::new).collect(Collectors.toList());
    }

}

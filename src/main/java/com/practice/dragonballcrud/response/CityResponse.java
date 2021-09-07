package com.practice.dragonballcrud.response;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import lombok.Getter;


import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CityResponse {

    private final int cityId;
    private final String cityName;
    private final long population;
    private final float longitude;
    private final float latitude;
    private final Planet planetId;

    public CityResponse(City city){
        this.cityId = city.getCityId();
        this.cityName = city.getCityName();
        this.population = city.getPopulation();
        this.longitude  = city.getLongitude();
        this.latitude = city.getLatitude();
        this.planetId = city.getPlanetId();
    }

    public static List<CityResponse> convert(List<City> cities){
        return cities.stream().map(CityResponse::new).collect(Collectors.toList());
    }
}


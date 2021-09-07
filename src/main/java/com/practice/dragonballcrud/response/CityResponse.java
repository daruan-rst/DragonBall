package com.practice.dragonballcrud.response;

import com.practice.dragonballcrud.entities.City;


import java.util.List;
import java.util.stream.Collectors;

public class CityResponse {

    String cityName;
    long population;
    float longitude;
    float latitude;

    public CityResponse(City city){
        this.cityName = city.getCityName();
        this.population = city.getPopulation();
        this.longitude  = city.getLongitude();
        this.latitude = city.getLatitude();
    }

    public static List<CityResponse> convert(List<City> cities){
        return cities.stream().map(CityResponse::new).collect(Collectors.toList());
    }
}


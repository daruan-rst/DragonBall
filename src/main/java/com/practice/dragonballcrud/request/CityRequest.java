package com.practice.dragonballcrud.request;

import com.practice.dragonballcrud.entities.City;

public class CityRequest {

    String cityName;
    long population;
    float longitude;
    float latitude;

    public City convert(){
        return new City(this.cityName, this.population, this.longitude, this.latitude);
    }

}

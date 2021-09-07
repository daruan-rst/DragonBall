package com.practice.dragonballcrud.request;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.repository.PlanetRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class CityRequest {

    private int id;
    private String cityName;
    private long population;
    private float longitude;
    private float latitude;
    private String planetName;

    public City convert(PlanetRepository planetRepository){
        Optional<Planet> planetOptional = planetRepository.findById(planetName);
        Planet planet = planetOptional.get();
        return new City(getId(), this.cityName,this.population, this.longitude, this.latitude, planet);
    }

    public City updateConvert(PlanetRepository planetRepository, int cityId){
        City updatedCity = convert(planetRepository);
        return new City(cityId,
                updatedCity.getCityName(),
                updatedCity.getPopulation(),
                updatedCity.getLongitude(),
                updatedCity.getLatitude(),
                updatedCity.getPlanetId());}



}

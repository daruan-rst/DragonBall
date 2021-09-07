package com.practice.dragonballcrud.service;


import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public void updateCityPopulation(City city, long population){
        city.setPopulation(city.getPopulation() + population);
        cityRepository.save(city);
    }
}

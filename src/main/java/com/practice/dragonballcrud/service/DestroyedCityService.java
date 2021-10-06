package com.practice.dragonballcrud.service;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.DestroyedCity;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.DestroyedCityRepository;
import com.practice.dragonballcrud.request.CityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestroyedCityService {

    @Autowired
    private final DestroyedCityRepository destroyedCityRepository;

    @Autowired
    private final CityRepository cityRepository;

    private PlanetService planetService;

    public List<DestroyedCity> findAll(){
        return destroyedCityRepository.findAll();
    }

    public void remove(int id){
        destroyedCityRepository.deleteById(id);
    }

    public void wishForCityBack(int id){
        DestroyedCity onceDestroyedCity = destroyedCityRepository.findById(id).get();
        if(planetService.aWishIsPossible(onceDestroyedCity.getPlanet())){
            City rebornCity = CityRequest.destroyedCityConvert(onceDestroyedCity);
            cityRepository.save(rebornCity);
            planetService.updatePlanetPopulation(rebornCity.getPlanetId(), rebornCity.getPopulation());
            remove(id);
        }
    }

}

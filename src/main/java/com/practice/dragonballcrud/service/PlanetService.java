package com.practice.dragonballcrud.service;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.exceptions.ImpossibleWish;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.HabitantRepository;
import com.practice.dragonballcrud.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlanetService {

    final private HabitantRepository habitantRepository;
    final private CityRepository cityRepository;
    final private CityService cityService;
    final private PlanetRepository planetRepository;
    Planet planet;

    public void updatePlanetPopulation(Planet planet, long population){
        planet.setPlanetPopulation(planet.getPlanetPopulation() + population);
        planetRepository.save(planet);
    }

    public boolean doesPlanetHaveAtLeastOneNamek(Planet thisOnePlanet){
        List<City> cities = cityRepository.findCityByPlanetId(thisOnePlanet);
        Predicate<City> atLeastOneNamek = cityService::doesThisCityHaveAtLeastOneNamek;
        List<City> resultCities = cities.stream()
                .filter(atLeastOneNamek)
                .collect(Collectors.toList());
            return !resultCities.isEmpty();}


    public boolean aWishIsPossible(Planet planet){
        boolean wishIsPossible = doesPlanetHaveAtLeastOneNamek(planet) && planet.isHasDragonBalls();
        if(!wishIsPossible){throw new ImpossibleWish();}
        return wishIsPossible;}
}

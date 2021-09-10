package com.practice.dragonballcrud.service;


import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.HabitantRepository;
import com.practice.dragonballcrud.repository.PlanetRepository;
import com.practice.dragonballcrud.request.HabitantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PopulationService {

    private final HabitantRepository habitantRepository;
    private final CityRepository cityRepository;
    private final PlanetRepository planetRepository;
    private final PlanetService planetService;
    private final CityService cityService;



    public void updatePopulation(Habitant habitant, long population) {
        cityService.updateCityPopulation(habitant.getCityId(), population);
        planetService.updatePlanetPopulation(habitant.getCityId().getPlanetId(), population);
    }

    public void wishForSomeoneBackToLife(Habitant habitant) {
        Planet planet = habitant.getCityId().getPlanetId();
        if (planetService.aWishIsPossible(planet)) {
            habitant.setAlive(true);
            updatePopulation(habitant, 1);
            habitantRepository.save(habitant);
        }
    }

    public HabitantRequest generateRandomHabitant(String name) {
        List<Planet> allKnowPlanets= planetRepository.findAll();
        Planet randomPlanet = randomElementFromList(allKnowPlanets);
        List<City> allKnownCitiesInPlanet = cityRepository
                .findCityByPlanetId(randomPlanet);
        City randomCity = randomElementFromList(allKnownCitiesInPlanet);
        int powerLevel = randomInt(allKnowPlanets.size()*allKnownCitiesInPlanet.size()*100);
        Races race = Races.values()[randomInt(Races.values().length)];

        HabitantRequest habitantRequest = new HabitantRequest();
        habitantRequest.setId(0);
        habitantRequest.setName(name);
        habitantRequest.setRace(race);
        habitantRequest.setCityId(randomCity.getCityId());
        habitantRequest.setAlive(true);
        habitantRequest.setPowerLevel(powerLevel);

        return habitantRequest;
    }

    private <T> T randomElementFromList(List<T> everything){
        int length = everything.size();
        T chosenEntity = everything.get(randomInt(length));
        return chosenEntity;
    }

    private int randomInt(int n){
        Random rand = new Random();
        return rand.nextInt(n);
    }


}

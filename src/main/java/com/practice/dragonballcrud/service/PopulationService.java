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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PopulationService {

    private final HabitantRepository habitantRepository;

    private final CityRepository cityRepository;

    private final PlanetRepository planetRepository;

    private final PlanetService planetService;

    private final CityService cityService;

    public Habitant createHabitant(HabitantRequest habitantRequest){
        Habitant createdHabitant = habitantRequest.convert(cityRepository);
        habitantRepository.save(createdHabitant);
        if (createdHabitant.isAlive()) {
            updatePopulation(createdHabitant, 1);
        }
        return createdHabitant;
    }

    public List<Habitant> findByName(String name){
         return habitantRepository.findHabitantsByName(name);}

    public List<Habitant> findAll(){
        return habitantRepository.findAll();
    }

    public List<Habitant> findWhoIsAlive(){
        return habitantRepository.findAll().
                stream().filter(Habitant::isAlive)
                .collect(Collectors.toList());}

    public List<Habitant> ghostCitizen(){
        return habitantRepository.findAll().
                stream().filter(h -> !h.isAlive())
                .collect(Collectors.toList());}

    public List<Habitant> findByCityId(int cityId){
        return habitantRepository
                .findHabitantsByCityId(cityRepository
                        .getById(cityId));
    }

    public List<Habitant> findByRace(Races race){
        return habitantRepository.findByRace(race);
    }

    public Habitant updateId(
            int id,
            HabitantRequest habitantRequest){
        Habitant habitant = habitantRequest.updateConvert(cityRepository, id);
        habitantRepository.save(habitant);
        return habitant;
    }

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

    public Habitant kill(int id){
        Habitant habitant = habitantRepository.findById(id);
        habitant.setAlive(false);
        updatePopulation(habitant, -1);
        habitantRepository.save(habitant);
        return habitant;
    }

    public Habitant wishBacktoLife(int id){
        Habitant habitant = habitantRepository.findById(id);
        wishForSomeoneBackToLife(habitant);
        System.out.println("Your wish has been fulfilled!\n"+habitant.getName() + " was brought back to live");
    return habitant;
    }

    public void remove(int id){
        updatePopulation(habitantRepository.findById(id), -1);
        habitantRepository.deleteById(id);
    }

    private <T> T randomElementFromList(List<T> everything){
        int length = everything.size();
        return everything.get(randomInt(length));
    }

    private int randomInt(int n){
        Random rand = new Random();
        return rand.nextInt(n);
    }


}

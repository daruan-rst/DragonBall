package com.practice.dragonballcrud.service;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.exceptions.ImpossibleWish;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.PlanetRepository;
import com.practice.dragonballcrud.request.PlanetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlanetService {

    final private CityRepository cityRepository;

    final private PlanetRepository planetRepository;

    final private PlanetCityService cityService;


   public Planet createPlanet(PlanetRequest planetRequest){
       Planet createdPlanet = planetRequest.convert();
       planetRepository.save(createdPlanet);
       return createdPlanet;
   }

    public List<Planet> findAll(){return planetRepository.findAll();}

   public List<Planet> hasDragonBalls(){
       return findAll().stream()
               .filter(Planet::isHasDragonBalls)
               .collect(Collectors.toList());
   }

   public List<Planet> hasPopulationGreaterThan(long population){
       return planetRepository.findPlanetByPlanetPopulationGreaterThan(population);
   }

    public void updatePlanetPopulation(Planet planet, long population){
        planet.setPlanetPopulation(planet.getPlanetPopulation() + population);
        planetRepository.save(planet);
    }

    public Planet update(String planetName,PlanetRequest planetRequest){
        Planet updatedPlanet = planetRequest.updateConvert(planetName);
        planetRepository.save(updatedPlanet);
        return updatedPlanet;
    }

    public void remove(String planetName){
        planetRepository.deleteById(planetName);
    }

    private boolean doesPlanetHaveAtLeastOneNamek(Planet thisOnePlanet){
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

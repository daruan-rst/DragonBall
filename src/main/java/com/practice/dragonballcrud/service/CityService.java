package com.practice.dragonballcrud.service;


import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.DestroyedCityRepository;
import com.practice.dragonballcrud.repository.HabitantRepository;

import com.practice.dragonballcrud.repository.PlanetRepository;
import com.practice.dragonballcrud.request.CityRequest;
import com.practice.dragonballcrud.request.DestroyedCityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {


    final private CityRepository cityRepository;

    final private HabitantRepository habitantRepository;

    private final PlanetRepository planetRepository;

    private final DestroyedCityRepository destroyedCityRepository;

    private final PlanetCityService planetService;

    @Transactional
    public City createCity(CityRequest cityRequest){
        City city = cityRequest.convert(planetRepository);
        cityRepository.save(city);
        planetService.updatePlanetPopulation(city.getPlanetId(), city.getPopulation());
        return city;
    }

    public List<City> findAll(){
        return cityRepository.findAll();
    }

    public List<City> hasPopulationGreaterThan(long population){
        return cityRepository.findCityByPopulationGreaterThan(population);
    }

    public void updateCityPopulation(City city, long population){
        city.setPopulation(city.getPopulation() + population);
        cityRepository.save(city);
    }

    public City updateCIty(int id, CityRequest cityRequest){
        City updatedCity = cityRequest.updateConvert(planetRepository, id);
        cityRepository.save(updatedCity);
        return updatedCity;
    }

    public boolean cityIsPresent(int id){
        return this.cityRepository.findById(id).isPresent();
    }

    public boolean doesThisCityHaveAtLeastOneNamek(City city){
        List<Habitant> cityHabitants = habitantRepository.findHabitantsByCityId(city);
        Predicate<Habitant> byNamekRace = habitant -> habitant.getRace() == Races.NAMEK;
        List<Habitant> namekHabitants = cityHabitants.stream()
                .filter(byNamekRace)
                .collect(Collectors.toList());
        return !namekHabitants.isEmpty();}

    public List<City> findByPlanet(String planetName){
            return cityRepository
                    .findCityByPlanetId(planetRepository
                    .findById(planetName)
                    .get());
        }

    public void remove(int id){
        cityRepository.deleteById(id);
    }

    public void destroyCity(int id){
        City destroyedCity = cityRepository.findById(id).get();
        destroyedCityRepository.save(new DestroyedCityRequest().
                convert(destroyedCity));
        planetService.updatePlanetPopulation(destroyedCity.getPlanetId(), -destroyedCity.getPopulation());
        habitantRepository.deleteAllByCityId(destroyedCity);
    }

}

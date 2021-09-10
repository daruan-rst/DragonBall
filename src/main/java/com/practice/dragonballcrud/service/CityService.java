package com.practice.dragonballcrud.service;


import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.HabitantRepository;

import com.practice.dragonballcrud.repository.PlanetRepository;
import com.practice.dragonballcrud.response.CityResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    final private CityRepository cityRepository;
    final private HabitantRepository habitantRepository;
    private final PlanetRepository planetRepository;



    public void updateCityPopulation(City city, long population){
        city.setPopulation(city.getPopulation() + population);
        cityRepository.save(city);
    }

    public boolean doesThisCityHaveAtLeastOneNamek(City city){
        List<Habitant> cityHabitants = habitantRepository.findHabitantsByCityId(city);
        Predicate<Habitant> byNamekRace = habitant -> habitant.getRace() == Races.NAMEK;
        List<Habitant> namekHabitants = cityHabitants.stream()
                .filter(byNamekRace)
                .collect(Collectors.toList());
        return !namekHabitants.isEmpty();}

    public ResponseEntity<List<CityResponse>> findByPlanet(String planetName){
            return ResponseEntity.ok(CityResponse
                    .convert(cityRepository
                            .findCityByPlanetId(planetRepository
                                    .findById(planetName)
                                    .get())));
        }




}

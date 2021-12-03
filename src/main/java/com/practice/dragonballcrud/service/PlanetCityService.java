package com.practice.dragonballcrud.service;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.DestroyedCityRepository;
import com.practice.dragonballcrud.repository.HabitantRepository;
import com.practice.dragonballcrud.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanetCityService {

    final private HabitantRepository habitantRepository;

    private final PlanetRepository planetRepository;


    public boolean doesThisCityHaveAtLeastOneNamek(City city){
        List<Habitant> cityHabitants = habitantRepository.findHabitantsByCityId(city);
        Predicate<Habitant> byNamekRace = habitant -> habitant.getRace() == Races.NAMEK;
        List<Habitant> namekHabitants = cityHabitants.stream()
                .filter(byNamekRace)
                .collect(Collectors.toList());
        return !namekHabitants.isEmpty();}

    public void updatePlanetPopulation(Planet planet, long population){
        planet.setPlanetPopulation(planet.getPlanetPopulation() + population);
        planetRepository.save(planet);
    }


}

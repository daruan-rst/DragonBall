package com.practice.dragonballcrud.service;


import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.HabitantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PopulationService {

    private final HabitantRepository habitantRepository;
    private final PlanetService planetService;
    private final CityService cityService;


    public void updatePopulation(Habitant habitant, long population){
        cityService.updateCityPopulation(habitant.getCityId(), population);
        planetService.updatePlanetPopulation(habitant.getCityId().getPlanetId(), population);
    }

    public void wishForSomeoneBackToLife(Habitant habitant){
        Planet planet = habitant.getCityId().getPlanetId();
        if (planetService.aWishIsPossible(planet)){
            habitant.setAlive(true);
            updatePopulation(habitant, 1);
            habitantRepository.save(habitant);
        }

    }
}

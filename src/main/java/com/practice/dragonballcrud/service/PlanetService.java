package com.practice.dragonballcrud.service;

import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PlanetService {
    final private PlanetRepository planetRepository;

    public void updatePlanetPopulation(Planet planet, long population){
        planet.setPlanetPopulation(planet.getPlanetPopulation() + population);
        planetRepository.save(planet);
    }
}

package com.practice.dragonballcrud.controller;


import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.repository.PlanetRepository;
import com.practice.dragonballcrud.response.PlanetResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/planet")
public class PlanetController {

    final private PlanetRepository planetRepository;
    private final PlanetResponse planetResponse;

    @GetMapping("/findAll")
    public List<PlanetResponse> findAll(){
        List<Planet> allPlanets = planetRepository.findAll();
        return PlanetResponse.convert(allPlanets);
    }

    @GetMapping("/hasDragonBall")
    public List<PlanetResponse> hasDragonBall(){
        List<Planet> planetsHaveDragonBall = planetRepository.findByHasDragonBalls();
        return planetResponse.convert(planetsHaveDragonBall);
    }

    @GetMapping("/hasPopulationGreaterThan/{population}")
    public List<PlanetResponse> hasPopulationGreaterThan(long population){
        List<Planet> planets = planetRepository.findPlanetByPlanetPopulationGreaterThan(population);
        return planetResponse.convert(planets);
    }


}

package com.practice.dragonballcrud.response;

import com.practice.dragonballcrud.entities.Planet;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlanetResponse {

    private final String planetName;
    private final long planetPopulation;
    private final boolean hasDragonBalls;

    public PlanetResponse (Planet planet){
        this.planetName = planet.getPlanetName();
        this.planetPopulation = planet.getPlanetPopulation();
        this.hasDragonBalls = planet.isHasDragonBalls();
    }

    public static List<PlanetResponse> convert(List<Planet> planets){
        return planets.stream().map(PlanetResponse::new).collect(Collectors.toList());
    }
}

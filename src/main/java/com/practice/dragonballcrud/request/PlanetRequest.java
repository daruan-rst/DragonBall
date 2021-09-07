package com.practice.dragonballcrud.request;

import com.practice.dragonballcrud.entities.Planet;

public class PlanetRequest {

    String planetName;
    long planetPopulation;
    boolean hasDragonBalls;

    public Planet convert(PlanetRequest planetRequest){
        return new Planet(this.planetName, this.planetPopulation, this.hasDragonBalls);
    }

    public Planet updateConvert(String planetName){
        return new Planet(planetName, this.planetPopulation, this.hasDragonBalls);
    }

}

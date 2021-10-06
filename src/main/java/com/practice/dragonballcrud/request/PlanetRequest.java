package com.practice.dragonballcrud.request;

import com.practice.dragonballcrud.entities.Planet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlanetRequest {

    private String planetName;
    private long planetPopulation;
    private boolean hasDragonBalls;

    public Planet convert(){
        return new Planet(this.planetName, this.planetPopulation, this.hasDragonBalls);
    }

    public Planet updateConvert(String planetName){
        return new Planet(planetName, this.planetPopulation, this.hasDragonBalls);
    }

}

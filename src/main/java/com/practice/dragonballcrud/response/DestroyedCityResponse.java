package com.practice.dragonballcrud.response;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.DestroyedCity;
import com.practice.dragonballcrud.entities.Planet;
import lombok.Getter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DestroyedCityResponse {

    private int destroyedCityId;
    private City destroyedCity;
    private Planet planet;

    public DestroyedCityResponse(DestroyedCity cityThatWillBeDestroyed){
        this.destroyedCityId = cityThatWillBeDestroyed.getDestroyedCityId();
        this.destroyedCity = cityThatWillBeDestroyed.getDestroyedCity();
        this.planet = cityThatWillBeDestroyed.getPlanet();
    }

    public static List<DestroyedCityResponse> convert(List<DestroyedCity> destroyedCities){
        return destroyedCities.stream().map(DestroyedCityResponse::new).collect(Collectors.toList());
    }

}

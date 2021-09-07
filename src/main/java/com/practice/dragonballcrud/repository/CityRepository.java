package com.practice.dragonballcrud.repository;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Integer > {

    List<City> findCityByPopulationGreaterThan(long population);
    List<City> findCityByPlanetId(Planet planetName);

}

package com.practice.dragonballcrud.repository;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Integer > {

    List<City> findCityByPopulationGreaterThan(long population);
    List<City> findCityByPlanetId(Planet planetName);
    City findByCityId(int cityId);


}

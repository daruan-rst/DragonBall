package com.practice.dragonballcrud.repository;

import com.practice.dragonballcrud.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet, String> {

    List<Planet> findByHasDragonBalls();
    List<Planet> findPlanetByPlanetPopulationGreaterThan(long Population);

}

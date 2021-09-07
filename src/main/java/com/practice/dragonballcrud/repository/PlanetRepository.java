package com.practice.dragonballcrud.repository;

import com.practice.dragonballcrud.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, String> {

    List<Planet> findPlanetByPlanetPopulationGreaterThan(long Population);

}

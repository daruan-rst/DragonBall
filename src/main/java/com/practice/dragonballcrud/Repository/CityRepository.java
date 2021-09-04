package com.practice.dragonballcrud.Repository;

import com.practice.dragonballcrud.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByPopulationGreaterThanEqual(long population);
}

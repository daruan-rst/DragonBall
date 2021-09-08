package com.practice.dragonballcrud.repository;

import com.practice.dragonballcrud.entities.DestroyedCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestroyedCityRepository extends JpaRepository<DestroyedCity, Integer> {
}

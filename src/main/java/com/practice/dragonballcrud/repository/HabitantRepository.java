package com.practice.dragonballcrud.repository;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitantRepository extends JpaRepository<Habitant, Integer> {

    List<Habitant> findByRace(Races race);
    Habitant findById(int id);
    List<Habitant> findHabitantsByName(String name);
    List<Habitant> findHabitantsByCityId(City cityId);
    void deleteAllByCityId(City cityId);

}

package com.practice.dragonballcrud.repository;

import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitantRepository extends JpaRepository<Habitant, Integer> {

    List<Habitant> findByRace(Races race);
    Habitant findById(int id);
    List<Habitant> findHabitantsByName(String name);
    List<Habitant> findHabitantsByAlive();
    List<Habitant> findHabitantsByAliveFalse();
}

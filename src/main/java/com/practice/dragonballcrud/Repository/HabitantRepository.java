package com.practice.dragonballcrud.Repository;

import com.practice.dragonballcrud.Entities.Habitant;
import com.practice.dragonballcrud.Enums.Races;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitantRepository extends JpaRepository<Habitant, Integer> {

    List<Habitant> findByRace(Races race);
}

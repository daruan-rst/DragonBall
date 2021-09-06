package com.practice.dragonballcrud.entities;

import com.practice.dragonballcrud.enums.Races;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Habitant {

    @Id

    int id;

    String name;

    Races race;
    int powerLevel;
    boolean alive;
    @ManyToOne
    City city;

    public Habitant(String name, Races race, int powerLevel, boolean alive) {
        this.name = name;
        this.race = race;
        this.powerLevel = powerLevel;
        this.alive = alive;
    }

}

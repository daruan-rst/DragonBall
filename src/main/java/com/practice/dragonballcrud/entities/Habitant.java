package com.practice.dragonballcrud.entities;

import com.practice.dragonballcrud.enums.Races;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Habitant {

    @Id
    @GeneratedValue
    int id;

    String name;

    @Enumerated(EnumType.STRING)
    Races race;
    @Column(name = "power_level")
    int powerLevel;
    boolean alive;

    @ManyToOne
    @JoinColumn(name="cityId", referencedColumnName = "cityId")
    City cityId;

    public Habitant(String name, Races race, int powerLevel, boolean alive) {
        this.name = name;
        this.race = race;
        this.powerLevel = powerLevel;
        this.alive = alive;
    }

}

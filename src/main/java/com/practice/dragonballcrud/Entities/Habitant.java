package com.practice.dragonballcrud.Entities;

import com.practice.dragonballcrud.Enums.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Habitant {

    @Id
    long id;
    String name;
    Race race;
    int powerLevel;
    boolean alive;

    @ManyToOne
    City city;

}

package com.practice.dragonballcrud.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Planet {

    @Id
    String planetName;
    long planetPopulation;
    boolean hasDragonBalls;

}

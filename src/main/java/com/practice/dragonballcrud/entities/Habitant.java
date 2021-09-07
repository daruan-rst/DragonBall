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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Races race;
    @Column(name = "power_level")
    private int powerLevel;
    private boolean alive;

    @ManyToOne
    @JoinColumn(name="cityId", referencedColumnName = "cityId")
    private City cityId;



}

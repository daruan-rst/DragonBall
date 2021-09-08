package com.practice.dragonballcrud.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class DestroyedCity {

    @Id
    private int destroyedCityId;

    @OneToOne
    @JoinColumn(name = "destroyed_city", referencedColumnName ="city_id")
    private City destroyedCity;


    @ManyToOne
    @JoinColumn(name = "planet", referencedColumnName = "planet_name")
    private Planet planet;
}

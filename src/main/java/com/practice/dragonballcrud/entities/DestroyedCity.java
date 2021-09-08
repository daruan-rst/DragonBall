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
@Table
@Entity
public class DestroyedCity {

    @Id
    private int destroyedCityId;
    private String cityName;
    private long population;
    private float longitude;
    private float latitude;


    @ManyToOne
    @JoinColumn(name = "planet", referencedColumnName = "planet_name")
    private Planet planet;
}

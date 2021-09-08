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
public class City {

    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private int cityId;
    private String cityName;
    private long population;
    private float longitude;
    private float latitude;

    @ManyToOne
    @JoinColumn(name = "planet_id" , referencedColumnName = "planet_name")
    private Planet planetId;

}

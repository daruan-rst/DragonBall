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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cityId")
    private int cityId;
    private String cityName;
    private long population;
    private float longitude;
    private float latitude;

    @ManyToOne
    @JoinColumn(name = "planetId" , referencedColumnName = "planet_name")
    private Planet planetId;



}

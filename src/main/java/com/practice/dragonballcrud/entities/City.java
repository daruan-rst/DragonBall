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
    int cityId;
    String cityName;
    long population;
    float longitude;
    float latitude;


    public City(String cityName, long population, float longitude, float latitude) {
        this.cityName = cityName;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @ManyToOne
    Planet planet;

}

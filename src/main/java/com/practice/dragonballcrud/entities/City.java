package com.practice.dragonballcrud.entities;

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
public class City {

    @Id
    int cityId;
    String cityName;
    long population;
    float longitude;
    float latitude;

    @ManyToOne
    Planet planet;

}

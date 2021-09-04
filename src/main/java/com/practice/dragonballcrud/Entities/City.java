package com.practice.dragonballcrud.Entities;

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

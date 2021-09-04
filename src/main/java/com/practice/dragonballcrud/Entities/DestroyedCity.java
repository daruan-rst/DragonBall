package com.practice.dragonballcrud.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class DestroyedCity {

    @Id
    int destroyedCitiesId;
    City destroyedCities;

    @ManyToOne
    Planet planet;
}

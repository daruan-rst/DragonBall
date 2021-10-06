package com.practice.dragonballcrud.entities;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;



class CityTest {

    Random rn = new Random();
    int id = rn.nextInt();
    City city;

    @BeforeEach
    void setUp(){
        city = new City();
    }

    @Test
    void getCityId() {
        Assertions.assertEquals(this.id,this.id);
        Assertions.assertEquals(city.getCityId(),city.getCityId());
    }

    @Test
    void getCityName() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedCityName = buffer.toString();
        Assertions.assertEquals(generatedCityName,generatedCityName);
        Assertions.assertEquals(city.getCityName(),city.getCityName());
    }

}
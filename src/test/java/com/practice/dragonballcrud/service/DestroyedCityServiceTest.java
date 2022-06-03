package com.practice.dragonballcrud.service;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.exceptions.ImpossibleWish;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.DestroyedCityRepository;
import com.practice.dragonballcrud.repository.HabitantRepository;
import com.practice.dragonballcrud.repository.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DestroyedCityServiceTest {

    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityService cityService;
    @Autowired
    private DestroyedCityService destroyedCityService;
    @Autowired
    private DestroyedCityRepository destroyedCityRepository;
    @Autowired
    private HabitantRepository habitantRepository;


    @Test
    @DirtiesContext
    void wishForCityBack() {
        populateTest(true,Races.NAMEK);
        cityService.destroyCity(2);
        destroyedCityService.wishForCityBack(2);
        assertEquals(0,destroyedCityRepository.count());
    }

    @Test
    @DirtiesContext
    void wishForCityBack_wrongRace() {
        populateTest(true,Races.SAYAN);
        cityService.destroyCity(2);
        assertThrows(ImpossibleWish.class,() ->
        {
            destroyedCityService.wishForCityBack(2);
        });
    }

    @Test
    @DirtiesContext
    void wishForCityBack_DoesNotHaveDragonBall() {
        populateTest(false,Races.NAMEK);
        cityService.destroyCity(2);
        assertThrows(ImpossibleWish.class,() ->
        {
            destroyedCityService.wishForCityBack(2);
        });
    }

    private void populateTest(boolean hasDragonBalls, Races race){
        Planet testPlanet = new Planet("testPlanet",10,hasDragonBalls);
        City testCity = new City(1,"testCity",10,0,0,testPlanet);
        City upToBeDestroyedCity = new City(2,"destroyMe",10,0,0,testPlanet);
        Habitant testHabitant = new Habitant(1,"regular john", race,9001,true,testCity);
        planetRepository.save(testPlanet);
        cityRepository.save(testCity);
        cityRepository.save(upToBeDestroyedCity);
        habitantRepository.save(testHabitant);
    }
}
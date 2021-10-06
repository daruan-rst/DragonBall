package com.practice.dragonballcrud.service;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.request.CityRequest;
import com.practice.dragonballcrud.request.PlanetRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@WebAppConfiguration
class CityServiceTest {

    @Autowired
    CityService cityService;

    @Autowired
    PlanetService planetService;

    @Test
    void createCity() {
        PlanetRequest testPlanetRequest = new PlanetRequest("Earth", 10, true);
        Planet testPlanet = planetService.createPlanet(testPlanetRequest);
        CityRequest testCityRequest = new CityRequest(0,"testName",10,11,12,"Earth");
        City testCity = cityService.createCity(testCityRequest);
        assertEquals("testName", testCity.getCityName());
        assertEquals(10, testCity.getPopulation());
        assertEquals(11, testCity.getLongitude());
        assertEquals(12, testCity.getLatitude());
    }



    @Test
    void destroyCity() {
        PlanetRequest testPlanetRequest = new PlanetRequest("Earth", 10, true);
        Planet testPlanet = planetService.createPlanet(testPlanetRequest);
        CityRequest testCityRequest = new CityRequest(0,"testName",10,11,12,"Earth");
        City testCity = cityService.createCity(testCityRequest);
        assertTrue(cityService.cityIsPresent(testCity.getCityId()));
        cityService.destroyCity(testCity.getCityId());
    }
}
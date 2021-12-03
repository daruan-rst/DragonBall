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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@WebAppConfiguration
class CityServiceTest {

    @Autowired
    CityService cityService;

    @Autowired
    PlanetService planetService;

    private City testSamples(String name){
    PlanetRequest testPlanetRequest = new PlanetRequest("Earth", 10, true);
    Planet testPlanet = planetService.createPlanet(testPlanetRequest);
    CityRequest testCityRequest = new CityRequest(0,name,10,11,12,testPlanet.getPlanetName());
    return cityService.createCity(testCityRequest);}

    @Test
    void createCity() {
        City testCity = testSamples("testName");
        assertEquals("testName", testCity.getCityName());
        assertEquals(10, testCity.getPopulation());
        assertEquals(11, testCity.getLongitude());
        assertEquals(12, testCity.getLatitude());
    }



    @Test
    void destroyCity() {
        City testCity = testSamples("testName");
        assertTrue(cityService.cityIsPresent(testCity.getCityId()));
        cityService.destroyCity(testCity.getCityId());
    }

    @Test
    void hasPopulationGreaterThan(){
        City testCity = testSamples("testName");
        City secondTestCity = testSamples("secondTest");
        List<City> cities = cityService.hasPopulationGreaterThan(9);
        assertTrue(!cities.isEmpty());
    }
}
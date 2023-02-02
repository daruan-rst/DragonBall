package com.practice.dragonballcrud.service;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.DestroyedCityRepository;
import com.practice.dragonballcrud.request.CityRequest;
import com.practice.dragonballcrud.request.PlanetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DirtiesContext
class CityServiceTest {

    @Autowired
    private CityService cityService;
    @Autowired
    private PlanetService planetService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DestroyedCityRepository destroyedCityRepository;

    @Autowired
    private PopulationService populationService;

    @BeforeEach
    private void setUp() {
        PlanetRequest testPlanetRequest = new PlanetRequest("Earth", 10, true);
        Planet testPlanet = planetService.createPlanet(testPlanetRequest);
        City firstTestCity = cityService
                .createCity(
                        new CityRequest(1, "firstTestName", 10L, 1f, 1f, "Earth"));

        City secondTestCity = cityService
                .createCity(
                        new CityRequest(2, "secondTestName", 5L, 1f, 1f, "Earth"));
    }

    @Test
    @DirtiesContext
    void testCreateCity() {
        City testCity = cityService
                .createCity(
                        new CityRequest(3, "testName", 1L, 1f, 1f, "Earth"));

        City city = cityRepository.findAll().get(2);
        assertEquals(3, cityRepository.count());
        assertEquals(testCity.getCityName(), city.getCityName());
        assertEquals(1, city.getPopulation());
    }


    @Test
    void testDestroyCity() {
        cityService.destroyCity(1);
        assertEquals(1, destroyedCityRepository.findAll().get(0).getDestroyedCityId());
    }

    @Test
    void testHasPopulationGreaterThan() {
        assertEquals(1, cityService.hasPopulationGreaterThan(5L).size());
        assertEquals(2, cityService.hasPopulationGreaterThan(4L).size());
    }

    @Test
    void testFindByPlanet() {
        assertEquals(2, cityService.findByPlanet("Earth").size());

    }

    @Test
    void testFindAll() {
        assertEquals(2, cityService.findAll().size());
    }

    @Test
    void testRemove(){
        cityService.remove(1);
        assertEquals(1,cityService.findAll().size());
    }
}
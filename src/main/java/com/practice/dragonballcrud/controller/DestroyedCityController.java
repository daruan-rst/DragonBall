package com.practice.dragonballcrud.controller;

import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.DestroyedCity;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.DestroyedCityRepository;
import com.practice.dragonballcrud.response.DestroyedCityResponse;
import com.practice.dragonballcrud.service.PlanetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/destroyed-cities")
public class DestroyedCityController {

    private final DestroyedCityRepository destroyedCityRepository;
    private final CityRepository cityRepository;
    private final PlanetService planetService;

    @GetMapping("/find-all")
    public ResponseEntity<List<DestroyedCityResponse>> findAll() {
        List<DestroyedCity> allDestroyedCities = destroyedCityRepository.findAll();
        return ResponseEntity.ok(DestroyedCityResponse.convert(allDestroyedCities));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<DestroyedCityResponse> remove(@PathVariable int id) {
        destroyedCityRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("wish-for-city-to-return/{id}")
    public void destroyCity(@PathVariable int id) {
        DestroyedCity onceDestroyedCity = destroyedCityRepository.findById(id).get();
        if(planetService.aWishIsPossible(onceDestroyedCity.getPlanet())){
            City rebornCity = onceDestroyedCity.getDestroyedCity();
            cityRepository.save(rebornCity);
            planetService.updatePlanetPopulation(rebornCity.getPlanetId(), rebornCity.getPopulation());
            remove(id);
        }
    }



}

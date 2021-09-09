package com.practice.dragonballcrud.controller;


import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.exceptions.ParamNotFoundException;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.DestroyedCityRepository;
import com.practice.dragonballcrud.repository.PlanetRepository;
import com.practice.dragonballcrud.request.CityRequest;
import com.practice.dragonballcrud.request.DestroyedCityRequest;
import com.practice.dragonballcrud.response.CityResponse;
import com.practice.dragonballcrud.service.CityService;
import com.practice.dragonballcrud.service.PlanetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/cities")
public class CityController {

    final private CityRepository cityReposiory;
    final private PlanetRepository planetRepository;
    final private PlanetService planetService;
    final private CityService cityService;
    final private DestroyedCityRepository destroyedCityRepository;

    @PostMapping
    public ResponseEntity<CityResponse> createCity(
            @RequestBody CityRequest cityRequest,
            UriComponentsBuilder uriComponentsBuilder
    ){
        City createdCity = cityRequest.convert(planetRepository);
        cityReposiory.save(createdCity);
        planetService.updatePlanetPopulation(createdCity.getPlanetId(), createdCity.getPopulation());
        URI uri = uriComponentsBuilder.path("/city/{cityId}")
        .buildAndExpand(createdCity.getCityId()).toUri();
        return ResponseEntity.created(uri).body(new CityResponse(createdCity));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<CityResponse>> findAll() {
        List<City> allCities = cityReposiory.findAll();
        return ResponseEntity.ok(CityResponse.convert(allCities));
    }


    @GetMapping("/has-population-greater-than/{population}")
    public ResponseEntity<List<CityResponse>> hasPopulationGreaterThan(@RequestParam long population) {
        List<City> cities = cityReposiory.findCityByPopulationGreaterThan(population);
        return ResponseEntity.ok(CityResponse.convert(cities));
    }

    @GetMapping("/find-by-planet/{planetName}")
    public ResponseEntity<List<CityResponse>> findByPlanet(@RequestParam String planetName){
        try {
            return ResponseEntity.ok(CityResponse
                    .convert(cityReposiory
                    .findCityByPlanetId(planetRepository
                            .findById(planetName)
                            .get())));
        }catch(ParamNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CityResponse> update(
            @PathVariable int id,
            @RequestBody CityRequest cityRequest
    ) {
        if (this.cityReposiory.findById(id).isPresent()) {
            City updatedCity = cityRequest.updateConvert(planetRepository, id);
            cityReposiory.save(updatedCity);
            return ResponseEntity.ok(new CityResponse(updatedCity));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<CityResponse> remove(@PathVariable int id) {
        cityReposiory.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/destroy-city/{id}")
    public ResponseEntity<CityResponse> destroyCity(@PathVariable int id){
        City destroyedCity = cityReposiory.findById(id).get();
        destroyedCityRepository.save(new DestroyedCityRequest().
                convert(destroyedCity));
        planetService.updatePlanetPopulation(destroyedCity.getPlanetId(), -destroyedCity.getPopulation());
        return remove(id);}
}

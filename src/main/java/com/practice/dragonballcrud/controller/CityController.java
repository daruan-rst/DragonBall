package com.practice.dragonballcrud.controller;


import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.exceptions.ParamNotFoundException;
import com.practice.dragonballcrud.repository.DestroyedCityRepository;
import com.practice.dragonballcrud.request.CityRequest;
import com.practice.dragonballcrud.response.CityResponse;
import com.practice.dragonballcrud.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/cities")
public class CityController {

    @Autowired
    final private CityService cityService;

    final private DestroyedCityRepository destroyedCityRepository;

    @PostMapping
    public ResponseEntity<CityResponse> createCity(
            @RequestBody CityRequest cityRequest,
            UriComponentsBuilder uriComponentsBuilder
    ){
        City createdCity = cityService.createCity(cityRequest);
        URI uri = uriComponentsBuilder.path("/city/{cityId}")
                .buildAndExpand(createdCity.getCityId()).toUri();
        return ResponseEntity.created(uri).body(new CityResponse(createdCity));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<CityResponse>> findAll(){
        return ResponseEntity.ok(CityResponse.convert(cityService.findAll()));
    }


    @GetMapping("/has-population-greater-than/{population}")
    public ResponseEntity<List<CityResponse>> hasPopulationGreaterThan(@RequestParam long population) {
        List<City> cities = cityService.hasPopulationGreaterThan(population);
        return ResponseEntity.ok(CityResponse.convert(cities));
    }

    @GetMapping("/find-by-planet/{planetName}")
    public ResponseEntity<List<CityResponse>> findByPlanet(@RequestParam String planetName){
        try {
            return ResponseEntity.ok(CityResponse.convert(cityService.findByPlanet(planetName)));
        }catch(ParamNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CityResponse> update(
            @PathVariable int id,
            @RequestBody CityRequest cityRequest
    ) {
        if (cityService.cityIsPresent(id)) {
            City updatedCity = cityService.updateCIty(id, cityRequest);
            return ResponseEntity.ok(new CityResponse(updatedCity));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<CityResponse> remove(@PathVariable int id) {
        cityService.remove(id);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/destroy-city/{id}")
    public ResponseEntity<CityResponse> destroyCity(@PathVariable int id){
        cityService.destroyCity(id);
        return remove(id);}
}
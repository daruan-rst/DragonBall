package com.practice.dragonballcrud.controller;


import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.request.CityRequest;
import com.practice.dragonballcrud.response.CityResponse;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {

    final private CityRepository cityReposiory;

    @GetMapping("/findAll")
    public ResponseEntity<List<CityResponse>> findAll() {
        List<City> allCities = cityReposiory.findAll();
        return ResponseEntity.ok(CityResponse.convert(allCities));
    }

    @GetMapping("/hasPopulationGreaterThan/{population}")
    public ResponseEntity<List<CityResponse>> hasPopulationGreaterThan(long population) {
        List<City> cities = cityReposiory.findCityByPopulationGreaterThan(population);
        return ResponseEntity.ok(CityResponse.convert(cities));
    }

    @DeleteMapping("Remove/{id}")
    public ResponseEntity<CityResponse> remove(@PathVariable int id) {
        cityReposiory.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CityResponse> update(
            @PathVariable int id,
            @RequestBody CityRequest cityRequest
    ) {
        if (this.cityReposiory.findById(id).isPresent()) {
            City updatedCity = cityRequest.convert();
            updatedCity.setCityId(id);
            cityReposiory.save(updatedCity);
            return ResponseEntity.ok(new CityResponse(updatedCity));
        }
        return ResponseEntity.badRequest().build();
    }


}

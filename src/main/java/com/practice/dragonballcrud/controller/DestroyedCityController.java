package com.practice.dragonballcrud.controller;

import com.practice.dragonballcrud.response.DestroyedCityResponse;
import com.practice.dragonballcrud.service.DestroyedCityService;
import com.practice.dragonballcrud.service.PlanetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/destroyed-cities")
public class DestroyedCityController {

    private final PlanetService planetService;

    @Autowired
    private final DestroyedCityService destroyCityService;

    @GetMapping("/find-all")
    public ResponseEntity<List<DestroyedCityResponse>> findAll() {
    return ResponseEntity.ok(DestroyedCityResponse.convert(destroyCityService.findAll()));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<DestroyedCityResponse> remove(@PathVariable int id) {
        destroyCityService.remove(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("wish-for-city-to-return/{id}")
    public void wishForCityToComeBack(@PathVariable int id) {
        destroyCityService.wishForCityBack(id);
    }



}

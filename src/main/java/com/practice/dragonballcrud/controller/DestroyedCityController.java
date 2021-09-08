package com.practice.dragonballcrud.controller;

import com.practice.dragonballcrud.entities.DestroyedCity;
import com.practice.dragonballcrud.repository.DestroyedCityRepository;
import com.practice.dragonballcrud.response.DestroyedCityResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/destroyedCity")
public class DestroyedCityController {

    private final DestroyedCityRepository destroyedCityRepository;

    @GetMapping("/findAll")
    public ResponseEntity<List<DestroyedCityResponse>> findAll(){
        List<DestroyedCity> allDestroyedCities = destroyedCityRepository.findAll();
    return ResponseEntity.ok(DestroyedCityResponse.convert(allDestroyedCities));}

}

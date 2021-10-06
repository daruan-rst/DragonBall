package com.practice.dragonballcrud.controller;



import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.repository.PlanetRepository;
import com.practice.dragonballcrud.request.PlanetRequest;
import com.practice.dragonballcrud.response.PlanetResponse;
import com.practice.dragonballcrud.service.PlanetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    final private PlanetService planetService;

    @PostMapping
    public ResponseEntity<PlanetResponse> createNewPlanet(
            @RequestBody PlanetRequest planetRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Planet createdPlanet = planetService.createPlanet(planetRequest);
        URI uri = uriComponentsBuilder.path("/planet/{planetName}")
                .buildAndExpand(createdPlanet.getPlanetName()).toUri();
    return ResponseEntity.created(uri).body(new PlanetResponse(createdPlanet));}


    @GetMapping("/find-all")
    public ResponseEntity<List<PlanetResponse>> findAll(){
    return ResponseEntity.ok(PlanetResponse.convert(planetService.findAll()));
    }

    @GetMapping("/has-dragonBall")
    public ResponseEntity<List<PlanetResponse>> hasDragonBall(){
        return ResponseEntity.ok(PlanetResponse.convert(planetService.hasDragonBalls()));
    }


    @GetMapping("/has-population-greater-than/{population}")
    public ResponseEntity<List<PlanetResponse>> hasPopulationGreaterThan(@PathVariable long population){
    return ResponseEntity.ok(PlanetResponse.convert(planetService.hasPopulationGreaterThan(population)));
    }

    @PutMapping("/update/{planetName}")
    public ResponseEntity<PlanetResponse> update(
            @PathVariable String planetName,
            @RequestBody PlanetRequest planetRequest){
        return ResponseEntity.ok(new PlanetResponse(planetService.update(planetName, planetRequest)));
    }

    @DeleteMapping("remove/{planetName}")
    public ResponseEntity<PlanetResponse> remove(@RequestParam String planetName){
        planetService.remove(planetName);
        return ResponseEntity.ok().build();}

}

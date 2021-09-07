package com.practice.dragonballcrud.controller;



import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.repository.PlanetRepository;
import com.practice.dragonballcrud.request.PlanetRequest;
import com.practice.dragonballcrud.response.PlanetResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/planet")
public class PlanetController {

    final private PlanetRepository planetRepository;

    @PostMapping
    public ResponseEntity<PlanetResponse> createNewPlanet(
            @RequestBody PlanetRequest planetRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Planet createdPlanet = planetRequest.convert();
        planetRepository.save(createdPlanet);
        URI uri = uriComponentsBuilder.path("/planet/{planetName}")
                .buildAndExpand(createdPlanet.getPlanetName()).toUri();
    return ResponseEntity.created(uri).body(new PlanetResponse(createdPlanet));}


    @GetMapping("/findAll")
    public ResponseEntity<List<PlanetResponse>> findAll(){
        List<Planet> allPlanets = planetRepository.findAll();
        return ResponseEntity.ok(PlanetResponse.convert(allPlanets));
    }

    @GetMapping("/hasDragonBall")
    public ResponseEntity<List<PlanetResponse>> hasDragonBall(){
        List<Planet> planetsHaveDragonBall = planetRepository.findAll()
                .stream().filter(Planet::isHasDragonBalls)
                .collect(Collectors.toList());
        return ResponseEntity.ok(PlanetResponse.convert(planetsHaveDragonBall));
    }

    @GetMapping("/hasPopulationGreaterThan/{population}")
    public ResponseEntity<List<PlanetResponse>> hasPopulationGreaterThan(long population){
        List<Planet> planets = planetRepository.findPlanetByPlanetPopulationGreaterThan(population);
        return ResponseEntity.ok(PlanetResponse.convert(planets));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<PlanetResponse> remove(@PathVariable String planetName){
        planetRepository.deleteById(planetName);
        return ResponseEntity.ok().build();}

    @PutMapping("/update/{id}")
    public ResponseEntity<PlanetResponse> update(
            @PathVariable String planetName,
            @RequestBody PlanetRequest planetRequest){
        Planet updatedPlanet = planetRequest.updateConvert(planetName);
        planetRepository.save(updatedPlanet);
        return ResponseEntity.ok(new PlanetResponse(updatedPlanet));
    }


}

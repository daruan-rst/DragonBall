package com.practice.dragonballcrud.controller;



import com.practice.dragonballcrud.entities.City;
import com.practice.dragonballcrud.entities.Planet;
import com.practice.dragonballcrud.repository.PlanetRepository;
import com.practice.dragonballcrud.request.PlanetRequest;
import com.practice.dragonballcrud.response.PlanetResponse;
import com.practice.dragonballcrud.service.PlanetService;
import lombok.AllArgsConstructor;
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

    final private PlanetRepository planetRepository;
    final private PlanetService planetService;

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

    @GetMapping("/at-least-one-namek")
    public String atLeastOneNamek(@RequestParam String planetName){
         Planet planet = planetRepository.findById(planetName).get();
        String resposta = (planetService.doesPlanetHaveAtLeastOneNamek(planet)? "Sim" : "não");
        return resposta;
    }

    @GetMapping("/hasPopulationGreaterThan/{population}")
    public ResponseEntity<List<PlanetResponse>> hasPopulationGreaterThan(@PathVariable long population){
        List<Planet> planets = planetRepository.findPlanetByPlanetPopulationGreaterThan(population);
        return ResponseEntity.ok(PlanetResponse.convert(planets));
    }

    @PutMapping("/update/{planetName}")
    public ResponseEntity<PlanetResponse> update(
            @PathVariable String planetName,
            @RequestBody PlanetRequest planetRequest){
        Planet updatedPlanet = planetRequest.updateConvert(planetName);
        planetRepository.save(updatedPlanet);
        return ResponseEntity.ok(new PlanetResponse(updatedPlanet));
    }

    @DeleteMapping("remove/{planetName}")
    public ResponseEntity<PlanetResponse> remove(@RequestParam String planetName){
        planetRepository.deleteById(planetName);
        return ResponseEntity.ok().build();}



}

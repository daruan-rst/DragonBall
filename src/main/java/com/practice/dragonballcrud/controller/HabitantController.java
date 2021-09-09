package com.practice.dragonballcrud.controller;


import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.exceptions.ParamNotFoundException;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.HabitantRepository;
import com.practice.dragonballcrud.request.HabitantRequest;
import com.practice.dragonballcrud.response.HabitantResponse;
import com.practice.dragonballcrud.service.CityService;
import com.practice.dragonballcrud.service.PlanetService;
import com.practice.dragonballcrud.service.PopulationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/habitants")
public class HabitantController {

    private final HabitantRepository habitantRepository;
    private final PopulationService populationService;
    private final CityRepository cityRepository;



    @PostMapping
    public ResponseEntity<HabitantResponse> createHabitant(
            @RequestBody HabitantRequest habitantRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Habitant createdHabitant = habitantRequest.convert(cityRepository);
        habitantRepository.save(createdHabitant);
        if (createdHabitant.isAlive()) {
                populationService.updatePopulation(createdHabitant, 1);
        }
        URI uri = uriComponentsBuilder.path("/habitant/{id}")
                .buildAndExpand(createdHabitant.getId()).toUri();
        return ResponseEntity.created(uri).body(new HabitantResponse(createdHabitant));
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<HabitantResponse>> findByName(@PathVariable String name) {
        List habitants = habitantRepository.findHabitantsByName(name);
        return ResponseEntity.ok().body(HabitantResponse.convert(habitants));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<HabitantResponse>> findAll() {
        return ResponseEntity.ok().body(HabitantResponse.convert(habitantRepository.findAll()));
    }

    @GetMapping("/find-who-is-alive")
    public ResponseEntity<List<HabitantResponse>> findAlive() {
        var aliveCitizen = habitantRepository.findAll().
                stream().filter(Habitant::isAlive)
                .collect(Collectors.toList());
        return ResponseEntity.ok(HabitantResponse.convert(aliveCitizen));
    }

    @GetMapping("/find-who-is-dead")
    public ResponseEntity<List<HabitantResponse>> findWhoIsDead() {
        List ghostCitizen = habitantRepository.findAll().
                stream().filter(h -> !h.isAlive())
                .collect(Collectors.toList());
        return ResponseEntity.ok(HabitantResponse.convert(ghostCitizen));
    }

    @GetMapping("/find-by-city/{cityId}")
    public ResponseEntity<List<HabitantResponse>> findByCity(@RequestParam int cityId) {
        try {
            return ResponseEntity.ok(HabitantResponse
                    .convert(habitantRepository
                            .findHabitantsByCityId(cityRepository
                                    .getById(cityId))));
        } catch (ParamNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }


    }


    @GetMapping("/find-by-race/{race}")
    public ResponseEntity<List<HabitantResponse>> findByRace(@RequestParam Races race) {
        try {
            return ResponseEntity.ok().body(HabitantResponse.convert(habitantRepository.findByRace(race)));
        } catch (ParamNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HabitantResponse> update(
            @PathVariable int id,
            @RequestBody HabitantRequest habitantRequest) {
        Habitant habitant = habitantRequest.updateConvert(cityRepository, id);
        habitantRepository.save(habitant);
        return ResponseEntity.ok(new HabitantResponse(habitant));
    }

    @PutMapping("/kill/{id}")
    public ResponseEntity<HabitantResponse> kill(
            @PathVariable int id) {
        Habitant habitant = habitantRepository.findById(id);
        habitant.setAlive(false);
        populationService.updatePopulation(habitant, -1);
        habitantRepository.save(habitant);
        return ResponseEntity.ok(new HabitantResponse(habitant));
    }

    @PutMapping("/wish-back-to-life/{id}")
    public ResponseEntity<HabitantResponse> makeAWish(
            @PathVariable int id) {
        Habitant habitant = habitantRepository.findById(id);
        populationService.wishForSomeoneBackToLife(habitant);
        return ResponseEntity.ok(new HabitantResponse(habitant));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<HabitantResponse> remove(@PathVariable int id) {
        populationService.updatePopulation(habitantRepository.findById(id), -1);
        habitantRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

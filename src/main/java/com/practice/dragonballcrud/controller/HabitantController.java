package com.practice.dragonballcrud.controller;


import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.exceptions.ParamNotFoundException;
import com.practice.dragonballcrud.repository.HabitantRepository;
import com.practice.dragonballcrud.request.HabitantRequest;
import com.practice.dragonballcrud.response.HabitantResponse;
import com.practice.dragonballcrud.service.PopulationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/habitants")
public class HabitantController {

    @Autowired
    private PopulationService populationService;


    @PostMapping
    public ResponseEntity<HabitantResponse> createHabitant(
            @RequestBody HabitantRequest habitantRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Habitant createdHabitant = populationService.createHabitant(habitantRequest);
        URI uri = uriComponentsBuilder.path("/habitant/{id}")
                .buildAndExpand(createdHabitant.getId()).toUri();
        return ResponseEntity.created(uri).body(new HabitantResponse(createdHabitant));
    }

    @PostMapping("/someone-is-born/{name}")
    public ResponseEntity<HabitantResponse> someoneIsBorn(@PathVariable String name, UriComponentsBuilder uriComponentsBuilder){
        HabitantRequest bornPersonRequest = populationService.generateRandomHabitant(name);
        return createHabitant(
            bornPersonRequest,
            uriComponentsBuilder
    );}

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<HabitantResponse>> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(HabitantResponse.convert(populationService.findByName(name)));}

    @GetMapping("/find-all")
    public ResponseEntity<List<HabitantResponse>> findAll() {
        return ResponseEntity.ok().body(HabitantResponse.convert(populationService.findAll()));
    }

    @GetMapping("/find-who-is-alive")
    public ResponseEntity<List<HabitantResponse>> findAlive() {
        return ResponseEntity.ok(HabitantResponse.convert(populationService.findWhoIsAlive())); }

    @GetMapping("/find-who-is-dead")
    public ResponseEntity<List<HabitantResponse>> findWhoIsDead() {
        return ResponseEntity.ok(HabitantResponse.convert(populationService.ghostCitizen()));}

    @GetMapping("/find-by-city/{cityId}")
    public ResponseEntity<List<HabitantResponse>> findByCity(@RequestParam int cityId) {
        try {
            return ResponseEntity.ok(HabitantResponse
                    .convert(populationService.findByCityId(cityId)));
        } catch (ParamNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/find-by-race/{race}")
    public ResponseEntity<List<HabitantResponse>> findByRace(@RequestParam Races race) {
        try {
            return ResponseEntity.ok().body(HabitantResponse.convert(populationService.findByRace(race)));
        } catch (ParamNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HabitantResponse> update(
            @PathVariable int id,
            @RequestBody HabitantRequest habitantRequest) {
        return ResponseEntity.ok(new HabitantResponse(populationService.updateId(id, habitantRequest)));
    }

    @PutMapping("/kill/{id}")
    public ResponseEntity<HabitantResponse> kill(
            @PathVariable int id) {
        return ResponseEntity.ok(new HabitantResponse(populationService.kill(id)));
    }

    @PutMapping("/wish-back-to-life/{id}")
    public ResponseEntity<HabitantResponse> makeAWish(
            @PathVariable int id) {
        return ResponseEntity.ok(new HabitantResponse(populationService.wishBacktoLife(id)));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<HabitantResponse> remove(@PathVariable int id) {
        populationService.remove(id);
        return ResponseEntity.ok().build();
    }


}

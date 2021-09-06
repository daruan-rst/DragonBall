package com.practice.dragonballcrud.controller;


import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.exceptions.ParamNotFoundException;
import com.practice.dragonballcrud.repository.HabitantRepository;
import com.practice.dragonballcrud.request.HabitantRequest;
import com.practice.dragonballcrud.response.HabitantResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/habitant")
public class HabitantController {

    private final HabitantRepository habitantRepository;

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<HabitantResponse>> findByName(@PathVariable String name){
    List habitants = habitantRepository.findHabitantsByName(name);
    return ResponseEntity.ok().body(HabitantResponse.convert(habitants));}

    @GetMapping("/findAll")
    public ResponseEntity<List<HabitantResponse>> findAll(){
        return ResponseEntity.ok().body(HabitantResponse.convert(habitantRepository.findAll()));}

    @GetMapping("/findWhoIsAlive")
    public ResponseEntity<List<HabitantResponse>> findAlive(){
        List aliveCitizen = habitantRepository.findHabitantsByAlive();
        return ResponseEntity.ok(HabitantResponse.convert(aliveCitizen));
    }

    @GetMapping("/findWhoIsDead")
    public ResponseEntity<List<HabitantResponse>> findWhoIsDead(){
        List ghostCitizen = habitantRepository.findHabitantsByAliveFalse();
        return ResponseEntity.ok(HabitantResponse.convert(ghostCitizen));
    }

    @GetMapping("/findByRace/{race}")
    public ResponseEntity<List<HabitantResponse>> findByRace(@RequestParam Races race){
        try{
            return ResponseEntity.ok().body(HabitantResponse.convert(habitantRepository.findByRace(race)));
        } catch(ParamNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HabitantResponse> update(
            @PathVariable int id,
            @RequestBody HabitantRequest habitantRequest){
            Habitant habitant = habitantRequest.convert(habitantRequest);
            habitantRepository.save(habitant);
            return ResponseEntity.ok(new HabitantResponse(habitant));
    }




}
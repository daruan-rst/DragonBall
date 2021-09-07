package com.practice.dragonballcrud.controller;


import com.practice.dragonballcrud.entities.Habitant;
import com.practice.dragonballcrud.enums.Races;
import com.practice.dragonballcrud.exceptions.ParamNotFoundException;
import com.practice.dragonballcrud.repository.CityRepository;
import com.practice.dragonballcrud.repository.HabitantRepository;
import com.practice.dragonballcrud.request.HabitantRequest;
import com.practice.dragonballcrud.response.HabitantResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/habitant")
public class HabitantController {

    private final HabitantRepository habitantRepository;
    private final CityRepository cityRepository;


    @PostMapping
    public ResponseEntity<HabitantResponse> createHabitant(
            @RequestBody HabitantRequest habitantRequest,
            UriComponentsBuilder uriComponentsBuilder
    ){
        Habitant createdHabitant = habitantRequest.convert(cityRepository);
        URI uri = uriComponentsBuilder.path("/habitant/{id}")
                .buildAndExpand(createdHabitant.getId()).toUri();
        return ResponseEntity.created(uri).body(new HabitantResponse(createdHabitant));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<HabitantResponse>> findByName(@PathVariable String name){
    List habitants = habitantRepository.findHabitantsByName(name);
    return ResponseEntity.ok().body(HabitantResponse.convert(habitants));}

    @GetMapping("/findAll")
    public ResponseEntity<List<HabitantResponse>> findAll(){
        return ResponseEntity.ok().body(HabitantResponse.convert(habitantRepository.findAll()));}

    @GetMapping("/findWhoIsAlive")
    public ResponseEntity<List<HabitantResponse>> findAlive(){
        var aliveCitizen = habitantRepository.findAll().
                stream().filter(Habitant::isAlive)
                .collect(Collectors.toList());
        return ResponseEntity.ok(HabitantResponse.convert(aliveCitizen));
    }

    @GetMapping("/findWhoIsDead")
    public ResponseEntity<List<HabitantResponse>> findWhoIsDead(){
        List ghostCitizen = habitantRepository.findAll().
                stream().filter(h -> !h.isAlive())
                .collect(Collectors.toList());
        return ResponseEntity.ok(HabitantResponse.convert(ghostCitizen));
    }

    @GetMapping("/findByCity/{cityId}")
    public ResponseEntity<List<HabitantResponse>> findByCity(@RequestParam int cityId){
        try{
            return ResponseEntity.ok().body(HabitantResponse.convert(habitantRepository.findHabitantsByCityId(cityId)));
        } catch(ParamNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
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
            @RequestBody HabitantRequest habitantRequest)
    {
            Habitant habitant = habitantRequest.updateConvert(cityRepository, id);
            habitantRepository.save(habitant);
            return ResponseEntity.ok(new HabitantResponse(habitant));
    }

    @PutMapping("/kill/{id}")
    public ResponseEntity<HabitantResponse> kill(
            @PathVariable int id){
        var habitant = habitantRepository.findById(id);
        habitant.setAlive(false);
        habitantRepository.save(habitant);
        return ResponseEntity.ok(new HabitantResponse(habitant));
    }

    @DeleteMapping("Remove/{id}")
    public ResponseEntity<HabitantResponse> remove(@PathVariable int id){
        habitantRepository.deleteById(id);
    return ResponseEntity.ok().build();}
}

package com.example.leapro.controller;

import com.example.leapro.model.Luggage;
import com.example.leapro.service.LuggageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/luggage")
@CrossOrigin
public class LuggageController {

    @Autowired
    private LuggageService luggageService;


    // 1. Register new luggage
    @PostMapping
    public String createLuggage(@RequestBody Luggage luggage) {

        return luggageService.createLuggage(luggage);
    }


    // 2. Get all luggage
    @GetMapping
    public List<Luggage> getAllLuggage() {

        return luggageService.getAllLuggage();
    }


    // 3. Get luggage by ID
    @GetMapping("/{id}")
    public Luggage getLuggage(@PathVariable int id) {

        return luggageService.getLuggage(id);
    }


    // 4. Scan luggage and update location
   @PutMapping("/{id}/scan")
public String scanLuggage(
        @PathVariable int id,
        @RequestParam String location) {

    return luggageService.scanLuggage(id, location);
}


    // 5. Check luggage status using tag number
    @GetMapping("/status/{tag}")
    public Luggage getStatus(@PathVariable String tag) {

        return luggageService.getStatus(tag);
    }


    // 6. Delete luggage
    @DeleteMapping("/{id}")
    public String deleteLuggage(@PathVariable int id) {

        return luggageService.deleteLuggage(id);
    }
}
package com.galasd.testAPI.controllers;

import com.galasd.testAPI.entities.Nasa;
import com.galasd.testAPI.repositories.NasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/nasa")
public class NasaController {

    @Autowired
    private NasaRepository nasaRepository;

    // Get all nasa data list
    @GetMapping("/nasa")
    public List<Nasa> getNasaData() {
        return nasaRepository.findAll();
    }

    // Get asteroids by id
    @GetMapping("/nasa/{id}")
    public ResponseEntity<Nasa> getAsteroidsById(@PathVariable(value = "id") Long asteroidId) throws ResourceAccessException {
        Nasa nasa =  nasaRepository.findById(asteroidId).orElseThrow(() ->
                new ResourceAccessException("No asteroid found on "+ asteroidId));

        return ResponseEntity.ok().body(nasa);
    }

    // Create asteroid
    @PostMapping("/nasa")
    public Nasa createAsteorid(@Valid @RequestBody Nasa asteroid) {
        return nasaRepository.save(asteroid);
    }

}

package com.galasd.testAPI.controllers;

import com.galasd.testAPI.entities.Nasa;
import com.galasd.testAPI.repositories.NasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class NasaController {

    @Autowired
    private NasaRepository nasaRepository;

    // Get all nasa data list
    @GetMapping("/nasa")
    public List<Nasa> getNasaData() {
        return nasaRepository.findAll();
    }

    // Get asteroid by id
    @GetMapping("/nasa/{id}")
    public ResponseEntity<Nasa> getAsteroidById(@PathVariable(value = "id") Long asteroidId) throws ResourceAccessException {
        Nasa nasa = nasaRepository.findById(asteroidId).orElseThrow(() ->
                new ResourceAccessException("Asteroid not found on " + asteroidId));
        return ResponseEntity.ok().body(nasa);
    }

    // Create new asteorid
    @PostMapping("/nasa")
    public Nasa createAsteroid(@Valid @RequestBody Nasa asteroid) {
        return nasaRepository.save(asteroid);
    }

    // Update existing asteorid
    @PostMapping("/nasa/{id}")
    public ResponseEntity<Nasa> updateAsteorid(
            @PathVariable(value = "id") Long asteroidId, @Valid @RequestBody Nasa asteroid) throws ResourceAccessException {
        Nasa nasa = nasaRepository.findById(asteroidId).orElseThrow(() ->
                new ResourceAccessException("Asteroid not found on " + asteroidId));
        nasa.setName(asteroid.getName());
        nasa.setEarthDistance(asteroid.getEarthDistance());
        final Nasa updatedAsteroid = nasaRepository.save(asteroid);

        return ResponseEntity.ok().body(updatedAsteroid);
    }

    // Delete existing asteroid
    @DeleteMapping("/nasa/{id}")
    public Map<String, Boolean> deleteAsteroid(@PathVariable(value = "id") Long asteroidId) {
        Nasa nasa = nasaRepository.findById(asteroidId).orElseThrow(() ->
                new ResourceAccessException("Asteroid not found on " + asteroidId));
        nasaRepository.delete(nasa);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
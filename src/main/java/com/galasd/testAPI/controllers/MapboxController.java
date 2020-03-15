package com.galasd.testAPI.controllers;

import com.galasd.testAPI.entities.Mapbox;
import com.galasd.testAPI.repositories.MapboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mapbox")
public class MapboxController {

    @Autowired
    private MapboxRepository mapboxRepository;

    // Get all mapbox data list
    @GetMapping("/mapbox")
    public List<Mapbox> getMapboxData() {

        return mapboxRepository.findAll();
    }

    // Get cities by id
    @GetMapping("/mapbox/{id}")
    public ResponseEntity<Mapbox> getCitiesById(@PathVariable(value = "id") Long cityId)
            throws ResourceAccessException {
        Mapbox mapbox = mapboxRepository.findById(cityId).
                orElseThrow(() -> new ResourceAccessException("City not found on " + cityId));

        return ResponseEntity.ok().body(mapbox);
    }

    // Create city
    @PostMapping("/mapbox")
    public Mapbox createCity(@Valid @RequestBody Mapbox city) {
        return mapboxRepository.save(city);
    }

    // Update mapbox response entity
    @PutMapping("/mapbox/{id}")
    public ResponseEntity<Mapbox> updateCity(@PathVariable(value = "id") Long cityId,
                                             @Valid @RequestBody Mapbox cityDetails) throws ResourceAccessException {
        Mapbox mapbox = mapboxRepository.findById(cityId).
                orElseThrow(() -> new ResourceAccessException("City not found on " + cityId));

        mapbox.setName(cityDetails.getName());
        mapbox.setLatitude(cityDetails.getLatitude());
        mapbox.setLongitude(cityDetails.getLongitude());
        //todo updated at

        final Mapbox updatedMapbox = mapboxRepository.save(cityDetails);
        return ResponseEntity.ok().body(updatedMapbox);

    }

    // Delete city
    @DeleteMapping("/mapbox/{id}")
    public Map<String, Boolean> deleteCity(@PathVariable(value = "id") Long cityId) throws Exception {
        Mapbox mapbox = mapboxRepository.findById(cityId).orElseThrow(() -> new ResourceAccessException(
                "City not found on " + cityId));

        mapboxRepository.delete(mapbox);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}

package com.example.plant_shop_basic.controller;

import com.example.plant_shop_basic.entity.Plant;
import com.example.plant_shop_basic.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    /**
     * Get all plants
     * @return List of all plants
     */
    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return new ResponseEntity<>(plants, HttpStatus.OK);
    }

    @PostMapping("/{id}/buy")
    public ResponseEntity<String> buyPlant(@PathVariable("id") Long id, @RequestParam BigDecimal quantity) {
        if (quantity.compareTo(BigDecimal.ZERO) > 0) {
            try {
                plantService.buyPlant(id, quantity);
                return new ResponseEntity<>("Purchase successful", HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        else return new ResponseEntity<>("Purchase not successful", HttpStatus.BAD_REQUEST);
    }



    /**
     * Get a single plant by ID
     * @param id Plant ID
     * @return The plant with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable("id") Long id) {
        Optional<Plant> plant = plantService.getPlantById(id);
        return plant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Create a new plant
     * @param plant The plant to be created
     * @return The created plant
     */
    @PostMapping
    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant) {
        Plant newPlant = plantService.createPlant(plant);
        return new ResponseEntity<>(newPlant, HttpStatus.CREATED);
    }

    /**
     * Update an existing plant
     * @param id The ID of the plant to update
     * @param plant The updated plant details
     * @return The updated plant
     */
    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable("id") Long id, @RequestBody Plant plant) {
        Optional<Plant> updatedPlant = plantService.updatePlant(id, plant);
        return updatedPlant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a plant by ID
     * @param id The ID of the plant to delete
     * @return A message indicating the deletion status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlant(@PathVariable("id") Long id) {
        boolean isDeleted = plantService.deletePlant(id);
        if (isDeleted) {
            return new ResponseEntity<>("Plant deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Plant not found", HttpStatus.NOT_FOUND);
        }
    }
}
package com.example.plant_shop_basic.service;

import com.example.plant_shop_basic.entity.Plant;
import com.example.plant_shop_basic.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    /**
     * Get all plants from the database
     * @return List of all plants
     */
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    /**
     * Get a plant by ID
     * @param id The ID of the plant
     * @return Optional containing the plant if found, or empty if not found
     */
    public Optional<Plant> getPlantById(Long id) {
        return plantRepository.findById(id);
    }

    /**
     * Create a new plant
     * @param plant The plant to be created
     * @return The newly created plant
     */
    public Plant createPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    /**
     * Update an existing plant
     * @param id The ID of the plant to update
     * @param plant The updated plant details
     * @return Optional containing the updated plant if found, or empty if not found
     */
    public Optional<Plant> updatePlant(Long id, Plant plant) {
        return plantRepository.findById(id).map(existingPlant -> {
            existingPlant.setPrice(plant.getPrice());
            existingPlant.setAmountInstock(plant.getAmountInstock());
            existingPlant.setPlantName(plant.getPlantName());
            existingPlant.setAuthor(plant.getAuthor());
            existingPlant.setUser(plant.getUser());
            return plantRepository.save(existingPlant);
        });
    }

    public Optional<Plant> buyPlant(Long id, BigDecimal quantity) {
        if (plantRepository.existsById(id)) {
            BigDecimal amountInStock = plantRepository.findAmountInStockById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Plant not found"));

            if (amountInStock.compareTo(quantity) > 0) {
                return plantRepository.findById(id).map(existingPlant -> {
                    existingPlant.setAmountInstock(existingPlant.getAmountInstock().subtract(quantity));
                    return plantRepository.save(existingPlant);
                });
            } else {
                throw new IllegalArgumentException("Plant stock is not enough");
            }
        } else {
            throw new IllegalArgumentException("Plant not found");
        }
    }


    public BigDecimal getPlantStock(Long plantId) {
        return plantRepository.findAmountInStockById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant not found"));
    }


    /**
     * Delete a plant by ID
     * @param id The ID of the plant to delete
     * @return True if the plant was deleted, false if not found
     */
    public boolean deletePlant(Long id) {
        if (plantRepository.existsById(id)) {
            plantRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
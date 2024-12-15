package com.example.plant_shop_basic.repository;

import com.example.plant_shop_basic.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant,Long> {
}

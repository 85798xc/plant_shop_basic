package com.example.plant_shop_basic.repository;

import com.example.plant_shop_basic.entity.Plant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Plant p SET p.amountInstock = p.amountInstock - :quantity WHERE p.id = :plantId AND p.amountInstock >= :quantity")
    int decreaseStock(Long plantId, BigDecimal quantity);

    @Query("SELECT p.amountInstock FROM Plant p WHERE p.id = :plantId")
    Optional<BigDecimal> findAmountInStockById(Long plantId);
}

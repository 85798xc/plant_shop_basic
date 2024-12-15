package com.example.plant_shop_basic.repository;

import com.example.plant_shop_basic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
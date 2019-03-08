package com.molly.repository;

import com.molly.domain.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Long> {
    List<Food> findAll();
    Food findByFoodType(String foodType);
//    Food createFood(String newfood);
//    void deleteFood(String foodName);
}

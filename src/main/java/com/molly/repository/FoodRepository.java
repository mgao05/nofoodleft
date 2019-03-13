package com.molly.repository;

import com.molly.domain.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Long> {
    List<Food> findAll();
    Food findByFoodType(String foodType);
    void delete(Food food);
    Food save(Food food);
    List<Food> findByBuildingId(Long id);

}
//todo ask repository syntax should be same as sql requirement
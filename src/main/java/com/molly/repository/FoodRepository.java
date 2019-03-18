package com.molly.repository;

import com.molly.domain.Food;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Long> {
    List<Food> findAll();
    @Query("select f from Food f where f.foodType=?1")
    List<Food> findByFoodType(String foodType);
    void delete(Food food);
    Food save(Food food);
    List<Food> findByBuilding_Id(Long id);

}
//todo ask repository syntax should be same as sql requirement
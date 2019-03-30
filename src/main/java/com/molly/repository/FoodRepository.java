package com.molly.repository;

import com.molly.domain.Food;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Long> {
    List<Food> findAll();
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
    List<Food> findByFoodType(String foodType);
    List<Food> findByBuilding_Id(Long id); //go to building entity to find by id

}

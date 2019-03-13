package com.molly.service;

import com.molly.domain.Food;
import com.molly.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Transactional
    public Food findById(Long id){
        return foodRepository.findById(id).get();
    }

    @Transactional
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Transactional
    public void delete(Food food){
        foodRepository.delete(food);
    }

    @Transactional
    public Food findByFoodType(String foodType){
       return foodRepository.findByFoodType(foodType);
    }

    @Transactional
    public List<Food> findByBuildingId(Long id){
        return foodRepository.findByBuilding_Id(id);
    }
}
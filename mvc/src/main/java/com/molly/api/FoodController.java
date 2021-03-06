package com.molly.api;

import com.molly.domain.Food;
import com.molly.repository.FoodRepository;
import com.molly.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/food", "/api/foods"})
public class FoodController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;

    @RequestMapping(method = RequestMethod.POST)
    public Food generateFood(@RequestBody Food food) {
        return foodService.save(food);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Food> getFoodList() {
        logger.debug("List food");
        return foodRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"foodType"})
    public List<Food> findByFoodType(@RequestParam("foodType") String foodType) {
        logger.debug(foodType);
        return foodService.findByFoodType(foodType);
    }


    @RequestMapping(method = RequestMethod.GET, params = {"building_Id"})
    public List<Food> findByBuildingId(@RequestParam("building_Id") Long id) {
        logger.debug("yy" + id);
        return foodService.findByBuildingId((id));
    }

    @ResponseBody
    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public Map<String, String> uploadPicture(@RequestParam(value = "pic") MultipartFile picture) {
        Map<String, String> result = new HashMap<>(1);
        logger.info(picture.getOriginalFilename());
        return result;
    }
}
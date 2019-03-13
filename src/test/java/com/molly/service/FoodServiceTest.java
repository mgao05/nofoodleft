package com.molly.service;

import com.molly.config.AppConfig;
import com.molly.domain.Area;
import com.molly.domain.Building;
import com.molly.domain.Food;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class FoodServiceTest {
    @Autowired
    private FoodService foodService;
    private Food newFood;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private AreaService areaService;
    private Building newBuilding;

    @Before
    public void setup(){
        newFood = new Food();
        newFood.setFoodType("Vietnam");
        newFood.setBuilding(new Building());
        newBuilding = new Building();
        newBuilding.setBuildingName("trumptower");
        newBuilding.setBuildingAddress("Arlington,VA");
    }

    @Test
    @Transactional
    public void findByIdTest(){
        foodService.save(newFood);
        Food testFood = foodService.findById(newFood.getId());
        assertNotNull(testFood);
        assertEquals(newFood.getId(),testFood.getId());
    }

    @Test
    public void saveTest(){
        assertTrue(false);
    }

    @Test
    public void deleteTest(){
        assertTrue(false);
    }

    @Test
    public void findByFoodTypeTest(){
        assertTrue(false);
    }

    @Test
    @Transactional
    public void findByBuildingIdTest(){
        Area a = new Area();
        a.setAreaName("ryo's house");
        areaService.save(a);
        newBuilding.setArea(a);
        buildingService.save(newBuilding);
        newFood.setBuilding(newBuilding);
        foodService.save(newFood);
        Building b = buildingService.findById(newBuilding.getId());
        assertNotNull(b.getId());
//        List<Food> testFood = foodService.findByBuildingId(newBuilding.getId());
//        assertNotNull(testFood);
//        Food firstFood = testFood.get(0);
//        assertEquals(newFood.getId(),firstFood.getId());
    }

}

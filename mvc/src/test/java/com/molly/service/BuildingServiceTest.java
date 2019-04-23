package com.molly.service;
import com.molly.config.AppConfig;
import com.molly.domain.Area;
import com.molly.domain.Building;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.molly.service.AreaService;
import com.molly.service.BuildingService;


import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class BuildingServiceTest {
    @Autowired
    private BuildingService buildingService;
    private Building newBuilding;
    @Autowired
    private AreaService areaService;
    //private Area newArea;

   @Before
   public void setup(){
       newBuilding = new Building();
       newBuilding.setBuildingName("SEAS");
       newBuilding.setBuildingAddress("Arlington");

   }

    @Test
    @Transactional
    public void findByIdTest(){
        Area a = new Area();
        a.setAreaName("ryo's house");
        areaService.save(a);
        newBuilding.setArea(a);
        buildingService.save(newBuilding);
        Building testBuilding = buildingService.findById(newBuilding.getId());
        assertNotNull(testBuilding);
        assertEquals(newBuilding.getId(),testBuilding.getId());
    }

    @Test
    @Transactional
    public void findByBuildingNameTest(){
        Area a = new Area();
        a.setAreaName("ryo's house");
        a.getId();
        areaService.save(a);
        newBuilding.setArea(a);
        buildingService.save(newBuilding);
        newBuilding.getId();
        Building testBuilding = buildingService.findByBuildingName(newBuilding.getBuildingName());
        assertNotNull(testBuilding);
        assertEquals(newBuilding.getBuildingName(),testBuilding.getBuildingName());
    }

    @Test
    @Transactional
    public void findByAreaIdTest(){
        Area testArea = new Area();
        testArea.setAreaName("nowhere");
        testArea.getId();
        areaService.save(testArea);
        newBuilding.setArea(testArea);
        buildingService.save(newBuilding);
        List<Building> buildings = buildingService.findByAreaId(testArea.getId());
        assertEquals(buildings.size(),1);
        assertEquals(buildings.get(0).getId(),newBuilding.getId());

    }
}

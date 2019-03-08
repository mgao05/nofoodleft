package com.molly.api;

import com.molly.domain.Building;
import com.molly.repository.BuildingRepository;
import com.molly.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/buildings", "/api/building"})
public class BuildingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingService buildingService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Building> getBuildingList() {
        logger.debug("List buildings");
        return buildingRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"buildingName"})
    public Building genarateBuilding(@RequestParam String buildingName){
        return buildingService.findByBuildingName(buildingName);
    }

}


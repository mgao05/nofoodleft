package com.molly.service;

import com.molly.domain.Building;
import com.molly.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Transactional
    public Building findById(Long id){
        return buildingRepository.findById(id).get();
    }

    @Transactional
    public Building save(Building building){
        return buildingRepository.save(building);
    }

    @Transactional
    public Building findByBuildingName(String buildingName) {
        return buildingRepository.findByBuildingName(buildingName);
    }

    @Transactional
    public List<Building> findByAreaId(Long id) {
        return buildingRepository.findByArea_Id(id);
    }

//    @Transactional
//    public Building createBuilding(Building newBuilding){return buildingRepository.save(newBuilding);}
}

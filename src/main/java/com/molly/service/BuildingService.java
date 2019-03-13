package com.molly.service;

import com.molly.domain.Building;
import com.molly.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Transactional
    public Building findById(Long id){
        return buildingRepository.findById(id).get();
    }

    @Transactional
    public Building findByBuildingName(String buildingName) {
        return buildingRepository.findByBuildingName(buildingName);
    }

    @Transactional
    public Building findByAreaId(long id) {
        return buildingRepository.findByAreaId(id);
    }

//    @Transactional
//    public Building createBuilding(Building newBuilding){return buildingRepository.save(newBuilding);}
}

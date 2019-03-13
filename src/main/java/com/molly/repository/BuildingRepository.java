package com.molly.repository;

import com.molly.domain.Building;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BuildingRepository extends CrudRepository<Building, Long> {
    List<Building> findAll();
    Building findByBuildingName(String buildingName);
    Building findByArea_Id(Long id); //foreign key has to follow this way, area_Id with line in middle to get recognized

}

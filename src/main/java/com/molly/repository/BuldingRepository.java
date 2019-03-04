package com.molly.repository;

import com.molly.domain.Building;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BuldingRepository extends CrudRepository<Building, Long> {
    List<Building> findAll();

}

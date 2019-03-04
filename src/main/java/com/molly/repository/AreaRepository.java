package com.molly.repository;

import com.molly.domain.Area;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AreaRepository extends CrudRepository<Area, Long> {
    List<Area> findAll();
}

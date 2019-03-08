package com.molly.service;

import com.molly.domain.Area;
import com.molly.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service

public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Transactional
    public Area findByAreaName(String areaName){
        return areaRepository.findByAreaName(areaName);}
//    @Transactional
//    public Area createArea(Area newArea){return areaRepository.save(newArea);}
}
